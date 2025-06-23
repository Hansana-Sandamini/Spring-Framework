package lk.ijse.aad.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements InitializingBean {
    @Value("sandamini")
    private String name;

    public SpringBean() {
        System.out.println(name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(name);
    }



//    public SpringBean() {}
//
//    @Autowired(required = false)
//    public SpringBean(@Value("sandamini") String name, @Value("10") int number, @Value("true") boolean flag) {
//        System.out.println("SpringBean Constructor");
//        System.out.println(name);
//        System.out.println(number);
//        System.out.println(flag);
//    }
//
//    @Autowired(required = false)
//    public SpringBean(@Value("sandamini") String name, @Value("10") int number) {
//        System.out.println("SpringBean Constructor");
//        System.out.println(name);
//        System.out.println(number);
//    }

}
