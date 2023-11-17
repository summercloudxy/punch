package com.xy.work;

import com.xy.work.punchin.service.PunchInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PunchController {
    @Autowired
    PunchInService punchInService;

    @GetMapping("/go")
    public void go(){
        punchInService.go();
    }


    @GetMapping("/work")
    public void work(){
        punchInService.work();
    }
}
