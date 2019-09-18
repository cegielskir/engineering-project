package com.cgk.engineering.team.mainservice.excel;

import com.cgk.engineering.team.dbservice.model.Article;

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

//        if (fileLocation.endsWith(".xls")) {
//            data = readHSSFWorkbook(fileInputStream);
//        } else if (fileLocation.endsWith(".xlsx")) {
            articles = readXSSFWorkbook(inputStream);
        //}

        return articles;
    }

    private List<Article> readHSSFWorkbook(FileInputStream fis) throws IOException {
        List<Article> articles = new ArrayList<>();
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(fis);

            HSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                HSSFRow row = sheet.getRow(i);
                if (row != null) {
                    try {
                        articles.add(createArticleFromExcelRow(row));
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
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
        XSSFWorkbook workbook = null;
        List<Article> articles = new ArrayList<>();
        try {

            workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
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
    }
}