package com.alirizakocas.streamreader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ContextModel implements Serializable {

    private static final long serialVersionUID = -3017243123242052957L;
    @JsonProperty("source")
    private String source;

    public String getSource() {
        return source;
    }
}
