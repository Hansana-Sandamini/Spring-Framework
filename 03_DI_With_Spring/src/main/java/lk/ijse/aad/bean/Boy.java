package lk.ijse.aad.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Boy {

//    Property Injection
//    Girl girl = new Girl();
//
//    public void chatWithGirl() {
//        girl.chat();
//    }


//    Constructor through injection
//    Girl girl;
//
//    public Boy(Girl girl) {
//        this.girl = girl;
//    }
//
//    public void chatWithGirl() {
//        Boy boy = new Boy(new Girl());
//        boy.chat();
//    }


//    Setter Method through injection
//    Girl girl;
//
//    public void setterMethod(Girl girl) {
//        this.girl = girl;
//    }
//
//    public void chatWithGirl() {
//        Boy boy = new Boy();
//        boy.setterMethod(new Girl());
//        boy.chat();
//    }


    @Autowired
    @Qualifier("girl2")
    Agreement girl;

    public Boy() {
        System.out.println("Boy Constructor");
    }

    public void chatWithGirl() {
        girl.chat();
    }
}






