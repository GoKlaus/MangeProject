package org.industry.api.auth.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.industry.api.auth.fallback.UserFallback;
import org.industry.common.bean.R;
import org.industry.common.constant.ServiceConstant;
import org.industry.common.dto.UserDto;
import org.industry.common.model.User;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;

@FeignClient(path = ServiceConstant.Auth.TOKEN_URL_PREFIX, name = ServiceConstant.Auth.SERVICE_NAME, fallbackFactory = UserFallback.class)
public interface UserClient {

    /**
     * 新增 User
     *
     * @param user User
     * @return User
     */
    @PostMapping("/add")
    R<User> add(@Validated(Insert.class) @RequestBody User user);

    /**
     * 根据 ID 删除 User
     *
     * @param id User Id
     * @return Boolean
     */
    @PostMapping("/delete/{id}")
    R<Boolean> delete(@NotNull @PathVariable(value = "id") String id);

    /**
     * 修改 User
     * <p>
     * 支  持: Enable,Password
     * 不支持: Name
     *
     * @param user User
     * @return User
     */
    @PostMapping("/update")
    R<User> update(@Validated(Update.class) @RequestBody User user);

    /**
     * 根据 ID 重置 User 密码
     *
     * @param id User Id
     * @return Boolean
     */
    @PostMapping("/reset/{id}")
    R<Boolean> restPassword(@NotNull @PathVariable(value = "id") String id);

    /**
     * 根据 ID 查询 User
     *
     * @param id User Id
     * @return User
     */
    @GetMapping("/id/{id}")
    R<User> selectById(@NotNull @PathVariable(value = "id") String id);

    /**
     * 根据 Name 查询 User
     *
     * @param name User Name
     * @return User
     */
    @GetMapping("/name/{name}")
    R<User> selectByName(@NotNull @PathVariable(value = "name") String name);

    /**
     * 分页查询 User
     *
     * @param userDto Dto
     * @return Page<User>
     */
    @PostMapping("/list")
    R<Page<User>> list(@RequestBody(required = false) UserDto userDto);

    /**
     * 检测用户是否存在
     *
     * @param name User Name
     * @return Boolean
     */
    @GetMapping("/check/{name}")
    R<Boolean> checkUserValid(@NotNull @PathVariable(value = "name") String name);
}
