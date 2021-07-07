package com.guide.cm.reportgenerator.controller;

import com.guide.cm.reportgenerator.processor.ExcelProcessorAllReportFeesAndTypeView;
import com.guide.cm.reportgenerator.processor.ExcelProcessorAllReportFeesView;
import com.guide.cm.reportgenerator.reportviews.AllReportFeesAndTypeView;
import com.guide.cm.reportgenerator.reportviews.AllReportFeesView;
import com.guide.cm.reportgenerator.reportviews.ReportView;
import com.thoughtworks.xstream.core.util.Fields;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.guide.cm.reportgenerator.proxy.Client;
import com.guide.cm.reportgenerator.proxy.FeeCalculation;
import com.guide.cm.reportgenerator.proxy.FeeProxy;
import com.guide.cm.reportgenerator.proxy.UserProfileProxy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ReportGeneratorController {

    @Autowired
    FeeProxy feeProxy;

    @Autowired
    UserProfileProxy userProfileProxy;

    @Autowired
    ExcelProcessorAllReportFeesView excelProcessorAllReportFeesView;

    @Autowired
    ExcelProcessorAllReportFeesAndTypeView excelProcessorAllReportFeesAndTypeView;

    @GetMapping("/allReportFeesView/{assessMentYear}")
    public List<ReportView> getReportFeesView(@PathVariable String assessMentYear) throws IOException, IllegalAccessException {
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

        allReportFeesViewToExcel(reportViews,assessMentYear);
        return reportViews;
    }


    @GetMapping("/allReportFeesAndTypeView/{assessMentYear}")
    public List<ReportView> getReportFeesAndTypeView(@PathVariable String assessMentYear) throws IOException, IllegalAccessException {
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

        allReportFeesAndTypeViewToExcel(reportViews,assessMentYear);
        return reportViews;
    }

    private void allReportFeesViewToExcel(List<ReportView> reportViews,String assessMentYear) throws IOException, IllegalAccessException {
            excelProcessorAllReportFeesView.applyStratergy(reportViews,assessMentYear,AllReportFeesView.class);

    }


    private void allReportFeesAndTypeViewToExcel(List<ReportView> reportViews, String assessMentYear) throws IOException, IllegalAccessException {
        excelProcessorAllReportFeesAndTypeView.applyStratergy(reportViews,assessMentYear, AllReportFeesAndTypeView.class);
    }






}
