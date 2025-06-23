package lk.ijse.aad;

import lk.ijse.aad.bean.SpringBean;
import lk.ijse.aad.bean.SpringBeanOne;
import lk.ijse.aad.bean.SpringBeanTwo;
import lk.ijse.aad.config.AppConfig;
import lk.ijse.aad.config.AppConfig1;
import lk.ijse.aad.config.AppConfig2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.register(AppConfig1.class);
//        context.register(AppConfig2.class);

        context.register(AppConfig.class);
        context.refresh();

        SpringBean springBean = context.getBean(SpringBean.class);
        System.out.println(springBean);

        // Configuration Class 1
//        SpringBeanOne bean1 = context.getBean(SpringBeanOne.class);
//        SpringBeanOne bean11 = context.getBean(SpringBeanOne.class);
//        System.out.println(bean1);
//        System.out.println(bean11);

        // Configuration Class 2
//        SpringBeanTwo bean2 = context.getBean(SpringBeanTwo.class);
//        SpringBeanTwo bean22 = context.getBean(SpringBeanTwo.class);
//        System.out.println(bean2);
//        System.out.println(bean22);

        context.registerShutdownHook();
    }
}
