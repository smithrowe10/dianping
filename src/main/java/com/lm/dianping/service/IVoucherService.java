package com.lm.dianping.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.dianping.dto.Result;
import com.lm.dianping.entity.Voucher;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IVoucherService extends IService<Voucher> {

    Result queryVoucherOfShop(Long shopId);

    void addSeckillVoucher(Voucher voucher);
}
