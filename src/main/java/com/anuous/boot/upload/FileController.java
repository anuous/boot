package com.anuous.boot.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @GetMapping("/index")
    public String upload() {
        return "index";
    }


    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
       // String filePath = "/Users/itinypocket/workspace/temp/";
       // File dest = new File(filePath + fileName);
        try {
            LOGGER.info("上传成功");
            return "上传成功";
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败！";
    }
}
