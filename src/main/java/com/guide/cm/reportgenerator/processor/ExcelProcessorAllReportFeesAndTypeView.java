package com.guide.cm.reportgenerator.processor;

import com.guide.cm.reportgenerator.mapper.ReportViewToAllReportFeesAndTypeView;
import com.guide.cm.reportgenerator.mapper.ReportViewToAllReportFeesView;
import com.guide.cm.reportgenerator.reportviews.AllReportFeesAndTypeView;
import com.guide.cm.reportgenerator.reportviews.AllReportFeesView;
import com.guide.cm.reportgenerator.reportviews.ReportView;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class ExcelProcessorAllReportFeesAndTypeView extends AbstracteExcelReportProcessor {

    @Autowired
    ReportViewToAllReportFeesAndTypeView reportViewToAllReportFeesAndTypeView;

    public void applyStratergy(List<ReportView> reportViews, String assessMentYear, Class className) throws IOException, IllegalAccessException {
        Workbook workbook = createNewWorkBook();
        Sheet sheet = createSheet(workbook, assessMentYear);
        addColumns(sheet, AllReportFeesAndTypeView.class.getDeclaredFields().length);
        Row headerRow = createHeaderRow(sheet);
        CellStyle headerStyle = setHeaderStyle(workbook);
        addValuesToHeaderCells(headerRow, headerStyle, className);
        CellStyle cellStyle = wrapText(workbook);
        List<AllReportFeesAndTypeView> allReportFeesAndTypeViews = reportViewToAllReportFeesAndTypeView.convert(reportViews);
        addRowsToSheet(allReportFeesAndTypeViews, sheet, cellStyle);
        writeExcelFile(workbook);
    }

    private void addRowsToSheet(List<AllReportFeesAndTypeView> allReportFeesAndTypeViews, Sheet sheet, CellStyle cellStyle) throws IllegalAccessException {

        for (int i = 1; i <= allReportFeesAndTypeViews.size(); i++) {
            addRowsToSheet(i, allReportFeesAndTypeViews.get(i-1), sheet, cellStyle);
        }

    }

    private void addRowsToSheet(int i, AllReportFeesAndTypeView allReportFeesAndTypeView, Sheet sheet, CellStyle cellStyle) throws IllegalAccessException {
        Row row = sheet.createRow(i);
        Field[] fields = allReportFeesAndTypeView.getClass().getDeclaredFields();
        for (int k = 0; k < fields.length; k++) {
            fields[k].setAccessible(true);
            writeColumn(row, k, fields[k].get(allReportFeesAndTypeView), cellStyle);
        }

    }

    private void writeColumn(Row row, int k, Object value, CellStyle cellStyle) {
        Cell cell = row.createCell(k);
        cell.setCellValue(value.toString());
        cell.setCellStyle(cellStyle);
     }

}
