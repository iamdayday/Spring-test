package net.seehope.foodie.common.properties;

import lombok.Data;
import net.seehope.foodie.common.enums.LoginType;

/**
 * @author dayday
 * @date 8/3/2022 9:57 PM
 **/
@Data
public class FormLoginProperties {
    private String loginPage = "/index.html";
    private String loginProcessingUrl = "/authentication/form";
    private String usernameParam = "username";
    private String passwordParam = "password";

    private LoginType loginType = LoginType.REDIRECT;
}
