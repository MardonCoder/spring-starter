package org.mardon.spring.http.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mardon.spring.database.entity.Role;
import org.mardon.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @ModelAttribute("roles")
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping("/hello")
    public String hello(Model model,
                        HttpServletRequest request,
                        @ModelAttribute("userReadDto") UserReadDto userReadDto){

        model.addAttribute("user", new UserReadDto(1L, "Ivan"));
        return "greeting/hello";
    }

    // после запуска hello, атрибут user перейдёт в сессию из-за аннотации над классом и bye получит к нему доступ
    @RequestMapping(value = "/bye", method = RequestMethod.GET)
    public String bye(@SessionAttribute("user") UserReadDto user, Model model){
        return "greeting/bye";
    }

    //    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello/{id}")
    public ModelAndView hello2(ModelAndView hello, HttpServletRequest request,
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
}
