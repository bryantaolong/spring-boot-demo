package com.bryan.springbootdemo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: PageRequest
 * Package: com.bryan.springbootdemo.model.request
 * Description:
 * Author: Bryan Long
 * Create: 2025/2/16 - 17:28
 * Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {

    /**
     * 当前页号
     */
    private int pageNum;

    /**
     * 页面大小
     */
    private int pageSize;

}
