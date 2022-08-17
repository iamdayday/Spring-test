package net.seehope.foodie.security.configuration.authentication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.seehope.foodie.common.enums.LoginType;
import net.seehope.foodie.common.properties.ProjectProperties;
import net.seehope.foodie.common.util.JsonResult;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dayday
 * @date 8/5/2022 12:26 PM
 **/
@Configuration
@Slf4j
@AllArgsConstructor
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private ProjectProperties properties;
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (properties.getFormLogin().getLoginType().equals(LoginType.Json)) {

            response.setContentType("application/json;charset=utf-8");
            log.error(exception.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(objectMapper.writeValueAsString(JsonResult.errorParams("登陆失败",exception.getMessage())));

        } else {
            super.onAuthenticationFailure(request,response,exception);
        }
    }
}