package com.xy.work.punchin.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xy.work.punchin.pojo.LoginResponse;
import com.xy.work.punchin.pojo.OrgInfo;
import com.xy.work.punchin.pojo.PunchRequest;
import com.xy.work.punchin.pojo.ZxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.xy.work.holiday.HolidayUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class PunchInService {

    @Autowired
    ZxFeignClient zxFeignClient;
    @Autowired
    HolidayUtils holidayUtils;

    @Scheduled(cron = "0 0 8 * * ? ")
    public void randomWork() {
        try {
            Thread.sleep(new Random().nextInt(30 * 60) * 1000);
            work();

        }catch (Exception e){
            log.error("打卡失败",e);
        }
    }

    public void work(){
        try {
            for (int i = 0; i < 3; i++) {
                boolean punch = punch("13682192877", "123xiayun", 1);
                if (punch) {
                    break;
                }
            }
            for (int i = 0; i < 3; i++) {
                boolean punch = punch("18522290119", "meng777.", 1);
                if (punch) {
                    break;
                }
            }
        }catch (Exception e){

            log.error("打卡失败",e);
        }
    }


    @Scheduled(cron = "0 0 17 * * ? ")
    public void randomGo() {
        try {

            Thread.sleep(new Random().nextInt(30 * 60) * 1000);
            go();

        }catch (Exception e){
            log.error("打卡失败",e);
        }
    }


    public void go(){
        try {
            for (int i = 0; i < 3; i++) {
                boolean punch = punch("13682192877", "123xiayun", 0);
                if (punch) {
                    break;
                }
            }
            for (int i = 0; i < 3; i++) {
                boolean punch = punch("18522290119", "meng777.", 0);
                if (punch) {
                    break;
                }
            }
        }catch (Exception e){

            log.error("打卡失败",e);
        }
    }


    public boolean punch(String userName, String password, int signType) {
        try {
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
            if (!holidayUtils.isHoliday(new Date())) {
                ZxResponse<LoginResponse> login = zxFeignClient.login(userName, password, "app");
                if ("M0000".equals(login.getCode())) {
                    LoginResponse data = login.getData();
                    String accessToken = data.getAccess_token();
                    String orgInfoStr = data.getOrgInfo();
                    List<OrgInfo> orgInfos = JSON.parseObject(orgInfoStr, new TypeReference<List<OrgInfo>>() {
                    });
                    for (OrgInfo orgInfo : orgInfos) {
                        if ("6".equals(orgInfo.getCorpId())) {
                            String userId = orgInfo.getUserId();
                            PunchRequest punchRequest = new PunchRequest();
                            punchRequest.setUserId(userId);
                            punchRequest.setSignType(signType);
                            ZxResponse punch = zxFeignClient.punch(punchRequest, "Bearer " + accessToken, "app");
                            if (punch != null && "M0000".equals(punch.getCode())) {
                                log.info("{}打卡成功", userName);
                                return true;
                            } else {
                                log.error("{}打卡失败", userName);
                            }
                        }
                    }
                } else {
                    log.error("{}登录失败", userName);
                }
            }
            log.warn("今天是节假日，无需打卡");
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
