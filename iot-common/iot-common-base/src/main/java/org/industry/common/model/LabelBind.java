package org.industry.common.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.valid.Insert;
import org.industry.common.valid.Update;

import javax.validation.constraints.NotNull;

/**
 * 标签关系表
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LabelBind extends Description {

    @NotNull(message = "label id can't be empty", groups = {Insert.class, Update.class})
    private String labelId;

    @NotNull(message = "entity id can't be empty", groups = {Insert.class, Update.class})
    private String entityId;

    private String type;
}
