package com.trailcloud.core.util;


import com.trailcloud.core.basetest.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * GetDataFromExcel class is to return data from the given excel sheet
 */
@Slf4j
public class GetData extends BaseTest {
    static Workbook book;
    static Sheet sheet;

    public static Object[][] getData(String excelName, String sheetName) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(System.getProperty("user.dir") + prop.getApplicationPropertyValue("testdata.excelpath") + excelName + ".xlsx");
        } catch (FileNotFoundException e) {
            log.error("Failed to load file from the location due to :" + e.getMessage());
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(fileInputStream);
        } catch (InvalidFormatException e) {
            log.error("Failed to create workbook due to :" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("Failed to create workbook due to :" + e.getMessage());
            e.printStackTrace();
        }

        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum() + 1][sheet.getRow(0).getLastCellNum()];
        for (Row row : sheet) {
            for (Cell cell : row) {
                data[row.getRowNum()][cell.getColumnIndex()] = sheet.getRow(row.getRowNum()).getCell(cell.getColumnIndex()).toString();
            }
        }
        log.info("Data returned from the excel sheet :" + excelName);
        return data;
    }
}
