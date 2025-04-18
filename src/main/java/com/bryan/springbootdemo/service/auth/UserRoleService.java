package com.bryan.springbootdemo.service.auth;

import java.util.HashMap;

/**
 * ClassName: UserRoleService
 * Package: com.bryan.template.service
 * Description:
 * Author: Bryan Long
 * Create: 2024/12/29 - 21:59
 * Version: v1.0
 */
public interface UserRoleService {
    HashMap<Integer, String> loadUserRoleMap();

    String loadRoleNameByRoleId(Integer roleId);

    Integer loadRoleIdByRoleName(String roleName);
}
