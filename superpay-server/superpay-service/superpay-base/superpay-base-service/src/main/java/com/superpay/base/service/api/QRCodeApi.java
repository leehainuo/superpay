package com.superpay.base.service.api;

import com.superpay.base.service.service.QRCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@Slf4j
@Tag(name = "二维码操作API", description = "OpenFeign客户端")
@RestController
@RequestMapping("/qrcodeApi")
public class QRCodeApi {
    @Resource
    private QRCodeService qrCodeService;

    @Operation(summary = "生成二维码")
    @GetMapping("/generate/{url}")
    public String generateQRCode(@PathVariable("url") String url) {
        url = new String(Base64.getDecoder().decode(url));
        String code = qrCodeService.generateQRCode(url);
        return code;
    }
}
