package com.superpay.base.model.amap.regeo;

import lombok.Data;

import java.util.List;

@Data
public class AddressComponent {
    private String city;
    private String province;
    private String adcode;
    private String district;
    private String towncode;
    private StreetNumber streetNumber;
    private String country;
    private String township;
    private String seaArea;
    private List<BusinessArea> businessAreas;
    private Building building;
    private Neighborhood neighborhood;
    private String citycode;

    // getters and setters
}
