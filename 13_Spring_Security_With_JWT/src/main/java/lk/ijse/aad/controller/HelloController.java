package lk.ijse.aad.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String helloAdmin(){
        return "Hello Admin";
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String helloUser(){
        return "Hello User";
    }

}
