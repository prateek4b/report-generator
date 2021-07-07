package com.guide.cm.reportgenerator.mapper;

import com.guide.cm.reportgenerator.proxy.Client;
import com.guide.cm.reportgenerator.proxy.FeeEntry;
import com.guide.cm.reportgenerator.reportviews.AllReportFeesAndTypeView;
import com.guide.cm.reportgenerator.reportviews.AllReportFeesView;
import com.guide.cm.reportgenerator.reportviews.ReportView;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ReportViewToAllReportFeesAndTypeView {

    public AllReportFeesAndTypeView convert(ReportView reportView) throws IllegalAccessException {
        AllReportFeesAndTypeView allReportFeesAndTypeView = new AllReportFeesAndTypeView();
        populateFromClientInfo(allReportFeesAndTypeView, reportView.getClient());

        for (FeeEntry feeEntry : reportView.getFeeCalculation().getFeeEntries()) {
            String feeHead = feeEntry.getFeeHead();
            long fees = feeEntry.getFees();
            findAndPopulateFeesAndType(feeHead, fees,feeEntry.getFeeType(), allReportFeesAndTypeView);
        }

        allReportFeesAndTypeView.evaluateTotalFees();
        return allReportFeesAndTypeView;

    }

    private void findAndPopulateFeesAndType(String feeHead, long fees, String feeType, AllReportFeesAndTypeView allReportFeesAndTypeView) throws IllegalAccessException {

        Field feesField = Arrays.stream(allReportFeesAndTypeView.getClass().getDeclaredFields()).filter(fd -> fd.getName().toUpperCase().contains(feeHead.toUpperCase()) && !fd.getName().toUpperCase().contains("FEETYPE")).findFirst().orElse(null);

        if (feesField != null) {
            feesField.setAccessible(true);
            feesField.set(allReportFeesAndTypeView, fees);
        }

        Field feeTypeField = Arrays.stream(allReportFeesAndTypeView.getClass().getDeclaredFields()).filter(fd -> fd.getName().toUpperCase().contains(feeHead.toUpperCase()) && fd.getName().toUpperCase().contains("FEETYPE")).findFirst().orElse(null);

        if (feeTypeField != null) {
            feeTypeField.setAccessible(true);
            feeTypeField.set(allReportFeesAndTypeView, feeType);
        }

    }

    private void populateFromClientInfo(AllReportFeesAndTypeView allReportFeesAndTypeView, Client client) {
        allReportFeesAndTypeView.setId(client.getId());
        allReportFeesAndTypeView.setGstNo(client.getGstNo());
        allReportFeesAndTypeView.setName(client.getName());
        allReportFeesAndTypeView.setScheme(client.getScheme());
        allReportFeesAndTypeView.setComments(client.getComments());
    }

    public List<AllReportFeesAndTypeView> convert(List<ReportView> reportViews) throws IllegalAccessException
    {
        List<AllReportFeesAndTypeView> allReportFeesAndTypeViews = new ArrayList<>();
        for (ReportView reportView : reportViews) {
            allReportFeesAndTypeViews.add(convert(reportView));
        }
        return allReportFeesAndTypeViews;
    }
}
