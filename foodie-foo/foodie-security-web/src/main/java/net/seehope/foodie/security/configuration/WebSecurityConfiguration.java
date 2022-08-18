package net.seehope.foodie.security.configuration;

import lombok.AllArgsConstructor;
import net.seehope.foodie.common.properties.FormLoginProperties;
import net.seehope.foodie.common.properties.ProjectConstant;
import net.seehope.foodie.common.properties.ProjectProperties;
import net.seehope.foodie.common.validate.ValidateCodeFilter;
import net.seehope.foodie.common.validate.authentication.MobileAuthenticationConfiguration;
import net.seehope.foodie.common.validate.processor.ValidateCodeProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.web.SessionStrategy;

import java.util.List;

/**
 * @author dayday
 * @date 8/3/2022 2:45 PM
 **/
@Configuration
@AllArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private ProjectProperties properties;
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy;
    private MobileAuthenticationConfiguration mobileAuthenticationConfiguration;

    private List<ValidateCodeProcessor> validateCodeProcessors;

    /**
     * security 认证，授权，攻击防护
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        FormLoginProperties formLogin = properties.getFormLogin();

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        validateCodeFilter.setValidateCodeProcessors(validateCodeProcessors);
        //添加过滤器，在验证username、password之前先验证 验证码是否正确。
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);


        /**
         * 加载短信登录模块
         */
        http.apply(mobileAuthenticationConfiguration);

        //登录认证设置
        http.formLogin()
                .loginPage(ProjectConstant.REQUIRE_AUTHENTICATION_URL)
                .loginProcessingUrl(formLogin.getLoginProcessingUrl())
                .usernameParameter(formLogin.getUsernameParam())
                .passwordParameter(formLogin.getPasswordParam())
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);


        //从yml获取的通过认证的url数组
        List<String> staticPermitUrl = properties.getStaticPermitUrl();
        String[] staticPermitUrls = staticPermitUrl.toArray(new String[staticPermitUrl.size()]);




        //security 认证
        //.and() //返回上一层
        http.authorizeRequests()
                .antMatchers(staticPermitUrls)
                .permitAll()
                .antMatchers(formLogin.getLoginPage(),
                        formLogin.getLoginProcessingUrl(),
                        ProjectConstant.GENERATE_VALIDATE_CODE_URL+"/*",
                        ProjectConstant.REQUIRE_AUTHENTICATION_URL)
                .permitAll()
                .anyRequest()
                .authenticated();

        //security攻击防护.关闭
        http.csrf().disable();
    }
}