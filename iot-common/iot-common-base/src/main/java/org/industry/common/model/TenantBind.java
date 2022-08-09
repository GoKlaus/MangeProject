package org.industry.common.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;

import javax.validation.constraints.NotNull;

/**
 * 租户关系表
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantBind extends Description {

    @NotNull(message = "tenant id can't be empty", groups = {Insert.class, Update.class})
    private String tenantId;

    @NotNull(message = "user id can't be empty", groups = {Insert.class, Update.class})
    private String userId;

}
