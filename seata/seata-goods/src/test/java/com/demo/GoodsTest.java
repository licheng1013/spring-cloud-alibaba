package com.demo;

import cn.hutool.json.JSONUtil;
import com.demo.entity.Goods;
import com.demo.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * @author root
 * @description TODO
 * @date 2021/3/20 11:38
 */
@SpringBootTest
@Slf4j
public class GoodsTest {
    @Autowired
    private GoodsService goodsService;

    @Test
    public void  test1(){
        Goods goods = goodsService.getById(1);
        goods.setTotal(goods.getTotal()-1);
        boolean b = goods.updateById();
        log.info("执行结果: {}",b);
    }

    @Test
    public void test2(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("secret", "6099aa2662a842359705cba8c1dc75eb");
        map.put("apikey", "82122648e6894f038640535735fa2b03");
        String str = JSONUtil.toJsonStr(map);
        System.out.println(str);
    }
}
