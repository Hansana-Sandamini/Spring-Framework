package lk.ijse.aad.config;

import lk.ijse.aad.bean.BeanC;
import lk.ijse.aad.bean.BeanD;
import lk.ijse.aad.bean.SpringBeanTwo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig2 {
//    @Bean
//    public SpringBeanTwo springBeanTwo(){
//        return new SpringBeanTwo();
//    }

    @Bean
    public BeanC beanC(){
        return new BeanC();
    }

    @Bean
    public BeanD beanD(){
        return new BeanD();
    }
}
