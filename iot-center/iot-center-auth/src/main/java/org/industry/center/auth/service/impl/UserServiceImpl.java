package org.industry.center.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.mapper.UserMapper;
import org.industry.center.auth.service.UserService;
import org.industry.common.dto.UserDto;
import org.industry.common.model.User;
import org.industry.core.annotation.Logs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Logs("Add user")
    @Transactional
    @Override
    public User add(User type) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public User update(User type) {
        return null;
    }

    @Override
    public User selectById(String id) {
        return null;
    }

    @Override
    public Page<User> list(UserDto dto) {
        return null;
    }

    @Override
    public LambdaQueryWrapper<User> fuzzyQuery(UserDto dto) {
        return null;
    }
}
