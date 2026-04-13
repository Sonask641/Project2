package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static String getCellData(String sheetName, int rowNum, int colNum) {
        String value = "";

        try {
            FileInputStream fis = new FileInputStream("src/test/resources/testdata.xlsx");
            Workbook workbook = new XSSFWorkbook(fis);

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);

            value = cell.getStringCellValue();

            workbook.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }
}