package com.apiassignment.base;

import java.util.HashMap;
import java.util.Map;

public class HeaderBuilder {
    Map<String,String> headers;

    public HeaderBuilder() {
        this.headers= new HashMap<>();
    }

    public HeaderBuilder withContentType(){
        headers.put("Content-Type","application/json");
        return this;
    }

    public Map<String, String> build() {
        return headers;
    }
}
