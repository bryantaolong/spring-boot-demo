package com.bryan.springbootdemo.service.auth.impl;

import com.bryan.springbootdemo.model.entity.UserDetails;
import com.bryan.springbootdemo.mapper.UserDetailsMapper;
import com.bryan.springbootdemo.service.auth.UserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ClassName: DefaultUserDetailsService
 * Package: com.bryan.template.service.impl
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 18:53
 * Version: v1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {

    private final UserDetailsMapper userDetailsMapper;

    /**
     * loadUserDetail
     *
     * @param username username
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("loadUserByUsername: {}", username);
        return userDetailsMapper.selectById(username);
    }

}
