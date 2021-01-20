package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("hello")
public class HelloController {

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public void sayHello() {
        System.out.println("Hello, MVC!");
    }

    @RequestMapping(value = "/run", method = RequestMethod.GET)
    public @ResponseBody
    String run() {
        return "Hello, MVC!";
    }

    @RequestMapping(value = "/param", method = RequestMethod.POST)
    public @ResponseBody
    String workWithParams(@RequestParam(value = "name", defaultValue = "Jack") String name) {
        return "You entered ".concat(name);
    }
}
