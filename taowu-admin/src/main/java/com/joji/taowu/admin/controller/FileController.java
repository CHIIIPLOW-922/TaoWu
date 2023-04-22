package com.joji.taowu.admin.controller;


import com.joji.taowu.common.client.FileClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/files")
public class FileController {

    @Resource
    private FileClient fileClient;

    @PostMapping("/upload")
    public String upload(MultipartFile file){

        return  fileClient.upload(file);
    }
}
