package com.guide.cm.reportgenerator.reportviews;

import com.guide.cm.reportgenerator.proxy.Client;
import com.guide.cm.reportgenerator.proxy.FeeCalculation;

public class ReportView {

    Client client;
    FeeCalculation feeCalculation;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public FeeCalculation getFeeCalculation() {
        return feeCalculation;
    }

    public void setFeeCalculation(FeeCalculation feeCalculation) {
        this.feeCalculation = feeCalculation;
    }
}
