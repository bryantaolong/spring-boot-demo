package com.bryan.springbootdemo.test.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: Posts
 * Package: com.bryan.springbootdemo.domain.entity
 * Description:
 * Author: Bryan Long
 * Create: 2025/1/25 - 19:47
 * Version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posts implements Serializable {
    private String id;
    private String title;
    private String body;
    private String category;
    private Integer likes;
    private List<String> tags;
    private String date;
}
