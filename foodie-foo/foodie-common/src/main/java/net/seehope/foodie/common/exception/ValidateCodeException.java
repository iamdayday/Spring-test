package net.seehope.foodie.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author dayday
 * @date 8/10/2022 5:15 PM
 **/
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}