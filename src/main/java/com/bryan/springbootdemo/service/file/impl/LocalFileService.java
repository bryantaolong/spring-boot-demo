package com.bryan.springbootdemo.service.file.impl;

import com.bryan.springbootdemo.service.file.FileService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 类名: LocalFileService
 * 包名: com.bryan.springbootdemo.service.file.impl
 * 描述: 本地文件上传和删除服务实现，提供文件上传和删除功能。
 * 作者: Bryan Long
 * 创建时间: 2024/12/18 - 14:06
 * 版本: v1.0
 */
@Primary
@Service("LocalFileService")
public class LocalFileService implements FileService {

    private static final String UPLOAD_DIR = "uploads/";  // 上传文件的目录

    static {
        // 创建上传目录（如果不存在）
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    /**
     * 上传文件并保存到本地目录。
     *
     * @param inputStream 文件的输入流
     * @param originalFilename 文件的原始名称
     * @return 上传后的文件路径
     */
    @Override
    public String uploadFile(InputStream inputStream, String originalFilename) {
        try {
            // 生成唯一的文件名，防止文件重名
            String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFilename;
            String filePath = UPLOAD_DIR + uniqueFileName;

            // 使用 Commons IO 的 FileUtils 将输入流保存为文件
            FileUtils.copyInputStreamToFile(inputStream, new File(filePath));

            return filePath; // 返回文件路径
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败", e); // 上传失败时抛出运行时异常
        } finally {
            IOUtils.closeQuietly(inputStream); // 关闭输入流，避免资源泄漏
        }
    }

    /**
     * 删除指定的文件。
     *
     * @param fileName 要删除的文件名
     */
    @Override
    public void deleteFile(String fileName) {
        try {
            File file = new File(UPLOAD_DIR + fileName);
            if (file.exists()) {
                // 使用 Commons IO 删除文件
                FileUtils.forceDelete(file);
            } else {
                throw new RuntimeException("文件不存在: " + fileName); // 如果文件不存在，抛出异常
            }
        } catch (IOException e) {
            throw new RuntimeException("文件删除失败", e); // 删除失败时抛出运行时异常
        }
    }
}
