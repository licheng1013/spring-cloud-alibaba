package com.demo;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpUtil;

import java.util.HashMap;

/**
 * @author root
 * @description TODO
 * @date 2021/1/23 11:00
 */
public class SeataTest {
    public static void main(String[] args) {
        ConcurrencyTester tester = ThreadUtil.concurrencyTest(10, () -> {
            String post = HttpUtil.post("http://localhost:9600/order/create",new HashMap<>());
            Console.log(post);
        });

        // 获取总的执行时间，单位毫秒
        Console.log(tester.getInterval());
    }
}
