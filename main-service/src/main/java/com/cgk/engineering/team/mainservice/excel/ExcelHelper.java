package com.cgk.engineering.team.mainservice.excel;

import com.cgk.engineering.team.dbservice.model.Article;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelHelper {

    public List<Article> readExcel(String fileLocation) throws IOException {

        List<Article> data = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(new File(fileLocation));

        if (fileLocation.endsWith(".xls")) {
            data = readHSSFWorkbook(fileInputStream);
        } else if (fileLocation.endsWith(".xlsx")) {
            data = readXSSFWorkbook(fileInputStream);
        }

        return data;
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
        article.setUrl(row.getCell(0).getStringCellValue());
        article.setNumberOfViews(row.getCell(1).getStringCellValue());
        article.setAuthor(row.getCell(2).getStringCellValue());
        article.setDate(row.getCell(3).getStringCellValue());
        article.setDescription(row.getCell(4).getStringCellValue());
        article.setContent(row.getCell(5).getStringCellValue());
        article.setTitle(row.getCell(6).getStringCellValue());
        article.setDownloadTime(row.getCell(7).getStringCellValue());
        return article;
    }

    private List<Article> readXSSFWorkbook(FileInputStream fis) throws IOException {
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
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return articles;
    }

}