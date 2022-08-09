
package org.industry.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.industry.common.valid.Insert;

import javax.validation.constraints.NotBlank;

/**
 * Rtmp
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Rtmp extends Description {

    @NotBlank(message = "name can't be empty", groups = {Insert.class})
    private String name;

    @NotBlank(message = "rtsp url can't be empty", groups = {Insert.class})
    private String rtspUrl;

    @NotBlank(message = "rtmp url can't be empty", groups = {Insert.class})
    private String rtmpUrl;

    @NotBlank(message = "command can't be empty", groups = {Insert.class})
    private String command;

    private Short videoType;
    private Boolean run;
    private Boolean autoStart;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String tenantId;

    public Rtmp(String id, boolean run) {
        super.setId(id);
        this.run = run;
    }

    public Rtmp(boolean autoStart) {
        this.autoStart = autoStart;
    }

}