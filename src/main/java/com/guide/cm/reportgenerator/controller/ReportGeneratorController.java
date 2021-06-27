package com.guide.cm.reportgenerator.controller;

import com.guide.cm.reportgenerator.reportviews.ReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guide.cm.reportgenerator.proxy.Client;
import com.guide.cm.reportgenerator.proxy.FeeCalculation;
import com.guide.cm.reportgenerator.proxy.FeeProxy;
import com.guide.cm.reportgenerator.proxy.UserProfileProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ReportGeneratorController {

    @Autowired
    FeeProxy feeProxy;

    @Autowired
    UserProfileProxy userProfileProxy;

    @GetMapping("/reportViewDetailed")
    public List<ReportView> getReportViewWithAllDetails() {
        List<ReportView> reportViews = new ArrayList<>();

        List<FeeCalculation> feeCalculationList = feeProxy.findAllFeeCalculation();
        List<Client> clients = userProfileProxy.findAllClients();

        Map<String, FeeCalculation> feeCalculationMap = feeCalculationList.stream().collect(Collectors.toMap(x -> x.getGstNo(), x -> x));
        Map<String, Client> clientMap = clients.stream().collect(Collectors.toMap(x -> x.getGstNo(), x -> x));

        for (Client client : clients) {
            ReportView reportView = new ReportView();
            reportView.setClient(client);
            reportView.setFeeCalculation(feeCalculationMap.get(client.getGstNo()));
            reportViews.add(reportView);
        }

        return reportViews;
    }

}
