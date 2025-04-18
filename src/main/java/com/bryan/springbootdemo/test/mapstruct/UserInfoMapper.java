package com.bryan.springbootdemo.test.mapstruct;

import org.mapstruct.Mapper;

/**
 * ClassName: UserConverter
 * Package: com.bryan.springbootdemo.converter
 * Description:
 * Author: Bryan Long
 * Create: 2025/1/6 - 9:43
 * Version: v1.0
 */
@Mapper
public interface UserInfoMapper {
    UserInfoDTO userInfoToUserInfoDTO(UserInfo userInfo);
}
