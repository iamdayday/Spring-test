package net.seehope.foodie.common.validate.processor;

import net.seehope.foodie.common.properties.ProjectConstant;
import net.seehope.foodie.common.properties.ProjectProperties;
import net.seehope.foodie.common.validate.code.ValidateCode;
import net.seehope.foodie.common.validate.generator.MobileValidateCodeGenerator;
import net.seehope.foodie.common.validate.sender.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author dayday
 * @date 8/15/2022 7:20 PM
 **/
@Service

public class MobileValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
    @Autowired
    private ProjectProperties properties;
    @Autowired
    private SmsCodeSender smsCodeSender;

    private Set<String> processingUrlSet = new HashSet<>();

    @PostConstruct
    public void init() {
        List<String> processingUrl = properties.getValidateCode().getSmsValidateCode().getProcessingUrl();

        for (String s : processingUrl) {
            processingUrl.add(s);
        }
        processingUrl.add(ProjectConstant.MOBILE_AUTHENTICATION_PROCESSING_URL);
    }

    @Override
    public Boolean isNeedValidate(ServletWebRequest request) {
        return processingUrlSet.contains(request.getRequest().getRequestURL());
    }

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
        smsCodeSender.send(request, code);
    }
}
