package com.demo.controller;


import com.demo.service.GoodsService;

import javax.annotation.Resource;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

}