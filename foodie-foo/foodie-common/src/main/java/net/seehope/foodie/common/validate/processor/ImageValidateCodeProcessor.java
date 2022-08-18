package net.seehope.foodie.common.validate.processor;

import net.seehope.foodie.common.properties.ProjectProperties;
import net.seehope.foodie.common.validate.code.ImageValidateCode;
import net.seehope.foodie.common.validate.generator.ImageValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dayday
 * @date 8/15/2022 7:21 PM
 **/
@Service

public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageValidateCode> {
    @Autowired
    private ProjectProperties properties;

    private Set<String> processingUrlSet = new HashSet<>();

    @PostConstruct
    public void init(){
        List<String> processingUrl = properties.getValidateCode().getImageValidateCode().getProcessingUrl();
        properties.getFormLogin().getLoginProcessingUrl();
        for (String s : processingUrl) {
            processingUrl.add(s);
        }
        processingUrl.add(properties.getFormLogin().getLoginProcessingUrl());
    }

    @Override
    public Boolean isNeedValidate(ServletWebRequest request) {
        return processingUrlSet.contains(request.getRequest().getRequestURL());
    }

    @Override
    public String getCodeType() {
        return properties.getImageValidateCode().getCodeType();
    }

    @Override
    public Class getValidateCodeType() {
        return ImageValidateCodeGenerator.class;
    }

    @Override
    public void send(ImageValidateCode code, ServletWebRequest request) throws IOException {
        ImageIO.write(code.getBufferedImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
