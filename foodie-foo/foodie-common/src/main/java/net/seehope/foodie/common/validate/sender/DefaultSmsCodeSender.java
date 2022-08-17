package net.seehope.foodie.common.validate.sender;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.seehope.foodie.common.properties.ProjectConstant;
import net.seehope.foodie.common.properties.ProjectProperties;
import net.seehope.foodie.common.validate.code.ValidateCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @author dayday
 * @date 8/16/2022 12:22 PM
 **/
@Slf4j
@Data
public class DefaultSmsCodeSender implements SmsCodeSender {
    private ProjectProperties properties;

    @Override
    public void send(ServletWebRequest request, ValidateCode validateCode) {
        String mobile = request.getParameter(ProjectConstant.MOBILE_AUTHENTICATION_FORM_USERNAME);
        if (StringUtils.isBlank(mobile)) {
            throw new UsernameNotFoundException("请求中未找到手机号，请重新登录");
        }
        log.warn("默认的短信发送流程没有实现，请用户自行实现SmsCodeSender接口");

        log.info("正在向手机号{}发送短信验证码{}", mobile, validateCode.getCode());
    }
}
