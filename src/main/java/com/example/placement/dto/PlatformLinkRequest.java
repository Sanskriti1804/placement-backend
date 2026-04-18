package com.example.placement.dto;

import com.example.placement.entity.types.PlatformType;

public class PlatformLinkRequest {
    private PlatformType type;
    private String url;

    // Getter and Setter for type
    public PlatformType getType() {
        return type;
    }

    public void setType(PlatformType type) {
        this.type = type;
    }

    // Getter and Setter for url
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}



