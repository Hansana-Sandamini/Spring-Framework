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

//        SpringBean bean = context.getBean(SpringBean.class);
//        bean.testBean();
//        System.out.println(bean);

//        SpringBean bean2 = context.getBean(SpringBean.class);
//        bean2.testBean();
//        System.out.println(bean2);

//        TestBean1 testBean1 = context.getBean(TestBean1.class);
//        System.out.println(testBean1);

//        TestBean2 testBean2 = context.getBean(TestBean2.class);
//        System.out.println(testBean2);

//        context.close();

//        TestBean3 bean3 = context.getBean(TestBean3.class);
//        System.out.println(bean3);

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.out.println("JVM is about to shutdown");
//            context.close();
//        }));

//        request bean from bean ID (Class name -> first letter simple)
//        TestBean1 byBeanID = (TestBean1) context.getBean("testBean1");
//        System.out.println(byBeanID);

//        TestBean1 byBeanID1 = (TestBean1) context.getBean("TestBean1"); //error-first letter must be simple
//        System.out.println(byBeanID1);

//        TestBean2 byBeanID2 = context.getBean("sandamini",TestBean2.class);
//        System.out.println(byBeanID2);

        MyConnection connection = (MyConnection) context.getBean("hansana");
        System.out.println(connection);

        context.registerShutdownHook();
    }
}