package com.guide.cm.reportgenerator.controller;

import com.guide.cm.reportgenerator.proxy.FeeProxy;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ResController {

    @Autowired
    FeeProxy feeProxy;

    @GetMapping("/retryMethod")
    @Retry(name = "retryMethod",fallbackMethod = "fallBackResponse")
    public String sayHello() {
        System.out.println("sayHello called ");
        feeProxy.findAllFeeCalculation();
        return "Hello";
    }

    public String fallBackResponse(Exception e)
    {
        return "fallBackResponse Called";
    }

}
