package org.mardon.spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1")
public class GreetingController {

//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public ModelAndView hello(ModelAndView hello){
        hello.setViewName("greeting/hello");
        return hello;
    }

    @RequestMapping(value = "/bye", method = RequestMethod.GET)
    public ModelAndView bye(ModelAndView bye){
        bye.setViewName("greeting/bye");
        return bye;
    }
}
