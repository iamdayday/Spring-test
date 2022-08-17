package net.seehope.foodie.common.validate.processor;

import lombok.AllArgsConstructor;
import net.seehope.foodie.common.properties.ProjectProperties;
import net.seehope.foodie.common.validate.code.ImageValidateCode;
import net.seehope.foodie.common.validate.generator.ImageValidateCodeGenerator;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @author dayday
 * @date 8/15/2022 7:21 PM
 **/
@Service
@AllArgsConstructor
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageValidateCode>{
    private ProjectProperties properties;
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
        ImageIO.write(code.getBufferedImage(),"JPEG",request.getResponse().getOutputStream());
    }
}
