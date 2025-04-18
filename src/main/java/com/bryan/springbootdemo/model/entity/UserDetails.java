package com.bryan.springbootdemo.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: UserDetails
 * Package: com.bryan.template.domain.entity
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 18:47
 * Version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails implements Serializable {
    private String id;

    private String username;
    private String password;

    private Integer roleId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Integer removed;
}
