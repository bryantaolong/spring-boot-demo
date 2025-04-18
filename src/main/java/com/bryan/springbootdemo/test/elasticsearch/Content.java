package com.bryan.springbootdemo.test.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * ClassName: Content
 * Package: com.bryan.springbootdemo.domain.entity
 * Description:
 * Author: Bryan Long
 * Create: 2025/1/4 - 16:20
 * Version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "contents")
public class Content implements Serializable {
//    @Id
//    private long id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String img;

    @Field(type = FieldType.Text)
    private String price;
}
