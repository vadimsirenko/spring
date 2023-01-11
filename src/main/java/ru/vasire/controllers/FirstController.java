package ru.vasire.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("first")
public class FirstController {

    @GetMapping("say_hello")
    public String SayHello(){
        return "first/hello";
    }

    @GetMapping("say_goodbye")
    public String SayGoodbye(){
        return "first/goodbye";
    }
}
