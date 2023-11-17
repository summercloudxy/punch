package com.xy.work.holiday;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: Holiday
 * @Author: lxh
 * @Description: 节假日
 * @Date: 2022/3/19 17:29
 */
@Component
@Slf4j
public class HolidayUtils {
    Set<String> allHoliday;

    @PostConstruct
    public void init(){
        File file = new File("config/no_work_day.txt");
        // 按行读取文件
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(fileInputStream);
            allHoliday = new HashSet<>();
            while (scanner.hasNextLine()){
                String nextLine = scanner.nextLine();
                allHoliday.add(nextLine);
            }
        } catch (IOException e) {
            log.error("读取节假日文件失败",e);
        }
        log.info("节假日初始化完成，加载节假日{}天",allHoliday.size());
    }


    public boolean isHoliday(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = simpleDateFormat.format(date);
        return allHoliday.contains(format);
    }
}

