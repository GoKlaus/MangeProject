package org.industry.common.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.valid.Insert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Ip 黑名单表
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BlackIp extends Description {

    @NotBlank(message = "Ip can't be empty",
            groups = {Insert.class})
    @Pattern(message = "Invalid ip , /^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$/",
            regexp = "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$",
            groups = {Insert.class})
    private String ip;
    private Boolean enable;
}
