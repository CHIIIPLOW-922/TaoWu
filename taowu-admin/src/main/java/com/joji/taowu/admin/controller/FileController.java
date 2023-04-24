package com.joji.taowu.admin.controller;


import cn.hutool.core.io.FileUtil;
import com.joji.taowu.common.utils.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 实现文件上传控制器
 */
@RestController
@RequestMapping("admin/files")
public class FileController {
    /*@Autowired
    private FileClient fileClient;*/

    /*@PostMapping("upload")
    public String upload(MultipartFile file) throws IOException {
        System.out.println(file.getBytes().length);
        return fileClient.upload(file);
    }*/

    @PostMapping("upload")
    public R upload(MultipartFile file) {
        try {
            // 保存文件到指定目录
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            String filePath = "public/"+fileName;
            String rootFilePath  = System.getProperty("user.dir")+"/taowu-static/src/main/resources/"+filePath;
            FileUtil.writeBytes(file.getBytes(),rootFilePath);//把文件写入到上传的路径

            // 返回上传成功信息，包括图片上传的地址
            return R.ok("上传成功",filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return R.fail("上传失败",e.getMessage());
        }
    }






}
