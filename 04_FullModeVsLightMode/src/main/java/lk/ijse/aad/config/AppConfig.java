package lk.ijse.aad.config;

import lk.ijse.aad.bean.SpringBeanOne;
import lk.ijse.aad.bean.SpringBeanTwo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="lk.ijse.aad.bean")
public class AppConfig {
    // Full Mode

//    @Bean
//    public SpringBeanOne springBeanOne() {
//        SpringBeanTwo springBeanTwo1 = springBeanTwo();    //InterBean Dependency
//        SpringBeanTwo springBeanTwo2 = springBeanTwo();
//        System.out.println(springBeanTwo1);
//        System.out.println(springBeanTwo2);
//        return new SpringBeanOne();
//    }
//
//    @Bean
//    public SpringBeanTwo springBeanTwo() {
//        return new SpringBeanTwo();
//    }
}
