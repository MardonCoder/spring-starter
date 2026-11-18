package org.mardon.spring.http.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1")
public class GreetingController {

//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello/{id}")
    public ModelAndView hello(ModelAndView hello, HttpServletRequest request,
                              @RequestParam Integer age,
                              @RequestHeader String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable Integer id){
//        var ageParamValue = request.getParameter("age");
//        var acceptHeader = request.getHeader("accept");
//        var cookies = request.getCookies();

        hello.setViewName("greeting/hello");
        return hello;
    }

    @RequestMapping(value = "/bye", method = RequestMethod.GET)
    public ModelAndView bye(ModelAndView bye){
        bye.setViewName("greeting/bye");
        return bye;
    }
}
