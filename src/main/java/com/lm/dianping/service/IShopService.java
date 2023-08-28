package com.lm.dianping.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.dianping.dto.Result;
import com.lm.dianping.entity.Shop;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IShopService extends IService<Shop> {

    Result queryById(Long id);

    Result update(Shop shop);

    Result queryShopByType(Integer typeId, Integer current, Double x, Double y);
}
