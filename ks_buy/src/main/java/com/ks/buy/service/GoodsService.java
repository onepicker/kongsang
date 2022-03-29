package com.ks.buy.service;

import com.ks.buy.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dCoder
 * @since 2022-03-28
 */
public interface GoodsService extends IService<Goods> {

    boolean saveOne(Goods good);
}
