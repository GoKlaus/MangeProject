package org.industry.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.industry.common.constant.CommonConstant;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean ok = false;

    private int code = Code.OK.getCode();

    private String message = CommonConstant.Response.ERROR;


    private enum Code {
        OK(200), FAILURE(500), NOTFOUND(3404);

        @Getter
        private int code;

        Code(int code) {
            this.code = code;
        }
    }
}
