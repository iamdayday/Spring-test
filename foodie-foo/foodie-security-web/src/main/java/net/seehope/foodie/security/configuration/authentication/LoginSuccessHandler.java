package net.seehope.foodie.security.configuration.authentication;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.seehope.foodie.common.enums.LoginType;
import net.seehope.foodie.common.properties.ProjectProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dayday
 * @date 8/5/2022 12:25 PM
 **/
@Configuration
@AllArgsConstructor
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private ProjectProperties properties;

    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        if (properties.getFormLogin().getLoginType().equals(LoginType.Json)) {

            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));

        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
