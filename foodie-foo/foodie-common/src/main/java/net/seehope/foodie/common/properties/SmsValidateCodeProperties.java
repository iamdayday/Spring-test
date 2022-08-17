package net.seehope.foodie.common.properties;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dayday
 * @date 8/12/2022 7:16 PM
 **/
@Data
public class SmsValidateCodeProperties {
    private Integer length = 4;
    private Integer expireIn = 60 * 15;

    private String codeType = "sms";

    /**
     * 以后再别的业务场景中，也有可能会用到短信业务逻辑
     */
    private List<String> processingUrl = new ArrayList<>();
}
