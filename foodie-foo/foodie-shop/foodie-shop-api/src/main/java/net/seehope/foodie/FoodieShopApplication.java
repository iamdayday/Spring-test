package net.seehope.foodie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author dayday
 * @date 8/2/2022 2:20 PM
 **/
@SpringBootApplication
@ComponentScan(basePackages= {"net.seehope"})
@MapperScan("net.seehope.**.mapper")
@EnableScheduling 
@EnableAsync
public class FoodieShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodieShopApplication.class,args);
    }
}
