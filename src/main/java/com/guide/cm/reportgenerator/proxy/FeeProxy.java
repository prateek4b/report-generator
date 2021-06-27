package com.guide.cm.reportgenerator.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "FEE-CALCULATION-SERVICE")
public interface FeeProxy {

    @GetMapping("feeEntry/getAll")
    public List<FeeCalculation> findAllFeeCalculation();
}
