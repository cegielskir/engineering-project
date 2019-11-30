package com.cgk.engineering.team.mainservice.excel;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.stereotype.Component;
import com.cgk.engineering.team.mainservice.model.Article;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelHelper {

    public List<Article> getArticlesFromExcelFile(InputStream inputStream) throws IOException {

        List<Article> articles;

//        } else if (fileLocation.endsWith(".xlsx")) {
        articles = readXSSFWorkbook(inputStream);


        return articles;
    }

    private Article createArticleFromExcelRow(Row row){
        Article article = new Article();
        article.setUrl(readCellContent(row.getCell(0)));
        article.setNumberOfViews(readCellContent(row.getCell(1)));
        article.setAuthor(readCellContent(row.getCell(2)));
        article.setDate(readCellContent(row.getCell(3)));
        article.setDescription(readCellContent(row.getCell(4)));
        article.setContent(readCellContent(row.getCell(5)));
        article.setTitle(readCellContent(row.getCell(6)));
        article.setDownloadTime(readCellContent(row.getCell(7)));
        return article;
    }

    private List<Article> readXSSFWorkbook(InputStream fis) throws IOException {
        XSSFWorkbook workbook;
        List<Article> articles = new ArrayList<>();
        try {

            workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            // without 1st row - there are descriptions of columns
            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                if (row != null) {
                    try {
                        articles.add(createArticleFromExcelRow(row));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return articles;
    }

    private String readCellContent(Cell cell) {
        String content;
        try {
            switch (cell.getCellType()) {
                case STRING:
                    content = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        content = cell.getDateCellValue() + "";
                    } else {
                        content = cell.getNumericCellValue() + "";
                    }
                    break;
                case BOOLEAN:
                    content = cell.getBooleanCellValue() + "";
                    break;
                case FORMULA:
                    content = cell.getCellFormula() + "";
                    break;
                default:
                    content = "";
            }
            return content;
        } catch (NullPointerException ex){
            return "";
        }
    }
}