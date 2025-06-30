package lk.ijse.aad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/char")
public class CharacterMappingController {

    //Ihs2
    //I234
    @GetMapping(path = "item/I???")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping(path = "????/search")
    public String sayHelloGet() {
        return "Hello World Search";
    }
}
