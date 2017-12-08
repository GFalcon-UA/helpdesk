package com.javamog.potapov.controller;

import com.javamog.potapov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/page3")
public class page3Controller {

    @Autowired
    private UserService userUserService;

    @RequestMapping(method = RequestMethod.GET)
    public String showHelloPage(Model model) {

        /*User user = userUserService.getUser();
        model.addAttribute("userName", user.getFirstName());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("id", user.getId());*/
        return "page3";
    }

}
