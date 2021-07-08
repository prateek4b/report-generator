package com.guide.cm.reportgenerator.processor;

import com.guide.cm.reportgenerator.mapper.ReportViewToAllReportView;
import com.guide.cm.reportgenerator.reportviews.AllReportView;
import com.guide.cm.reportgenerator.reportviews.ReportView;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class ExcelProcessorAllReportView extends AbstracteExcelReportProcessor {

    @Autowired
    ReportViewToAllReportView reportViewToAllReportView;

    public void applyStratergy(List<ReportView> reportViews, String assessMentYear, Class className) throws IOException, IllegalAccessException {
        Workbook workbook = createNewWorkBook();
        Sheet sheet = createSheet(workbook, assessMentYear);
        addColumns(sheet, AllReportView.class.getDeclaredFields().length);
        Row headerRow = createHeaderRow(sheet);
        CellStyle headerStyle = setHeaderStyle(workbook);
        addValuesToHeaderCells(headerRow, headerStyle, className);
        CellStyle cellStyle = wrapText(workbook);
        List<AllReportView> allReportViews = reportViewToAllReportView.convert(reportViews);
        addRowsToSheet(allReportViews, sheet, cellStyle);
        writeExcelFile(workbook);
    }

    private void addRowsToSheet(List<AllReportView> allReportViews, Sheet sheet, CellStyle cellStyle) throws IllegalAccessException {

        for (int i = 1; i <= allReportViews.size(); i++) {
            addRowsToSheet(i, allReportViews.get(i-1), sheet, cellStyle);
        }

    }

    private void addRowsToSheet(int i, AllReportView allReportView, Sheet sheet, CellStyle cellStyle) throws IllegalAccessException {
        Row row = sheet.createRow(i);
        Field[] fields = allReportView.getClass().getDeclaredFields();
        for (int k = 0; k < fields.length; k++) {
            fields[k].setAccessible(true);
            writeColumn(row, k, fields[k].get(allReportView), cellStyle);
        }

    }

    private void writeColumn(Row row, int k, Object value, CellStyle cellStyle) {
        Cell cell = row.createCell(k);
        cell.setCellValue(value.toString());
        cell.setCellStyle(cellStyle);
     }

}
