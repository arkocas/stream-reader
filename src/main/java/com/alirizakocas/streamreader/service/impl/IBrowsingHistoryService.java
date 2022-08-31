package com.alirizakocas.streamreader.service.impl;

import com.alirizakocas.streamreader.model.ProductViewModel;

public interface IBrowsingHistoryService {
    boolean saveRecord(ProductViewModel productView);
}
