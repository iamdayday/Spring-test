package net.seehope.foodie.common.validate.processor;

import net.seehope.foodie.common.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @author dayday
 * @date 8/15/2022 7:17 PM
 **/
public interface ValidateCodeProcessor {

    ValidateCode createValidateCode(ServletWebRequest request) throws IOException;

    void validate(ServletWebRequest request);

    String getCodeType();

    Class getValidateCodeType();

    Boolean isNeedValidate(ServletWebRequest request);
}
