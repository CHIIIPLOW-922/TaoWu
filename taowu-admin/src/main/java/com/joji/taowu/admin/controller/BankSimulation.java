package com.joji.taowu.admin.controller;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Bank {
    private int windowCount;
    private int maxCustomersPerWindow;
    private ReentrantLock[] windowLocks;

    public Bank(int windowCount, int maxCustomersPerWindow) {
        this.windowCount = windowCount;
        this.maxCustomersPerWindow = maxCustomersPerWindow;
        this.windowLocks = new ReentrantLock[windowCount];
        for (int i = 0; i < windowCount; i++) {
            windowLocks[i] = new ReentrantLock();
        }
    }

    public void serveCustomer(int customerId) {
        int selectedWindow = -1;
        for (int i = 0; i < windowCount; i++) {
            if (windowLocks[i].tryLock()) {
                selectedWindow = i;
                break;
            }
        }

        if (selectedWindow != -1) {
            System.out.println("Customer " + customerId + " is being served at Window " + selectedWindow);
            try {
                Thread.sleep(new Random().nextInt(3000)); // Simulate customer service time
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                windowLocks[selectedWindow].unlock();
                System.out.println("Customer " + customerId + " has left Window " + selectedWindow);
            }
        } else {
            System.out.println("Customer " + customerId + " is waiting for a window to be available.");
        }
    }
}

class Customer implements Runnable {
    private Bank bank;
    private int customerId;

    public Customer(Bank bank, int customerId) {
        this.bank = bank;
        this.customerId = customerId;
    }

    @Override
    public void run() {
        bank.serveCustomer(customerId);
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        int windowCount = 5; // Number of bank windows
        int maxCustomersPerWindow = 3; // Maximum customers that can be served simultaneously per window
        Bank bank = new Bank(windowCount, maxCustomersPerWindow);

        ExecutorService executor = Executors.newFixedThreadPool(windowCount);

        int totalCustomers = 10; // Total number of customers
        for (int i = 0; i < totalCustomers; i++) {
            executor.execute(new Customer(bank, i));
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
