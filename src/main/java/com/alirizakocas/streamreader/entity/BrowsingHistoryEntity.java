package com.alirizakocas.streamreader.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(schema = "public", name = "browsing_history")
public class BrowsingHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_id", nullable = false, unique = true)
    private String messageId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "optime", nullable = false)
    private Timestamp optime;

    public BrowsingHistoryEntity() {
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setOptime(Timestamp optime) {
        this.optime = optime;
    }
}
