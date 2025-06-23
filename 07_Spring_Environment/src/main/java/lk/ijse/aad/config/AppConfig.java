package lk.ijse.aad.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "lk.ijse.aad.bean")
@PropertySource("classpath:application.properties")
public class AppConfig {
}
