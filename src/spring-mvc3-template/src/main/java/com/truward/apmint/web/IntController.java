package com.truward.apmint.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Properties;

@Controller
public class IntController {

    @Autowired
    private Properties appProperties;

    @RequestMapping("/hello")
    public ModelAndView helloWorld() {
        return new ModelAndView("hello", "message", appProperties.getProperty("helloMessage"));
    }
}
