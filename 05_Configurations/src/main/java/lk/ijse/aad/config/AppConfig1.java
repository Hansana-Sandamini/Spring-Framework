package lk.ijse.aad.config;

import lk.ijse.aad.bean.BeanA;
import lk.ijse.aad.bean.BeanB;
import lk.ijse.aad.bean.SpringBeanOne;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@Import(AppConfig2.class)
public class AppConfig1 {

//    @Bean
//    public SpringBeanOne springBeanOne(){
//        return new SpringBeanOne();
//    }

    @Bean
    public BeanA beanA(){
        return new BeanA();
    }

    @Bean
    public BeanB beanB(){
        return new BeanB();
    }
}
