package net.seehope.foodie.common.validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.seehope.foodie.common.properties.ImageValidateCodeProperties;
import net.seehope.foodie.common.properties.ProjectConstant;
import net.seehope.foodie.common.properties.ProjectProperties;
import net.seehope.foodie.common.validate.code.ImageValidateCode;
import net.seehope.foodie.common.validate.code.ValidateCode;
import net.seehope.foodie.common.validate.processor.ValidateCodeProcessor;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * 验证码生成一共就 3个 逻辑
 * 创建
 * 存储   存储到session
 * 发送   到请求端
 *
 * @author dayday
 * @date 8/9/2022 6:34 PM
 **/
@Controller
@RequestMapping(ProjectConstant.GENERATE_VALIDATE_CODE_URL)
@AllArgsConstructor
@Slf4j
@Data
public class ValidateCodeController {

    private ProjectProperties properties;
    private List<ValidateCodeProcessor> validateCodeProcessors;
    private HttpServletRequest request;
    private HttpServletResponse response;


    @GetMapping("/{codeType}")
    public void generatorValidateCode(@PathVariable String codeType) throws IOException {
        for (ValidateCodeProcessor validateCodeProcessor : validateCodeProcessors) {
            if (StringUtils.equals(validateCodeProcessor.getCodeType(), codeType)) {
                validateCodeProcessor.createValidateCode(new ServletWebRequest(request, response));
            }
        }
    }
}
