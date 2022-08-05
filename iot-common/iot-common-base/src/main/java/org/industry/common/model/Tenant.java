package org.industry.common.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.valid.Auth;
import org.industry.common.valid.Insert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * User
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Tenant extends Description {

    @NotBlank(message = "Name can't be empty", groups = {Insert.class, Auth.class})
    @Pattern(regexp = "^[a-zA-Z]\\w{2,15}$", message = "Invalid name , /^[a-zA-Z]\\w{2,15}$/", groups = {Insert.class})
    private String name;

    private Boolean enable;
}
