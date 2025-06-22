package lk.ijse.aad.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Girl2 implements Agreement{

    @Override
    public void chat() {
        System.out.println("Girl 2 Chatting...");
    }

    public Girl2() {
        System.out.println("Girl Constructor");
    }
}
