package com.guide.cm.reportgenerator.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="USER-PROFILE-SERVICE")
public interface UserProfileProxy {

    @GetMapping("/findAll")
    public List<Client> findAllClients();

}
