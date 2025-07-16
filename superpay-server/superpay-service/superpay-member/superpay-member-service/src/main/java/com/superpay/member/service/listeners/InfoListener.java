package com.superpay.member.service.listeners;//package com.superpay.member.service.listeners;
//
//import com.alibaba.fastjson2.JSON;
//import com.superpay.member.model.entity.MemberInfo;
//import com.superpay.member.service.service.MemberInfoService;
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.*;
//import org.springframework.stereotype.Component;
//
///**
// * @author lihainuo
// */
//@Slf4j
//@Component
//public class InfoListener {
//
//    @Resource
//    private MemberInfoService memberInfoService;
//
//    @RabbitListener(queues = "info")
//    public void handle(String jsonStr){
//        log.info("接收到的字符串:{}",jsonStr);
//        MemberInfo info = JSON.parseObject(jsonStr, MemberInfo.class);
//        memberInfoService.save(info);
//    }
//}
