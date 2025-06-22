package lk.ijse.aad.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test2 implements TestDI {

//    property injection / field injection
//    @Autowired
//    DI test1;
//
//    public Test2(){
//        System.out.println("Test2 Constructor Called");
//    }
//
//    public void calledHelloMethod(){
//        test1.sayHello();
//    }



//    constructor through injection
//    DI test1;
//
//    @Autowired   // optional
//    public Test2(DI t1) {
//        this.test1 = t1;
//    }
//
//    public void  calledHelloMethod(){
//        test1.sayHello();
//    }



//    Setter method through injection
//    DI test1;
//
//    @Autowired
//    public void settest1(DI t1) {
//        this.test1 = t1;
//    }
//
//    public void calledHelloMethod(){
//        test1.sayHello();
//    }



//    Interface through injection
    DI test1;

    @Autowired
    @Override
    public void inject(DI test1) {
        this.test1 = test1;
    }

    public void calledHelloMethod(){
        test1.sayHello();
    }


}
