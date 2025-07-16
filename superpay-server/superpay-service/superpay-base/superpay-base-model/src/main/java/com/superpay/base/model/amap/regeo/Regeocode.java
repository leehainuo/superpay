package com.superpay.base.model.amap.regeo;

import java.util.List;

public class Regeocode {
    private List<Road> roads;
    private List<Roadinter> roadinters;
    private String formatted_address;
    private AddressComponent addressComponent;
    private List<Aoi> aois;
    private List<Poi> pois;
    private String info;
    private String infocode;

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    public List<Roadinter> getRoadinters() {
        return roadinters;
    }

    public void setRoadinters(List<Roadinter> roadinters) {
        this.roadinters = roadinters;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }

    public List<Aoi> getAois() {
        return aois;
    }

    public void setAois(List<Aoi> aois) {
        this.aois = aois;
    }

    public List<Poi> getPois() {
        return pois;
    }

    public void setPois(List<Poi> pois) {
        this.pois = pois;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }
// getters and setters
}