package com.bryan.springbootdemo.controller.file;

import com.bryan.springbootdemo.utils.ResponseUtils;
import com.bryan.springbootdemo.model.response.BaseResponse;
import com.bryan.springbootdemo.model.enums.StatusCode;
import com.bryan.springbootdemo.service.file.FileService;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * ClassName: LocalFileController
 * Package: com.bryan.toyouth.controller
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/18 - 14:07
 * Version: v1.0
 */
@Slf4j
@RestController
@RequestMapping("/files")
public class LocalFileController {

    @Autowired
    @Qualifier("LocalFileService")
    private FileService fileService;

    /**
     * 文件上传接口
     * @param file 上传的文件
     * @return 文件访问 URL
     */
    @PostMapping("/upload")
    public BaseResponse<?> uploadFile(
            @RequestParam("file") @NotNull MultipartFile file) {
        log.info("LocalFileController.uploadFile: {}", file.getOriginalFilename());

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String fileUrl = fileService.uploadFile(inputStream, originalFilename);

            return ResponseUtils.success(fileUrl, "文件上传成功");
        } catch (Exception e) {
            return ResponseUtils.error(StatusCode.SYSTEM_ERROR, e.getMessage());
        }
    }

    /**
     * 文件删除接口
     * @param fileName 文件名
     * @return 删除结果
     */
    @DeleteMapping("/delete/{fileName}")
    public BaseResponse<?> deleteFile(
            @PathVariable @NotNull String fileName) {
        try {
            fileService.deleteFile(fileName);

            return ResponseUtils.success("File deleted successfully");
        } catch (Exception e) {
            return ResponseUtils.error(StatusCode.SYSTEM_ERROR, e.getMessage());
        }
    }
}

