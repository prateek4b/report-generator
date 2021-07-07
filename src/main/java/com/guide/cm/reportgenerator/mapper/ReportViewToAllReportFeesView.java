package com.guide.cm.reportgenerator.mapper;

import com.guide.cm.reportgenerator.proxy.Client;
import com.guide.cm.reportgenerator.proxy.FeeEntry;
import com.guide.cm.reportgenerator.reportviews.AllReportFeesView;
import com.guide.cm.reportgenerator.reportviews.ReportView;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReportViewToAllReportFeesView {

    public AllReportFeesView convert(ReportView reportView) throws IllegalAccessException {
        AllReportFeesView allReportFeesView = new AllReportFeesView();
        populateFromClientInfo(allReportFeesView, reportView.getClient());

        for (FeeEntry feeEntry : reportView.getFeeCalculation().getFeeEntries()) {
            String feeHead = feeEntry.getFeeHead();
            long fees = feeEntry.getFees();
            findAndPopulateFees(feeHead, fees, allReportFeesView);
        }

        allReportFeesView.evaluateTotalFees();
        return allReportFeesView;

    }

    private void findAndPopulateFees(String feeHead, long fees, AllReportFeesView allReportFeesView) throws IllegalAccessException {

        Field field = Arrays.stream(allReportFeesView.getClass().getDeclaredFields()).filter(fd -> fd.getName().toUpperCase().contains(feeHead.toUpperCase())).findFirst().orElse(null);

        if (field != null) {
            field.setAccessible(true);
            field.set(allReportFeesView, fees);
        }

    }

    private void populateFromClientInfo(AllReportFeesView allReportFeesView, Client client) {
        allReportFeesView.setId(client.getId());
        allReportFeesView.setGstNo(client.getGstNo());
        allReportFeesView.setName(client.getName());
        allReportFeesView.setScheme(client.getScheme());
        allReportFeesView.setComments(client.getComments());
    }

    public List<AllReportFeesView> convert(List<ReportView> reportViews) throws IllegalAccessException
    {
        List<AllReportFeesView> allReportFeesViews = new ArrayList<>();
        for (ReportView reportView : reportViews) {
            allReportFeesViews.add(convert(reportView));
        }
        return allReportFeesViews;
    }
}
