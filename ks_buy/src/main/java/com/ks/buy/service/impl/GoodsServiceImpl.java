package com.ks.buy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ks.buy.entity.Goods;
import com.ks.buy.mapper.GoodsMapper;
import com.ks.buy.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dCoder
 * @since 2022-03-28
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public boolean saveOne(Goods good) {
        QueryWrapper<Goods> qw = new QueryWrapper<>();
        qw.eq("good_url",good.getGoodUrl()).eq("good_name",good.getGoodName());
        Goods one = this.getOne(qw);
        if (one != null){
            return false;
        }
        return this.save(good);
    }
}
