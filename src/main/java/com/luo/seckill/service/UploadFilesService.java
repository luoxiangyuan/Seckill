package com.luo.seckill.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class UploadFilesService {
    /*
     * 上传文件到指定目录，并返回可访问的路径
     * */

    public String upload(MultipartFile uploadFile, String path, HttpServletRequest req) {

        if (uploadFile.isEmpty()) {
            return null;
        }
//        String basePath = "/usr/local/Springboot/WebNest/uploadFiles";
        String basePath = "G:/Workspace/IntelliJ IDEA/uploadFiles";
        String filePath = new String();


        File folder = new File(basePath + path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try {
            uploadFile.transferTo(new File(folder, Objects.requireNonNull(uploadFile.getOriginalFilename())));
            filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/" + "WebNest/uploadFiles"
                    + path + "/" + uploadFile.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
