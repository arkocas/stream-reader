package com.alirizakocas.streamreader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PropertiesModel implements Serializable {

    private static final long serialVersionUID = 8482581641484561170L;
    @JsonProperty("productid")
    private String productid;

    public String getProductid() {
        return productid;
    }
}
