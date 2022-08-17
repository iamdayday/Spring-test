package net.seehope.foodie.common.properties;

import org.mockito.internal.configuration.FieldAnnotationProcessor;

/**
 * 工程常量
 * @author dayday
 * @date 8/8/2022 4:54 PM
 **/
public class ProjectConstant {
    /**
     * 当用户没有权限的时候访问到的路径
     */
    public static final String REQUIRE_AUTHENTICATION_URL = "/authentication/require";

    /**
     * 验证码路经
     */
    public static final String GENERATE_VALIDATE_CODE_URL = "/validateCode";

    /**
     * 前端提交手机短信登录的路经
     */
    public static final String MOBILE_AUTHENTICATION_PROCESSING_URL = "/authentication/mobile";

    public static final String VALIDATE_CODE_IN_SESSION = "VALIDATE_CODE_IN_SESSION";


    /**
     * 短信登陆的时候，表单中的手机号的key
     */
    public static final String MOBILE_AUTHENTICATION_FORM_USERNAME = "mobile";

    /**
     * 短信登陆的时候，表单中的验证码的key
     */
    public static final String MOBILE_AUTHENTICATION_FORM_PASSWORD = "validateCode";
}
