package org.industry.center.auth.service;

import org.industry.common.base.Service;
import org.industry.common.bean.R;
import org.industry.common.dto.UserDto;
import org.industry.common.model.User;

public interface UserService extends Service<User, UserDto> {

    User selectByEmail(String email, boolean isExt);

    User selectByPhone(String phone, boolean isExt);

    boolean resetPassword(String id);

    User selectByName(String phone, boolean isExt);

    Boolean checkUserValid(String name);
}
