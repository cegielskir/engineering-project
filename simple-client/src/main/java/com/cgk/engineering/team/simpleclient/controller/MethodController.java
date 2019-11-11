package com.cgk.engineering.team.simpleclient.controller;

import com.cgk.engineering.team.simpleclient.model.ComparisonMethod;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/method")
public class MethodController {

    @GetMapping()
    List<ComparisonMethod> getAvailableMethods() {

        List<ComparisonMethod> comparisonMethods = new LinkedList<>();
        comparisonMethods.add(new ComparisonMethod("Cosine", true));
        comparisonMethods.add(new ComparisonMethod("Dice", true));
        comparisonMethods.add(new ComparisonMethod("NeedlemanWunsch", false));
        return comparisonMethods;
    }

}
