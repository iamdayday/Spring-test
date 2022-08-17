package net.seehope.foodie.common.validate.sender;

import net.seehope.foodie.common.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author dayday
 * @date 8/16/2022 12:21 PM
 **/
public interface SmsCodeSender {
   void send(ServletWebRequest request, ValidateCode validateCode);
}
