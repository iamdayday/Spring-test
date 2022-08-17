package net.seehope.foodie.common.validate.generator;

import lombok.Data;
import net.seehope.foodie.common.properties.ProjectProperties;
import net.seehope.foodie.common.validate.code.ValidateCode;
import org.apache.commons.lang.RandomStringUtils;

/**
 * @author dayday
 * @date 8/15/2022 4:13 PM
 * 短信登录
 **/
@Data
public class DefaultMobileValidateCodeGenerator implements MobileValidateCodeGenerator {

    private ProjectProperties properties;

    @Override
    public ValidateCode generator() {
        return new ValidateCode(RandomStringUtils.randomNumeric(properties.getValidateCode().getSmsValidateCode().getLength()),
                properties.getValidateCode().getSmsValidateCode().getExpireIn());
    }
}
