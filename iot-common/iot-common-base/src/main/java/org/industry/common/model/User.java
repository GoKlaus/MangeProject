package org.industry.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.valid.Auth;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends Description {

    @NotBlank(message = "Name can't be empty", groups = {Insert.class, Auth.class})
    @Pattern(regexp = "^[a-zA-Z]\\w{2,15}$", message = "Invalid name , /^[a-zA-Z]\\w{2,15}$/", groups = {Insert.class})
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "Invalid phone , /^[1]([3-9])[0-9]{9}$/", groups = {Insert.class, Update.class})
    private String phone;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Pattern(regexp = "^([a-zA-Z0-9_\\.-])+@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$", message = "Invalid email , /^([a-zA-Z0-9_\\.-])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$/", groups = {Insert.class, Update.class})
    private String email;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @NotBlank(message = "Password can't be empty", groups = {Insert.class, Auth.class})
    @Pattern(regexp = "^[a-zA-Z]\\w{7,15}$", message = "Invalid password , /^[a-zA-Z]\\w{7,15}$/", groups = {Insert.class, Update.class})
    private String password;

    private Boolean enable;

}
