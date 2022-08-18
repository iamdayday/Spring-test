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
 *
 * @author dayday
 * @date 8/10/2022 11:13 AM
 **/

@Data
public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;
    List<ValidateCodeProcessor> validateCodeProcessors;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request, response);

        for (ValidateCodeProcessor validateCodeProcessor : validateCodeProcessors) {
            if (validateCodeProcessor.isNeedValidate(servletWebRequest)) {
                try {
                    validateCodeProcessor.validate(servletWebRequest);
                    filterChain.doFilter(request, response);
                    return;
                } catch (ValidateCodeException e) {
                    authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
