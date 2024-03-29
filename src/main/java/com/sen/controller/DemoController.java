package com.sen.controller;

import com.sen.service.DemoService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j
@Controller
public class DemoController {

    // == fields ==
    private final DemoService demoService;

    // == constructors ==
    @Autowired
    public DemoController(DemoService demoService){
        this.demoService = demoService;
    }

    // == request methods ==
    // http://localhost:8080/todo-list/hello
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    // http://localhost:8080/todo-list/welcome
    // prefix + name + suffix  =>  /WEB-INF/view/welcome.jsp (by viewResolver)
    @GetMapping("welcome")
    public String welcome(Model model){
        model.addAttribute("helloMessage", demoService.getHelloMessage("Tim"));
        log.info("model = " + model);
        return "welcome";  // return logical view name
    }

    // == model attribute ==
    @ModelAttribute("welcomeMessage")
    public String welcomeMessage(){
        log.info("welcomeMessage() called");
        return demoService.getWelcomeMessage();
    }
}
