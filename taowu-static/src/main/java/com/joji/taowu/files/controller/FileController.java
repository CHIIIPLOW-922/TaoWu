package com.joji.taowu.files.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 实现文件上传控制器
 * */
@RestController
@RequestMapping("/files")
public class FileController {

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        // 指定保存文件的目录
        String fileNamePath ="/public/"+ file.getOriginalFilename();
        file.transferTo(new File(fileNamePath));
        return fileNamePath; // 返回上传成功后的URL
    }

}
