package com.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.GoodsDao;
import com.demo.entity.Goods;
import com.demo.util.ResultHolder;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.io.Serializable;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Slf4j
@DubboService
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

    /**
     * @author lc
     * @date 2021/2/23
     * @description 锁定资源,检查资源
     */
    @Override
    public boolean updateTotal(BusinessActionContext actionContext,Serializable goodsId, Integer num) {
        String xid = actionContext.getXid();
        System.out.println("try Goods xid:" + xid);
        Goods byId = getById(goodsId);
        if (byId == null)
            throw new RuntimeException("商品找不到");
        byId.setTotal(byId.getTotal() - num);
        ResultHolder.set(xid, "goods");
        return byId.updateById();
    }

    /**
     * @author lc
     * @date 2021/2/23
     * @description 释放锁定的资源,并成功提交,业务上失败则短信通知
     */
    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        String v = ResultHolder.get(xid);
        if (v == null) {
            return true; //空回滚
        }
        System.out.println("Goods xid:" + xid);
        ResultHolder.remove(xid); //处理后删除
        return true;
    }

    /**
     * @author lc
     * @date 2021/2/23
     * @description 回滚操作,业务上失败则短信通知
     */
    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        String v = ResultHolder.get(xid);
        if (v == null) {
            return true; //空回滚
        }
        System.out.println("Goods xid:" + xid);
        ResultHolder.remove(xid); //处理后删除
        return true;
    }

    @Override
    public Integer getMoney(Integer goodsId) {
        System.out.println(goodsId);
        Goods byId = getById(goodsId);
        if (byId == null) {
            throw new RuntimeException("商品未找到");
        }
        return byId.getMoney();
    }
}