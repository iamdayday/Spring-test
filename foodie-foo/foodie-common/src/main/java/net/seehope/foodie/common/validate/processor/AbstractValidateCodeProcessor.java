package net.seehope.foodie.common.validate.processor;

import net.seehope.foodie.common.properties.ProjectConstant;
import net.seehope.foodie.common.validate.code.ValidateCode;
import net.seehope.foodie.common.validate.generator.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.List;

/**
 * @author dayday
 * @date 8/15/2022 7:19 PM
 **/
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {
    @Autowired
    private List<ValidateCodeGenerator> ValidateCodeGenerators;

    @Autowired
    private SessionStrategy sessionStrategy;

    @Override
    public ValidateCode createValidateCode(ServletWebRequest request) throws IOException {

        C generator = generator();
        save(generator, request);
        send(generator, request);

        return null;
    }

    public C generator() {
        C validate = null;
        for (ValidateCodeGenerator validateCodeGenerator : ValidateCodeGenerators) {
            if (getValidateCodeType().isAssignableFrom(validateCodeGenerator.getClass())) {
                validate = (C) validateCodeGenerator.generator();
            }
        }
        return validate;
    }

    public void save(C code, ServletWebRequest request) {
        sessionStrategy.setAttribute(request, ProjectConstant.VALIDATE_CODE_IN_SESSION,code);
    }

    public abstract void send(C code, ServletWebRequest request) throws IOException;


    @Override
    public void validate(ServletWebRequest request) {

    }

}
