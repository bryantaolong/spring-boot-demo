package com.bryan.springbootdemo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 类名: CreateUpdateTimeHandler
 * 包名: com.bryan.template.common.handler
 * 描述: 自动填充创建时间和更新时间的处理器，用于 MyBatis Plus 实现自动填充功能。
 * 作者: Bryan Long
 * 创建时间: 2024/12/29 - 19:02
 * 版本: v1.0
 */
@Slf4j
@Component
public class CreateUpdateTimeHandler implements MetaObjectHandler {

    /**
     * 在插入操作时自动填充 createTime 和 updateTime 字段。
     *
     * @param metaObject 元对象，包含了对象的元信息
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("在插入操作时自动填充 createTime 和 updateTime 字段");
        this.strictInsertFill(metaObject, "createTime", Date::new, Date.class);
        this.strictInsertFill(metaObject, "updateTime", Date::new, Date.class);
    }

    /**
     * 在更新操作时自动填充 updateTime 字段。
     *
     * @param metaObject 元对象，包含了对象的元信息
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("在更新操作时自动填充 updateTime 字段");
        this.strictUpdateFill(metaObject, "updateTime", Date::new, Date.class);
    }

}
