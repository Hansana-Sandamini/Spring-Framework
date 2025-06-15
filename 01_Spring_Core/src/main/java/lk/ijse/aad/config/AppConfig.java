package lk.ijse.aad.config;

import lk.ijse.aad.bean.MyConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="lk.ijse.aad.bean")
//@ComponentScan(basePackageClasses =  SpringBean.class)
//@ComponentScan(basePackageClasses =  TestBean1.class)
public class AppConfig {
    @Bean("hansana")
    MyConnection getConnection(){
        return new MyConnection();
    }
}
