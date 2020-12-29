package com.spbo.blog.controller;

import com.spbo.blog.service.FileService;
import com.spbo.blog.service.UserService;
import com.spbo.blog.domain.Usermsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@CrossOrigin
public class FileController {

    @Value("${baseUrl}")
    private String baseUrl;

    @Value("${userFilePath}")
    private String userFilePath;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @PostMapping("/file")
    public boolean uploadFile(@RequestParam("file") MultipartFile multipartFile,@RequestParam("id") String id) {
        String uuid="img"+System.currentTimeMillis();
        String fileName= uuid+multipartFile.getOriginalFilename();
        try {
            if (fileService.uploadFile(multipartFile,fileName)) {
                String result = baseUrl+"/image?imageName="+fileName;
                Usermsg ud = new Usermsg();
                ud.setIduser(id);
                ud.setUserhig(result);
                userService.updateUserInfo(ud);
                return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    @GetMapping("/image")
    public void getImage(@RequestParam("imageName") String imageName,
                         HttpServletResponse response) throws IOException {
        File fileDir = new File(userFilePath);
        File image=new File(fileDir.getAbsolutePath()+"/"+imageName);
        if (image.exists()){
            FileInputStream fileInputStream=new FileInputStream(image);
            byte[] bytes=new byte[fileInputStream.available()];
            if (fileInputStream.read(bytes)>0){
                OutputStream outputStream=response.getOutputStream();
                outputStream.write(bytes);
                outputStream.close();
            }
            fileInputStream.close();
        }
    }

}
