package com.cgk.engineering.team.mainservice.controller;


import com.cgk.engineering.team.dbservice.model.Article;
import com.cgk.engineering.team.mainservice.client.AlgorithmClient;
import com.cgk.engineering.team.mainservice.client.DatabaseServiceClient;
import com.cgk.engineering.team.mainservice.model.Comparison;
import com.cgk.engineering.team.mainservice.model.ComparisonData;
import com.cgk.engineering.team.mainservice.websocket.ComparisonWebSocketController;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/compare")
@EnableFeignClients
public class AlgorithmServiceController {


    @Autowired
    private DatabaseServiceClient dbClient;

    @Autowired
    private AlgorithmClient algorithmClient;

    @Autowired
    private ComparisonWebSocketController webSockController;

}
