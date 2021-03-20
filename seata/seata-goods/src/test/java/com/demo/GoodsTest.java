package com.demo;

import com.demo.entity.Goods;
import com.demo.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
