package net.seehope.foodie.common.configuration;

import lombok.AllArgsConstructor;
import net.seehope.foodie.common.properties.ProjectProperties;
import net.seehope.foodie.common.validate.code.ImageValidateCode;
import net.seehope.foodie.common.validate.generator.DefaultImageValidateCodeGenerator;
import net.seehope.foodie.common.validate.generator.DefaultMobileValidateCodeGenerator;
import net.seehope.foodie.common.validate.generator.ImageValidateCodeGenerator;
import net.seehope.foodie.common.validate.generator.MobileValidateCodeGenerator;
import net.seehope.foodie.common.validate.sender.DefaultSmsCodeSender;
import net.seehope.foodie.common.validate.sender.SmsCodeSender;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;

/**
 * @author dayday
 * @date 8/4/2022 8:24 PM
 **/
@Configuration
@AllArgsConstructor
public class ProjectBeanConfiguration {
    private ProjectProperties properties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RedirectStrategy redirectStrategy() {
        return new DefaultRedirectStrategy();
    }

    @Bean
    public RequestCache requestCache() {
        return new HttpSessionRequestCache();
    }

    @Bean
    public SessionStrategy sessionStrategy() {
        return new HttpSessionSessionStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsService defaultUserDetailService() {
        return new DefaultUserDetailServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(ImageValidateCodeGenerator.class)
    public ImageValidateCodeGenerator imageValidateCodeGenerator() {
        DefaultImageValidateCodeGenerator defaultImageValidateCodeGenerator = new DefaultImageValidateCodeGenerator();
        defaultImageValidateCodeGenerator.setProperties(properties);
        return defaultImageValidateCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(MobileValidateCodeGenerator.class)
    public MobileValidateCodeGenerator mobileValidateCodeGenerator() {
        DefaultMobileValidateCodeGenerator defaultMobileValidateCodeGenerator = new DefaultMobileValidateCodeGenerator();
        defaultMobileValidateCodeGenerator.setProperties(properties);
        return defaultMobileValidateCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        DefaultSmsCodeSender smsCodeSender = new DefaultSmsCodeSender();
        smsCodeSender.setProperties(properties);
        return smsCodeSender;
    }


}
