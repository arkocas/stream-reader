package com.alirizakocas.streamreader.repository;

import com.alirizakocas.streamreader.entity.BrowsingHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrowsingHistoryRepository extends CrudRepository<BrowsingHistoryEntity, Long> {
}
