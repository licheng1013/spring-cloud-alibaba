package com.demo.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.annotation.ParamLog;
import com.demo.authentication.PassToken;
import com.demo.entity.User;
import com.demo.feign.OrderFeign;
import com.demo.service.UserService;
import com.demo.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author root
 * @description TODO
 * @date 2020/12/18 11:39
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Value("${server.port}")
    private String port;
    @Resource
    private UserService userService;

    @PassToken
    @PostMapping("create")
    public JsonResult<String> create(User user){
        User tel = userService.getOne(new QueryWrapper<User>().eq("tel", user.getTel()));
        if (tel == null && StrUtil.isNotBlank(user.getTel())) {
            user.insert();
            return JsonResult.okMsg("创建成功");
        }
        return JsonResult.fail("创建失败");
    }

    @PassToken
    @GetMapping("list")
    public JsonResult<IPage<User>> list(Page<User> page,User user) throws InterruptedException {
        Thread.sleep(500);
        return JsonResult.okData(user.selectPage(page, new QueryWrapper<>(user)));
    }


    @Autowired
    private OrderFeign orderFeign;

    /** 接口测试 **/
    @GetMapping("test")
    @PassToken
    @ParamLog
    public JsonResult<String> test() {
        return JsonResult.okMsg("Hello World " + port);
    }
    /** openfeign调用测试 **/
    @GetMapping("feign/test")
    public JsonResult<String> feignTest(){
        log.info("feign调用: {}",port);
        return orderFeign.test();
    }

    /** 异常测试 **/
    @GetMapping("exception")
    public JsonResult<String> exception(){
        int i = 1/0;
        return JsonResult.okMsg("Hello exception");
    }

    /** openfeign异常调用测试 **/
    @GetMapping("feign/exception")
    public JsonResult<String> feignException(){
        return orderFeign.exception();
    }


    /** 随机验证码 **/
    @GetMapping("code")
    public void code(HttpServletResponse response) throws IOException {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        //随机验证码
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        captcha.setGenerator(randomGenerator);
        captcha.createCode();
        //captcha.getCode()=随机验证码,假设redis 手机号:code 则登入时调用randomGenerator.verify(captcha.getCode(), "1234");
        captcha.write(response.getOutputStream());
        response.getOutputStream().close();
    }
    /** 二维码 **/
    @GetMapping("qrCode")
    public void qrCode(HttpServletResponse response) throws IOException {
        QrCodeUtil.generate("Hello World", 300, 300, ImgUtil.IMAGE_TYPE_PNG,response.getOutputStream());
        response.getOutputStream().close();;
    }

}
