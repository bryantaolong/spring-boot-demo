package com.bryan.springbootdemo.service.auth.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bryan.springbootdemo.model.entity.UserRole;
import com.bryan.springbootdemo.mapper.UserRoleMapper;
import com.bryan.springbootdemo.service.auth.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 类名: DefaultUserRoleService
 * 包名: com.bryan.springbootdemo.service.auth.impl
 * 描述: 用户角色服务实现类，提供了加载用户角色映射和根据角色 ID 或名称获取角色信息的功能。
 * 作者: Bryan Long
 * 创建时间: 2024/12/29 - 22:01
 * 版本: v1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultUserRoleService implements UserRoleService {

    // 用户角色映射器
    private final UserRoleMapper userRoleMapper;

    /**
     * 加载用户角色映射，将所有角色信息以角色 ID 为键、角色名称为值存储到一个 HashMap 中。
     * 此方法使用缓存来提高性能，缓存的 key 为 'userRoleMap'。
     *
     * @return 包含角色信息的 HashMap
     */
    @Override
    @Cacheable(value = "userRoleCache", key = "'userRoleMap'")
    public HashMap<Integer, String> loadUserRoleMap() {
        log.info("DefaultUserRoleService.loadUserRoleMap");

        HashMap<Integer, String> roleMap = new HashMap<>();
        List<UserRole> roleList = userRoleMapper.selectList(null);

        for (UserRole role : roleList) {
            roleMap.put(role.getId(), role.getRoleName());
        }

        return roleMap;
    }

    /**
     * 根据角色 ID 加载角色信息。
     * 此方法使用缓存来提高性能，缓存的 key 为 'roleId:' + 角色 ID，除非结果为 null，否则将缓存结果。
     *
     * @param roleId 角色 ID
     * @return 对应的用户角色对象
     */
    @Override
    @Cacheable(value = "userRoleCache", key = "'roleId:' + #roleId", unless = "#result == null")
    public String loadRoleNameByRoleId(Integer roleId) {
        log.info("DefaultUserRoleService.loadRoleNameByRoleId: {}", roleId);
        return userRoleMapper.selectById(roleId).getRoleName();
    }

    /**
     * 根据角色名称加载角色 ID。
     * 此方法使用缓存来提高性能，缓存的 key 为 'roleName:' + 角色名称，除非结果为 null，否则将缓存结果。
     *
     * @param roleName 角色名称
     * @return 对应的角色 ID
     */
    @Override
    @Cacheable(value = "userRoleCache", key = "'roleName:' + #roleName", unless = "#result == null")
    public Integer loadRoleIdByRoleName(String roleName) {
        log.info("DefaultUserRoleService.fetchRoleIdByRoleName: {}", roleName);

        QueryWrapper<UserRole> wrapper = new QueryWrapper<UserRole>().eq("roleName", roleName);
        return userRoleMapper.selectOne(wrapper).getId();
    }
}
