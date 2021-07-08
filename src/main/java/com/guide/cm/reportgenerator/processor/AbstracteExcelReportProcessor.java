package com.guide.cm.reportgenerator.processor;

import com.guide.cm.reportgenerator.reportviews.AllReportFeesView;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class AbstracteExcelReportProcessor {


    public Workbook createNewWorkBook() {
        return new XSSFWorkbook();
    }

    public Sheet createSheet(Workbook workbook, String sheetName) {
        return workbook.createSheet(sheetName);
    }

    public void addColumns(Sheet sheet, int i) {
        for (int counter = 0; counter < i; counter++)
            sheet.setColumnWidth(i, 6000);

    }

    public Row createHeaderRow(Sheet sheet) {
        return sheet.createRow(0);
    }


    public CellStyle setHeaderStyle(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        headerStyle.setFont(font);
        return headerStyle;
    }


    public void addValuesToHeaderCells(Row header, CellStyle headerStyle, Class className) {

        int counter =0;

        Field[] fields = className.getDeclaredFields();
        for (Field field : fields) {
            Cell headerCell = header.createCell(counter++);
            if(field.getName().toUpperCase().contains("FEETYPE"))
            {
                headerCell.setCellValue("FEE_TYPE");
            }else if(field.getName().toUpperCase().contains("DATE"))
            {
                headerCell.setCellValue("DATE");
            }else if(field.getName().toUpperCase().contains("RECEIPTNO"))
            {
                headerCell.setCellValue("RCPT_NO");
            }else if(field.getName().toUpperCase().contains("YEAR"))
            {
                headerCell.setCellValue("Year");
            }else {
                headerCell.setCellValue(field.getName().replace("Fees", "").toUpperCase());
            }
            headerCell.setCellStyle(headerStyle);
        }
    }

    public CellStyle wrapText(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        return style;
    }

    public void writeExcelFile(Workbook workbook) throws IOException {

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();
    }

}
