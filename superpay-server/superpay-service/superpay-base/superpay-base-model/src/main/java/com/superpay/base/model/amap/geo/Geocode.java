package com.superpay.base.model.amap.geo;

import lombok.Data;

@Data
public class Geocode {
    private String formattedAddress;
    private String country;
    private String province;
    private String citycode;
    private String city;
    private String district;
    private String adcode;
    private String location;
    private String level;
    private Neighborhood neighborhood;
    private Building building;
}





