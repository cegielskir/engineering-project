package com.cgk.engineering.team.mainservice.client.comparison.util;

import com.cgk.engineering.team.mainservice.client.comparison.services.ComparisonService;
import com.cgk.engineering.team.mainservice.client.comparison.services.api.IComparisonService;
import com.cgk.engineering.team.mainservice.model.ComparisonMethod;
import com.cgk.engineering.team.mainservice.model.ComparisonMethodInfo;
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
    private ComparisonService comparisonService;



    private List<IComparisonService> getAvailableComparisonServices(){
        List<IComparisonService> comparisonServices = new ArrayList<>();
        comparisonServices.add(comparisonService);
        /* Here add new comparison services */

        return comparisonServices;
    }

    private Map<String, ComparisonMethodInfo> methodServiceMap;

    public List<ComparisonMethod> getAndUpdateAvailableBasicMethods(){

        methodServiceMap = new HashMap<>();

        List<ComparisonMethod> allAvailableMethods = new ArrayList<>();

        List<IComparisonService> comparisonServices = getAvailableComparisonServices();

        for(IComparisonService service : comparisonServices){
            List<ComparisonMethod> methods = new ArrayList<>();
            try {
                methods = service.getAvailableMethods();
            } catch (Exception ex){
                ex.printStackTrace();
            }

            for(ComparisonMethod method : methods){
                methodServiceMap.put(method.getName(), new ComparisonMethodInfo(service, method));
            }

            allAvailableMethods.addAll(methods);
        }

        return allAvailableMethods;
    }

    public List<String> getAvailableDetailedMethods(){

        getAndUpdateAvailableBasicMethods();

        return methodServiceMap.values().stream()
                .map(ComparisonMethodInfo::getComparisonMethod)
                .filter(ComparisonMethod::isDetailedMethodAvailable)
                .map(ComparisonMethod::getName)
                .collect(Collectors.toList());
    }

    public IComparisonService getServiceWithMethod(String method){
        return methodServiceMap.get(method).getComparisonService();
    }
}
