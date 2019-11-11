package com.cgk.engineering.team.mainservice.rest;

import com.cgk.engineering.team.mainservice.client.comparison.ComparisonServiceController;
import com.cgk.engineering.team.mainservice.model.ComparisonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/method")
public class MethodController {

    @Autowired
    ComparisonServiceController comparisonServiceController;

    @GetMapping("/basic")
    List<ComparisonMethod> getAvailableBasicMethods(){
        return comparisonServiceController.getAvailableBasicMethods();
    }

    @GetMapping("/detailed")
    List<String> getAvailableDetailedMethods(){
        return comparisonServiceController.getAvailableDetailedMethods();
    }



}
