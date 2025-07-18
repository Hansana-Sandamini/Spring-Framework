package lk.ijse.aad.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = {"lk.ijse.aad.bean", "lk.ijse.aad.controller"})
@EnableWebMvc
public class WebAppConfig implements WebMvcConfigurer {
}
