package org.industry.center.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.industry.center.auth.mapper.UserMapper;
import org.industry.center.auth.service.UserService;
import org.industry.common.bean.Pages;
import org.industry.common.constant.CommonConstant;
import org.industry.common.dto.UserDto;
import org.industry.common.exception.*;
import org.industry.common.model.User;
import org.industry.common.utils.IotUtil;
import org.industry.core.annotation.Logs;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Logs("Add user")
    @Transactional
    @Override
    public User add(User user) {
        // 判断用户是否存在
        User selectByName = selectByName(user.getName(), false);
        if (ObjectUtil.isNotNull(selectByName)) {
            throw new DuplicateException("The user already exists with username: {}", user.getName());
        }

        // 判断 phone 是否存在，如果有 phone 不为空，检查该 phone 是否被占用
        if (StrUtil.isNotEmpty(user.getPhone())) {
            User selectByPhone = selectByPhone(user.getPhone(), false);
            if (ObjectUtil.isNotNull(selectByPhone)) {
                throw new DuplicateException("The user already exists with phone: {}", user.getPhone());
            }
        }

        // 判断 email 是否存在，如果有 email 不为空，检查该 email 是否被占用
        if (StrUtil.isNotEmpty(user.getEmail())) {
            User selectByEmail = selectByEmail(user.getEmail(), false);
            if (ObjectUtil.isNotNull(selectByEmail)) {
                throw new DuplicateException("The user already exists with email: {}", user.getEmail());
            }
        }

        // 插入 user 数据，并返回插入后的 user
        if (userMapper.insert(user.setPassword(IotUtil.md5(user.getPassword()))) > 0) {
            return userMapper.selectById(user.getId());
        }

        throw new AddException("user add failed: {}", user.toString());
    }

    @Override
    public boolean delete(String id) {
        User user = selectById(id);
        if (null == user) {
            throw new NotFoundException("user doesn't exists");
        }
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public User update(User user) {
        User select = selectById(user.getId());
        if (null == select) {
            throw new NotFoundException("user doesn't exists");
        }
        // 电话合法判断
        if (StrUtil.isNotBlank(user.getPhone())) {
            if (null == select.getPhone() || !select.getPhone().equals(user.getPhone())) {
                //noinspection ResultOfMethodCallIgnored
                Optional.of(selectByPhone(user.getPhone(), false))
                        .orElseThrow(() -> new DuplicateException("The user already exists with phone {}", user.getPhone()));
            }
        } else {
            user.setPhone(null);
        }

        if (StrUtil.isNotBlank(user.getEmail())) {
            if (null == select.getEmail() || !select.getEmail().equals(user.getEmail())) {
                //noinspection ResultOfMethodCallIgnored
                Optional.of(selectByEmail(user.getEmail(), false))
                        .orElseThrow(() -> new DuplicateException("The user already exists with email {}", user.getEmail()));
            }
        } else {
            user.setEmail(null);
        }

        user.setName(null).setUpdateTime(null);
        if (userMapper.updateById(user) > 0) {
            User result = userMapper.selectById(user.getId());
            user.setName(result.getName());
            return result;
        }
        throw new ServiceException("user update failed");
    }

    @Override
    public User selectByEmail(String email, boolean isExt) {
        if (StrUtil.isEmpty(email)) {
            if (isExt) {
                throw new EmptyException("The phone is empty");
            }
            return null;
        }

        return selectByKey(User::getEmail, email, isExt);
    }

    @Override
    public User selectByPhone(String phone, boolean isExt) {
        if (StrUtil.isEmpty(phone)) {
            if (isExt) {
                throw new EmptyException("The phone is empty");
            }
            return null;
        }

        return selectByKey(User::getPhone, phone, isExt);
    }

    private User selectByKey(SFunction<User, ?> key, String value, boolean isExt) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>query().lambda();
        queryWrapper.eq(key, value);
        User user = userMapper.selectOne(queryWrapper);
        if (ObjectUtil.isNull(user)) {
            if (isExt) {
                throw new NotFoundException("The user does not exist");
            }
            return null;
        }
        return user;
    }

    @Override
    public User selectById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public Page<User> list(UserDto userDto) {
        if (!Optional.ofNullable(userDto.getPage()).isPresent()) {
            userDto.setPage(new Pages());
        }
        return userMapper.selectPage(userDto.getPage().convert(), fuzzyQuery(userDto));
    }

    @Override
    public LambdaQueryWrapper<User> fuzzyQuery(UserDto userDto) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>query().lambda();
        if (null != userDto) {
            if (StrUtil.isNotBlank(userDto.getName())) {
                queryWrapper.like(User::getName, userDto.getName());
            }
        }
        return queryWrapper;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean resetPassword(String id) {
        User user = selectById(id);
        if (null != user) {
            user.setPassword(IotUtil.md5(CommonConstant.Algorithm.DEFAULT_PASSWORD));
            return null != update(user);
        }
        return false;
    }

    /**
     * @param name
     * @return
     */
    @Override
    public User selectByName(String name, boolean isExt) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>query().lambda();
        wrapper.eq(User::getName, name);

        return userMapper.selectOne(wrapper);
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Boolean checkUserValid(String name) {
        User user = selectByName(name, false);
        if (null != user) {
            return user.getEnable();
        }

        user = selectByPhone(name, false);
        if (null != user) {
            return user.getEnable();
        }

        user = selectByEmail(name, false);
        if (null != user) {
            return user.getEnable();
        }

        return false;
    }
}
