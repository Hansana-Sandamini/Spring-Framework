package lk.ijse.aad.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping()
    public String sayHello() {
        return "Hello World";
    }

    @PostMapping
    public String sayHelloPost() {
        return "Hello Post";
    }

    @PutMapping
    public String sayHelloPut() {
        return "Hello Put";
    }

    @DeleteMapping
    public String sayHelloDelete() {
        return "Hello Delete";
    }

    @PatchMapping
    public String sayHelloPatch() {
        return "Hello Patch";
    }

    @GetMapping("/two")
    public String sayHelloGet() {
        return "Hello Get";
    }

}
