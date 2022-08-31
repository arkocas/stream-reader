package com.alirizakocas.streamreader.service;

import com.alirizakocas.streamreader.entity.BrowsingHistoryEntity;
import com.alirizakocas.streamreader.model.ProductViewModel;
import com.alirizakocas.streamreader.repository.IBrowsingHistoryRepository;
import com.alirizakocas.streamreader.service.impl.IBrowsingHistoryService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BrowsingHistoryService implements IBrowsingHistoryService {

    private final IBrowsingHistoryRepository browsingHistoryRepository;
    private final Gson gson;

    @Autowired
    public BrowsingHistoryService(IBrowsingHistoryRepository browsingHistoryRepository, Gson gson) {
        this.browsingHistoryRepository = browsingHistoryRepository;
        this.gson = gson;
    }

    @Override
    public boolean saveRecord(ProductViewModel productView) {

        if(productView == null || productView.getContext() == null || productView.getProperties() == null)
            return false;


        BrowsingHistoryEntity entity = new BrowsingHistoryEntity();

        try {

            entity.setMessageId(productView.getMessageid());
            entity.setProductId(productView.getProperties().getProductid());
            entity.setUserId(productView.getUserid());
            entity.setSource(productView.getContext().getSource());
            entity.setOptime(productView.getTimestamp());

            browsingHistoryRepository.save(entity);

            return true;
        } catch (Exception e) {
            log.error("An error occurred during the save process entity :{}", gson.toJson(entity));
            return false;
        }
    }
}
