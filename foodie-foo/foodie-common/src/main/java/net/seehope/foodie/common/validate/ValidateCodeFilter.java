package net.seehope.foodie.common.validate;


import lombok.Data;
import net.seehope.foodie.common.exception.ValidateCodeException;
import net.seehope.foodie.common.properties.ProjectConstant;
import net.seehope.foodie.common.properties.ProjectProperties;
import net.seehope.foodie.common.validate.code.ValidateCode;
import net.seehope.foodie.common.validate.processor.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 验证码验证
 * @author dayday
 * @date 8/10/2022 11:13 AM
 **/

@Data
public class ValidateCodeFilter extends OncePerRequestFilter {

    private ProjectProperties properties;
    private SessionStrategy sessionStrategy;
    private AuthenticationFailureHandler authenticationFailureHandler;

    List<ValidateCodeProcessor> validateCodeProcessors;
    private Set<String> processingUrl = new HashSet<>();

    @Override
    public void afterPropertiesSet() {
        List<String> processingUrlList = properties.getValidateCode().getImageValidateCode().getProcessingUrl();
        for (String s : processingUrlList) {
            processingUrl.add(s);
        }
        processingUrl.add(properties.getFormLogin().getLoginProcessingUrl());
        processingUrl.add(ProjectConstant.MOBILE_AUTHENTICATION_PROCESSING_URL);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String action = request.getRequestURI();

        //如果是 需要验证的请求，那么验证，不需要就放行。如果要验证的话，将请求中的参数与session中的参数做对比
        if (processingUrl.contains(action)) {
            try {
                validate(new ServletWebRequest(request, response));
                filterChain.doFilter(request, response);
            } catch (ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
            }

        } else {
            filterChain.doFilter(request, response);
        }
    }

    public void validate(ServletWebRequest request) {

        String validateCodeInRequest = request.getRequest().getParameter(properties.getValidateCode().getValidateCodeParmName());

        ValidateCode validateCodeInSession = (ValidateCode) sessionStrategy.getAttribute(request, ProjectConstant.GENERATE_VALIDATE_CODE_URL);

        if (StringUtils.isBlank(validateCodeInRequest)) {
            throw new ValidateCodeException("请求中未包含验证码");
        }
        if (validateCodeInSession == null) {
            throw new ValidateCodeException("验证码还未生成");
        }
        if (StringUtils.isBlank(validateCodeInSession.getCode())) {
            throw new ValidateCodeException("验证码还未生成");
        }
        if (!StringUtils.equals(validateCodeInSession.getCode(), validateCodeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        if (validateCodeInSession.isExpired()) {
            throw new ValidateCodeException("验证码过期");
        }

        sessionStrategy.removeAttribute(request,ProjectConstant.VALIDATE_CODE_IN_SESSION);
    }
}
