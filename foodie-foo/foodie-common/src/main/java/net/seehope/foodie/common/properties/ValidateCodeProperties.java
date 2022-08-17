package net.seehope.foodie.common.properties;

import lombok.Data;


/**
 * @author dayday
 * @date 8/9/2022 10:18 PM
 **/
@Data
public class ValidateCodeProperties {
    /**
     * request请求，后端需和前端标签name保持一致。
     */
    private String validateCodeParmName = "validateCode";

    private ImageValidateCodeProperties imageValidateCode = new ImageValidateCodeProperties();

    private SmsValidateCodeProperties smsValidateCode = new SmsValidateCodeProperties();
}
