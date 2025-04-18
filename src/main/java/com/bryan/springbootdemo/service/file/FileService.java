package com.bryan.springbootdemo.service.file;

import java.io.InputStream;

/**
 * InterfaceName: FileService
 * Package: com.bryan.toyouth.service
 * Description: 文件服务接口，定义文件上传和删除功能
 * Author: Bryan Long
 * Create: 2024/12/4 - 18:17
 * Version: v1.0
 */
public interface FileService {

    /**
     * 上传文件
     * @param inputStream 文件输入流
     * @param originalFilename 原始文件名
     * @return 文件的访问 URL
     */
    String uploadFile(InputStream inputStream, String originalFilename);

    /**
     * 删除文件
     * @param fileName 文件名
     */
    void deleteFile(String fileName);
}
