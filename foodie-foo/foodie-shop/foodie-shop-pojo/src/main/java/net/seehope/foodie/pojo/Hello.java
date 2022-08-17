package net.seehope.foodie.pojo;

import org.springframework.stereotype.Component;

/**
 * @author dayday
 * @date 8/2/2022 2:23 PM
 **/
@Component
public class Hello {
    public String sayHello(){
        return "hello maven";
    }
}
