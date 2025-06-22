package lk.ijse.aad.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Girl1 implements Agreement {
//    public void chat() {
//        System.out.println("Chatting...");
//    }

    public Girl1() {
        System.out.println("Girl Constructor");
    }


    @Override
    public void chat() {
        System.out.println("Girl 1 Chatting...");
    }
}
