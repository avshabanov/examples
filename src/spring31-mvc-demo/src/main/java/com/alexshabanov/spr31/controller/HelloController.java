package com.alexshabanov.spr31.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.alexshabanov.spr31.model.Hello;
import com.alexshabanov.spr31.service.HelloService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@Controller
public final class HelloController {
    private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private HelloService helloService;

    @RequestMapping("/index.html")
    public String index(Model model, HttpSession session, HttpServlet servlet) {
        final Hello hello = helloService.getGreeting("index.html");
        model.addAttribute(hello);

        LOG.info("Hello object = {}, sessionId = {}", hello, session.getId());

        return "hello";
    }
}
