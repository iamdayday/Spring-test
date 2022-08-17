package net.seehope.foodie.common.configuration;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author dayday
 * @date 8/4/2022 10:34 PM
 **/
public class DefaultUserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("请在业务模块中 实现 UserDetailService 接口，并返回 UserDetails到 security中");
    }
}
