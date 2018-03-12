package com.yyc.webpro.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class testcontroller {

    @RequestMapping("/world")
    @ResponseBody
    public String world(){
        return "hello--------------";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String testHot() {
        return "on hot deploy zzzzzzzzzzzz";
    }
}
