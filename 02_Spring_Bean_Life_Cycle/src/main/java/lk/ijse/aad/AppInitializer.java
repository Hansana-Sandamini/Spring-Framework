package lk.ijse.aad;

import lk.ijse.aad.bean.MyConnection;
import lk.ijse.aad.bean.SpringBean;
import lk.ijse.aad.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

//        SpringBean springBean = context.getBean(SpringBean.class);
//        System.out.println(springBean);

//        MyConnection myConnection = context.getBean(MyConnection.class);
//        System.out.println(myConnection);
//
//        MyConnection myConnection2 = context.getBean(MyConnection.class);
//        System.out.println(myConnection2);


        context.registerShutdownHook();
    }
}
