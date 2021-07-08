package com.guide.cm.reportgenerator.controller;

import com.guide.cm.reportgenerator.proxy.Proxy;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResController {

    @Autowired
    Proxy proxy;

    @GetMapping("/retryMethod")
    @Retry(name = "retryMethod",fallbackMethod = "fallBackResponse")
    public String sayHello() {
        System.out.println("sayHello called ");
        proxy.findAllFeeCalculation();
        return "Hello";
    }

    public String fallBackResponse(Exception e)
    {
        return "fallBackResponse Called";
    }

}
