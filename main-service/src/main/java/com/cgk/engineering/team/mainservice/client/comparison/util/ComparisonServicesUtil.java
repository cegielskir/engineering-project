package com.cgk.engineering.team.mainservice.client.comparison.util;

import com.cgk.engineering.team.mainservice.client.comparison.services.AlgorithmClient;
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
    private AlgorithmClient comparisonService;

    private Map<String, ComparisonMethodInfo> methodServiceMap;



    public List<ComparisonMethod> getAndUpdateAvailableBasicMethods(){

        methodServiceMap = new HashMap<>();

        List<ComparisonMethod> allAvailableMethods = new ArrayList<>();

        List<IComparisonService> comparisonServices = new ArrayList<>();
        comparisonServices.add(comparisonService);

        for(IComparisonService service : comparisonServices){
            List<ComparisonMethod> methods = service.getAvailableMethods();

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
        // System.out.println("HERE");
        System.out.println("METHODS: " + methodServiceMap.keySet().iterator().next().toString());
        for(String str:methodServiceMap.keySet()){
            System.out.println("Method in set: " + str);
        }
        System.out.println(method);
        System.out.println(methodServiceMap.get(method));
        return methodServiceMap.get(method).getComparisonService();
    }
}
