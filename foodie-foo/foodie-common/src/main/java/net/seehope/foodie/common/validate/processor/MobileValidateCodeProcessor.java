package net.seehope.foodie.common.validate.processor;

import lombok.AllArgsConstructor;
import net.seehope.foodie.common.properties.ProjectProperties;
import net.seehope.foodie.common.validate.code.ValidateCode;
import net.seehope.foodie.common.validate.generator.MobileValidateCodeGenerator;
import net.seehope.foodie.common.validate.sender.SmsCodeSender;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @author dayday
 * @date 8/15/2022 7:20 PM
 **/
@Service
@AllArgsConstructor
public class MobileValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode>{
    private ProjectProperties properties;

    private SmsCodeSender smsCodeSender;
    @Override
    public String getCodeType() {
        return properties.getValidateCode().getSmsValidateCode().getCodeType();
    }

    @Override
    public Class getValidateCodeType() {
        return MobileValidateCodeGenerator.class;
    }

    @Override
    public void send(ValidateCode code, ServletWebRequest request) {
        smsCodeSender.send(request,code);
    }
}
