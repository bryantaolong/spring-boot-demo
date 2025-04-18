package com.bryan.springbootdemo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: UserRole
 * Package: com.bryan.springbootdemo.domain.entity
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 21:58
 * Version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    private Integer id;
    private String roleName;
}
