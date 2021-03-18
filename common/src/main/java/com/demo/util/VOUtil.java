package com.demo.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;

/**
 * @author root
 * @description TODO
 * @date 2021/3/18 9:52
 */
public class VOUtil {

    /**
     * @param page 剔除page的不需要字段,减少字节传输
     * @return 结构对象
     */
    public static JsonResult<HashMap<String,Object>> VOPage(Page<?> page){
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("total", page.getTotal());
        map.put("records", page.getRecords());
        return JsonResult.okData(map);
    }
}
