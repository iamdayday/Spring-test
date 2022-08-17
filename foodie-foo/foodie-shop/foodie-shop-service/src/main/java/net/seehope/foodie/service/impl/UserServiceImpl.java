package net.seehope.foodie.service.impl;

import lombok.AllArgsConstructor;
import net.seehope.foodie.common.properties.ProjectConstant;
import net.seehope.foodie.common.properties.ProjectProperties;
import net.seehope.foodie.mapper.UserMapper;
import net.seehope.foodie.pojo.vo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


/**
 * @author dayday
 * @date 8/3/2022 7:19 PM
 **/
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private UserMapper userMapper;

    private ProjectProperties properties;

    private HttpServletRequest request;

    @Override
    //自己设置UserDetails 将会覆盖 DaoAuthenticationProvider的默认设置。
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;
        String action = request.getRequestURI();

        if (StringUtils.equals(properties.getFormLogin().getLoginProcessingUrl(), action)) {
            user = userMapper.selectByPrimaryKey(username);

        } else if (StringUtils.equals(ProjectConstant.MOBILE_AUTHENTICATION_PROCESSING_URL, action)) {
            User queryUser = new User();
            queryUser.setMobile(username);
            user = userMapper.selectOne(queryUser);

        } else {
            throw new UsernameNotFoundException("登陆方式非法");
        }

        if (user == null) {
            throw new UsernameNotFoundException("用户名为找到");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,ROLE_ADMIN"));


    }

}

