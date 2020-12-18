package com.demo.mail;

import cn.hutool.extra.mail.MailUtil;

/**
 * @author lc
 * @description TODO
 * @date 2020/12/18 14:41
 */
public class MailTest {
    public static void main(String[] args) {
        // 邮件发送 配置文件 mail.setting
        MailUtil.send("2501093733@qq.com", "测试", "Hello World",false );
    }
}
