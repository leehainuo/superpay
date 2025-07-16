package com.superpay.base.service.api;

import com.alibaba.fastjson2.JSON;
import com.superpay.base.model.Point;
import com.superpay.base.model.amap.geo.Response;
import com.superpay.base.model.amap.regeo.RegResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Tag(name = "地理位置操作API", description = "OpenFeign客户端")
@RestController
@RequestMapping("/geoApi")
public class GeoApi {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("{address}")
    public com.superpay.common.Response<Map<String,String>> geo(@PathVariable String address){
        String url = "https://restapi.amap.com/v3/geocode/geo?key=fd8ee40f41a8458cc2e38f8098f3f29d&address=<ADDR>";
        url = url.replace("<ADDR>", address);
        String response = restTemplate.getForEntity(url, String.class).getBody();
        System.out.println(response);
        //json字符串
        Response resp = JSON.parseObject(response, Response.class);
        //118.091678,24.439332
        String location = resp.getGeocodes()[0].getLocation();
        System.out.println("location:"+location);
        //对经纬度进行切割
        String[] split = location.split(",");
        //String->Double
        //Point point=new Point(Double.parseDouble(split[1]) ,Double.parseDouble(split[0]));
        HashMap<String,String> map=new HashMap<>();
        map.put("longitude",split[1]);
        map.put("latitude",split[0]);
        return com.superpay.common.Response.success(map);
    }

    // url: xxxx.com/24.5118/118.14577
    //{longitude:24.5118,latitude:118.14577}
    @PostMapping("reGeo")
    public com.superpay.common.Response<String> reGeo(@RequestBody Point point){
        String url="https://restapi.amap.com/v3/geocode/regeo?output=json&location=<Longitude>,<Latitude>&key=fd8ee40f41a8458cc2e38f8098f3f29d&radius=1000&extensions=all";
        url=url.replace("<Longitude>",point.getLongitude()+"");
        url=url.replace("<Latitude>",point.getLatitude()+"");
        //json字符串
        String response = restTemplate.getForEntity(url, String.class).getBody();
        System.out.println(response);
        RegResponse regResponse = JSON.parseObject(response, RegResponse.class);
        String township = regResponse.getRegeocode().getAddressComponent().getTownship();
        System.out.println("Township: " + township);
        return com.superpay.common.Response.success(township);
    }
}
