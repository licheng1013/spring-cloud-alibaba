package com.demo.controller;

import com.demo.authentication.PassToken;
import com.demo.service.PushServiceImpl;
import com.demo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author root
 * @description TODO
 * @date 2021/1/9 15:18
 */
@RequestMapping("push")
@RestController
public class PushController {
    @Autowired
    private PushServiceImpl pushService;

    @GetMapping("test")
    @PassToken
    public JsonResult<String> test(String tel){
        return JsonResult.okData(pushService.push(tel));
    }
}
