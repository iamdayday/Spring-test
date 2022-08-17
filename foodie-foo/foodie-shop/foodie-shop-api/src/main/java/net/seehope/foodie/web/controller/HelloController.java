package net.seehope.foodie.web.controller;

import lombok.AllArgsConstructor;
import net.seehope.foodie.pojo.Hello;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dayday
 * @date 8/2/2022 2:22 PM
 **/
@RestController
@RequestMapping("/hello")
@AllArgsConstructor
public class HelloController {
    private Hello hello;


    @GetMapping
    public String sayHello(){
        return hello.sayHello();
    }
}
