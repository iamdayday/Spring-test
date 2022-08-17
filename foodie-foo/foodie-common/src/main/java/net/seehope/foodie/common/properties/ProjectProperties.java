package net.seehope.foodie.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 工程变量
 * @author dayday
 * @date 8/3/2022 9:51 PM
 **/
@Configuration
@ConfigurationProperties(prefix = "net.seehope")
@Data
public class ProjectProperties {
    private FormLoginProperties formLogin = new FormLoginProperties();
    private List<String> staticPermitUrl = new ArrayList<>();
    private ValidateCodeProperties validateCode = new ValidateCodeProperties();
    private ImageValidateCodeProperties imageValidateCode = new ImageValidateCodeProperties();
}