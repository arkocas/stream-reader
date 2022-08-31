package com.alirizakocas.streamreader.service;

import com.alirizakocas.streamreader.model.ProductViewModel;
import com.alirizakocas.streamreader.service.impl.IBrowsingHistoryService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaListenerService {

    private final IBrowsingHistoryService browsingHistory;
    private final Gson gson;

    @Autowired
    public KafkaListenerService(IBrowsingHistoryService browsingHistory, Gson gson) {
        this.browsingHistory = browsingHistory;
        this.gson = gson;
    }

    @KafkaListener(
            topics = "${product.views.kafka.topic}",
            groupId = "${product.views.kafka.group.id}"
    )

    public void listen(@Payload ProductViewModel productView) {
        log.info("History received.. productView:{}", gson.toJson(productView));
        //save
        boolean success = browsingHistory.saveRecord(productView);

        if(!success)
            log.error("Failed to save to database!");
    }
}
