package net.seehope.foodie.common.validate.authentication;

import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author dayday
 * @date 8/13/2022 2:18 PM
 **/
@Data
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    /**
     * 方法的轮子： 接口AuthenticationProvider --> AbstractUserDetailsAuthenticationProvider.java 中 来
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());

        MobileAuthenticationToken result = new MobileAuthenticationToken(
                authentication.getPrincipal(), authentication.getCredentials(),
                authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));
        result.setDetails(authentication.getDetails());

        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (MobileAuthenticationProvider.class
                .isAssignableFrom(authentication));
    }
}
