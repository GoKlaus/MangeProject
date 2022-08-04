package org.industry.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.industry.common.valid.Auth;
import org.industry.common.valid.Check;
import org.industry.common.valid.Update;

import javax.validation.constraints.NotBlank;

/**
 * Login
 *
 * @author pnoker
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Login {

    @NotBlank(message = "Tenant can't be empty", groups = {Auth.class})
    private String tenant;

    @NotBlank(message = "Name can't be empty", groups = {Check.class, Auth.class, Update.class})
    private String name;

    @NotBlank(message = "Salt can't be empty", groups = {Check.class, Auth.class})
    private String salt;

    @NotBlank(message = "Password can't be empty", groups = {Auth.class})
    private String password;

    @NotBlank(message = "Token can't be empty", groups = {Check.class})
    private String token;

}
