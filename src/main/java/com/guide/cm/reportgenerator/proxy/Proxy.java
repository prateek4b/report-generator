package com.guide.cm.reportgenerator.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "API-GATEWAY")
public interface Proxy {

    @GetMapping("client/findAll")
    public List<Client> findAllClients();

    @GetMapping("fee/feeEntry/getAll")
    public List<FeeCalculation> findAllFeeCalculation();
}
