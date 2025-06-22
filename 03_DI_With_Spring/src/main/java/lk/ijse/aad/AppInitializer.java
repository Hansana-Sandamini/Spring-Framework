package lk.ijse.aad;

import lk.ijse.aad.bean.Boy;
import lk.ijse.aad.config.AppConfig;
import lk.ijse.aad.di.Test1;
import lk.ijse.aad.di.Test2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

//        Boy boy = context.getBean(Boy.class);
//        System.out.println(boy);
//        boy.chatWithGirl();

        Test2 test2 = context.getBean(Test2.class);
        test2.calledHelloMethod();

        context.registerShutdownHook();
    }
}
