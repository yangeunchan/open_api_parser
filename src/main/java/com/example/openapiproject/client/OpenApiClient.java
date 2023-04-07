package com.example.openapiproject.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "aptCodeClient", url = "http://apis.data.go.kr", configuration = FeignConfig.class)
public interface OpenApiClient {
    @GetMapping("/1613000/AptListService2/getTotalAptList")
    String getAptCode(@RequestParam(name = "ServiceKey") String serviceKey,
                          @RequestParam(name = "pageNo") Integer page,
                          @RequestParam(name = "numOfRows") Integer row);

    @GetMapping("/1613000/AptBasisInfoService1/getAphusBassInfo")
    String getAptDetail(@RequestParam(name = "kaptCode") String aptCode,
                        @RequestParam(name = "ServiceKey") String serviceKey);
}
