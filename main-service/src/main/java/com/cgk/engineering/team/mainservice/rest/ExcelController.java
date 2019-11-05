package com.cgk.engineering.team.mainservice.rest;

import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.excel.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    ExcelHelper excelHelper;

    @Autowired
    DatabaseServiceClient databaseServiceClient;

    @PostMapping("/uploadFile")
    public void uploadFile(@RequestBody MultipartFile file) {
        try {
            excelHelper.getArticlesFromExcelFile(file.getInputStream())
                    .forEach(a -> databaseServiceClient.addArticle(a));
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
    }
}
