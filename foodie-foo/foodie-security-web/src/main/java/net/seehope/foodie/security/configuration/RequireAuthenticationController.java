package net.seehope.foodie.security.configuration;

import lombok.AllArgsConstructor;
import net.seehope.foodie.common.enums.LoginType;
import net.seehope.foodie.common.properties.ProjectConstant;
import net.seehope.foodie.common.properties.ProjectProperties;
import net.seehope.foodie.common.util.JsonResult;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dayday
 * @date 8/8/2022 4:48 PM
 **/
@Controller
@AllArgsConstructor
@RequestMapping(ProjectConstant.REQUIRE_AUTHENTICATION_URL)
public class RequireAuthenticationController {

    private ProjectProperties properties;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private RedirectStrategy redirectStrategy;

    @RequestMapping(produces = "text/html")
    public void errorHtml() throws IOException {
        redirectStrategy.sendRedirect(request,response,properties.getFormLogin().getLoginPage());
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @RequestMapping
    public JsonResult error(HttpServletRequest request) throws IOException {
        return  JsonResult.errorAuthorize("当前用户没有权限访问该资源", null);
    }
}