package com.xy.work.punchin.service;


import com.xy.work.punchin.pojo.LoginResponse;
import com.xy.work.punchin.pojo.PunchRequest;
import com.xy.work.punchin.pojo.ZxResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "abc",url = "https://zhixin.zhiguaniot.com")
public interface ZxFeignClient {

    @GetMapping("/api/app/login")
    ZxResponse<LoginResponse> login(@RequestParam(value = "username") String userName, @RequestParam String password, @RequestHeader(name = "clientType")String clientType);

    @PostMapping("/api/location/v1/sign/officeSign")
    ZxResponse punch(@RequestBody PunchRequest punchRequest, @RequestHeader(name = "Authorization")String token, @RequestHeader(name = "clientType")String clientType);
}
