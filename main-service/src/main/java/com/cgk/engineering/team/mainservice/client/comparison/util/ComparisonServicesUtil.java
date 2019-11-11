package com.cgk.engineering.team.mainservice.client.comparison.util;

import com.cgk.engineering.team.mainservice.client.comparison.services.api.IComparisonService;
import com.cgk.engineering.team.mainservice.model.ComparisonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ComparisonServicesUtil {

    @Autowired
    private List<IComparisonService> comparisonServices;

    Map<String, ComparisonMethod> methodServiceMap;

    public List<ComparisonMethod> getAndUpdateAvailableBasicMethods(){


        methodServiceMap = new HashMap<>();

        List<ComparisonMethod> allAvailableMethods = new ArrayList<>();

        for(IComparisonService service : comparisonServices){
            List<ComparisonMethod> methods = service.geAvailableMethods();

            for(ComparisonMethod method : methods){
                method.setComparisonService(service);
                methodServiceMap.put(method.getName(), method);
            }

            allAvailableMethods.addAll(methods);
        }

        return allAvailableMethods;
    }

    public List<String> getAvailableDetailedMethods(){

        getAndUpdateAvailableBasicMethods();

        return methodServiceMap.values().stream()
                .filter(ComparisonMethod::isDetailedMethodAvailable)
                .map(ComparisonMethod::getName)
                .collect(Collectors.toList());
    }

    public IComparisonService getServiceWithMethod(String method){
        return methodServiceMap.get(method).getComparisonService();
    }
}
