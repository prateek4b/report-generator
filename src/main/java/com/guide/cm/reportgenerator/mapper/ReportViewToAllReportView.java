package com.guide.cm.reportgenerator.mapper;

import com.guide.cm.reportgenerator.proxy.Client;
import com.guide.cm.reportgenerator.proxy.FeeEntry;
import com.guide.cm.reportgenerator.reportviews.AllReportFeesAndTypeView;
import com.guide.cm.reportgenerator.reportviews.AllReportView;
import com.guide.cm.reportgenerator.reportviews.ReportView;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ReportViewToAllReportView {

    public AllReportView convert(ReportView reportView) throws IllegalAccessException {
        AllReportView allReportView = new AllReportView();
        populateFromClientInfo(allReportView, reportView.getClient());

        for (FeeEntry feeEntry : reportView.getFeeCalculation().getFeeEntries()) {
            String feeHead = feeEntry.getFeeHead();
            long fees = feeEntry.getFees();
            findAndPopulateFeesAndType(feeHead, fees,feeEntry.getFeeType(),feeEntry.getDate(),feeEntry.getReceiptNo(),feeEntry.getAssessMentYear(), allReportView);
        }

        allReportView.evaluateTotalFees();
        return allReportView;

    }

    private void findAndPopulateFeesAndType(String feeHead, long fees, String feeType,String feeDate,long feeReceiptNo,String assessmentYear , AllReportView allReportView) throws IllegalAccessException {

        Field feesField = Arrays.stream(allReportView.getClass().getDeclaredFields()).filter(fd -> fd.getName().toUpperCase().contains(feeHead.toUpperCase()) && !fd.getName().toUpperCase().contains("FEETYPE")).findFirst().orElse(null);

        if (feesField != null) {
            feesField.setAccessible(true);
            feesField.set(allReportView, fees);
        }

        Field feeTypeField = Arrays.stream(allReportView.getClass().getDeclaredFields()).filter(fd -> fd.getName().toUpperCase().contains(feeHead.toUpperCase()) && fd.getName().toUpperCase().contains("FEETYPE")).findFirst().orElse(null);

        if (feeTypeField != null) {
            feeTypeField.setAccessible(true);
            feeTypeField.set(allReportView, feeType);
        }

        Field dateTypeField = Arrays.stream(allReportView.getClass().getDeclaredFields()).filter(fd -> fd.getName().toUpperCase().contains(feeHead.toUpperCase()) && fd.getName().toUpperCase().contains("DATE")).findFirst().orElse(null);

        if (dateTypeField != null) {
            dateTypeField.setAccessible(true);
            dateTypeField.set(allReportView, feeDate);
        }

        Field receiptTypeField = Arrays.stream(allReportView.getClass().getDeclaredFields()).filter(fd -> fd.getName().toUpperCase().contains(feeHead.toUpperCase()) && fd.getName().toUpperCase().contains("RECEIPT")).findFirst().orElse(null);

        if (receiptTypeField != null) {
            receiptTypeField.setAccessible(true);
            receiptTypeField.set(allReportView, feeReceiptNo);
        }

        Field assementYearTypeField = Arrays.stream(allReportView.getClass().getDeclaredFields()).filter(fd -> fd.getName().toUpperCase().contains(feeHead.toUpperCase()) && fd.getName().toUpperCase().contains("ASSESSMENT")).findFirst().orElse(null);

        if (assementYearTypeField != null) {
            assementYearTypeField.setAccessible(true);
            assementYearTypeField.set(allReportView, assessmentYear);
        }


    }

    private void populateFromClientInfo(AllReportView allReportView, Client client) {
        allReportView.setId(client.getId());
        allReportView.setGstNo(client.getGstNo());
        allReportView.setName(client.getName());
        allReportView.setScheme(client.getScheme());
        allReportView.setComments(client.getComments());
    }

    public List<AllReportView> convert(List<ReportView> reportViews) throws IllegalAccessException
    {
        List<AllReportView> allReportViews = new ArrayList<>();
        for (ReportView reportView : reportViews) {
            allReportViews.add(convert(reportView));
        }
        return allReportViews;
    }
}
