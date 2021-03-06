package com.alexshabanov.springjmsdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.jms.Message;


/**
 * Represents sample service.
 */
@Service
public final class HelloService {
    private final Logger log = LoggerFactory.getLogger(HelloService.class);

    public int add(int a, int b) {
        log.info("adding {} + {}", a, b);
        return a + b;
    }
}
