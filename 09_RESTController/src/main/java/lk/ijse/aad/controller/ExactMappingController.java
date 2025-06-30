package lk.ijse.aad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exact")
public class ExactMappingController {

    @GetMapping(path = "/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping(path = "/hello/two")
    public String sayHelloTwo() {
        return "Hello World Two";
    }

    @GetMapping(path = "/hello/two/api")
    public String sayHelloTwoApi() {
        return "Hello World Two API";
    }
}
