package com.demo.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.PassToken;
import com.demo.TokenUtil;
import com.demo.annotation.ApiTotal;
import com.demo.annotation.Cache;
import com.demo.annotation.Lock;
import com.demo.entity.User;
import com.demo.exception.ServiceException;
import com.demo.feign.OrderFeign;
import com.demo.service.UserService;
import com.demo.util.JsonResult;
import com.demo.util.RedisString;
import com.demo.util.VOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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

    @PostMapping("login")
    @PassToken
    public JsonResult<String> login(String username, String password) {
        log.info(username);
        log.info(password);
        if ("admin".equals(username) && "admin".equals(password)) {
            return JsonResult.okData(TokenUtil.getToken("1"));
        }
        throw new RuntimeException("账号或密码错误");
    }

    @PostMapping("create")
    @PassToken
    public JsonResult<String> create(User user) {
        User tel = userService.getOne(new QueryWrapper<User>().eq("tel", user.getTel()));
        if (tel == null && StrUtil.isNotBlank(user.getTel())) {
            user.insert();
            return JsonResult.okMsg("创建成功");
        }
        return JsonResult.fail("创建失败");
    }

    @PostMapping("add")
    @PassToken
    public JsonResult<String> add() {
        String tel = RandomUtil.randomNumbers(11);
        User user = userService.getOne(new QueryWrapper<User>().eq("tel", tel));
        if (user == null) {
            user = new User();
            user.setTel(tel);
            user.setUserName(tel+"大熊");
            user.setNickName("超强哈市奇");
            user.insert();
            return JsonResult.fail("创建成功");
        }
        return JsonResult.fail("创建失败");
    }

    @GetMapping("list")
    @PassToken
    @Cache
    @ApiTotal
    public JsonResult<HashMap<String, Object>> list(Page<User> page, User user) {
//        log.info("请求收到: {}", "list");
        return VOUtil.VOPage(user.selectPage(page, new QueryWrapper<>(user).orderByDesc("create_time")));
    }

    @GetMapping("port")
    @PassToken
    public JsonResult<String> port() {
        return JsonResult.okData(port);
    }


    @PostMapping("update")
    public JsonResult<String> update(User user) {
        log.info("请求收到: {}", "update");
        user.updateById();
        return JsonResult.okMsg("更新成功");
    }

    @PostMapping("delete")
    public JsonResult<String> delete(@RequestParam List<String> idList) {
        log.info("请求收到: {}", "delete");
        userService.removeByIds(idList);
        return JsonResult.okMsg("删除成功");
    }

    @Autowired
    private OrderFeign orderFeign;

    /**
     * 接口测试
     **/
    @GetMapping("test")
    @PassToken
    @Lock(prefix = "createOrder:", exception = ServiceException.class)
    public JsonResult<String> test(String userId, String goodsId) {
        int i = 1 / 0;
        return JsonResult.okMsg("Hello World " + port);
    }

    /**
     * openfeign调用测试
     **/
    @GetMapping("feign/test")
    @PassToken
    public JsonResult<String> feignTest() {
        log.info("feign调用: {}", port);
        return JsonResult.okData(orderFeign.test());
    }

    /**
     * 异常测试
     **/
    @GetMapping("exception")
    public JsonResult<String> exception() {
        int i = 1 / 0;
        return JsonResult.okMsg("Hello exception");
    }

    /**
     * openfeign异常调用测试
     **/
    @GetMapping("feign/exception")
    public JsonResult<String> feignException() {
        return orderFeign.exception();
    }


    @Autowired
    private RedisString redisString;

    @GetMapping("redis")
    @PassToken
    @ApiTotal
    public JsonResult<String> redis(){
        return JsonResult.okData(redisString.get("hi"));
    }

    /**
     * 随机验证码
     **/
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

    /**
     * 二维码
     **/
    @GetMapping("qrCode")
    public void qrCode(HttpServletResponse response) throws IOException {
        QrCodeUtil.generate("Hello World", 300, 300, ImgUtil.IMAGE_TYPE_PNG, response.getOutputStream());
        response.getOutputStream().close();
        ;
    }





}
