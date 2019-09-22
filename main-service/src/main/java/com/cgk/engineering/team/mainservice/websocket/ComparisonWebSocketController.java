package com.cgk.engineering.team.mainservice.websocket;

import com.cgk.engineering.team.mainservice.model.Comparison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ComparisonWebSocketController {

    private SimpMessagingTemplate template;

    @Autowired
    public ComparisonWebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendComparison(Comparison comparison) {
        this.template.convertAndSend("/comparison", comparison);
    }

}