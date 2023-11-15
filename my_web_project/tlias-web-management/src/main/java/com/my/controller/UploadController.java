package com.my.controller;

import com.my.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    /**
     * local store
     * @param image
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("upload : {}",image);

        //get the name of file
        String originalFilename = image.getOriginalFilename();

        //create a unique filename --uuid
        int index = originalFilename.lastIndexOf('.');
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("new filename :{}", newFileName);
        String url = "/Users/ren/Documents/data/RecFile/" + newFileName;

        //store the file to local
        image.transferTo(new File(url));

        return Result.success(url);
    }

}
