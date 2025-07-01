package lk.ijse.aad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("one")
public class WildCardMappingController {

//    @GetMapping("test/*/hello")
//    public String sayHello() {
//        return "Hello World";
//    }
    // one/test/hello -> not matching
    // one//hello -> not matching
    // one/123/hello -> not matching
    // one/test/123/hello -> matching


//    @GetMapping("hello/*/*/")
//    public String sayHello2() {
//        return "Hello World 2";
//    }


//    @GetMapping("hello/**/ijse")
//    public String sayHello3() {
//        return "Hello World 3 IJSE";
//    }

}
