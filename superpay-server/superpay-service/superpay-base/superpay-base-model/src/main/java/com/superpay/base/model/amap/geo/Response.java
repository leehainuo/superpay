package com.superpay.base.model.amap.geo;

import lombok.Data;

@Data
public class Response {
    private String status;
    private String info;
    private String infocode;
    private String count;
    private Geocode[] geocodes;
}
