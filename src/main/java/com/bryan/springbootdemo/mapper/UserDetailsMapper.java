package com.bryan.springbootdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bryan.springbootdemo.model.entity.UserDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserDetailsMapper
 * Package: com.bryan.template.mapper
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 18:55
 * Version: v1.0
 */
@Mapper
public interface UserDetailsMapper extends BaseMapper<UserDetails> {
//    @Select("SELECT * FROM user_details WHERE id = #{id}")
//    UserDetails selectById(String id);
}
