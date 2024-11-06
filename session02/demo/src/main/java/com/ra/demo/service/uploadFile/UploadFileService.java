package com.ra.demo.service.uploadFile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Service
public class UploadFileService {
    @Value("${upload-path}")
    private String uploadPath;

    public String uploadFile(MultipartFile file) {
        File myFile = new File(uploadPath);
        if (!myFile.exists()) {
            myFile.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(),new File(uploadPath + File.separator + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }
}
