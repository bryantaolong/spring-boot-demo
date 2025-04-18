package com.bryan.springbootdemo.service.auth;

import com.bryan.springbootdemo.model.entity.UserDetails;

/**
 * ClassName: DefaultUserDetailsService
 * Package: com.bryan.template.service
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 18:47
 * Version: v1.0
 */
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username);
}
