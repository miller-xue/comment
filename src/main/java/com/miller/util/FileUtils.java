package com.miller.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by miller on 2018/7/8
 */
public class FileUtils {

    public static String save(MultipartFile multipartFile, String savePath) throws IOException {
        File path = new File(savePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File file = new File(path, fileName);
        multipartFile.transferTo(file);
        return fileName;
    }


    public static void removeFile(String path,String fileName) {
        File file = new File(path, fileName);
        file.deleteOnExit();
    }

}
