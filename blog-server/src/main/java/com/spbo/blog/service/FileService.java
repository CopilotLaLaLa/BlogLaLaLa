package com.spbo.blog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FileService {

    boolean uploadFile(MultipartFile multipartFile,String fileName)throws IOException;
}
