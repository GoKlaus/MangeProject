package org.industry.center.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.industry.common.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
