package com.bryan.springbootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bryan.springbootdemo.model.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserRoleMapper
 * Package: com.bryan.template.mapper
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 21:58
 * Version: v1.0
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
//    @Select("SELECT * FROM user_role")
//    List<UserRole> selectList();

//    @Select("SELECT * FROM user_role WHERE id = #{id}")
//    UserRole selectById(Integer id);
}
