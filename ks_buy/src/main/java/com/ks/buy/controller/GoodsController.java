package com.ks.buy.controller;


import com.atguigu.commonutils.R;
import com.ks.buy.entity.Goods;
import com.ks.buy.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dCoder
 * @since 2022-03-28
 */
@RestController
@RequestMapping("/buy")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @PostMapping("add")
    public R addGood(@RequestBody Goods good){
        boolean flag = goodsService.saveOne(good);
        R r = R.error();
        r.setMessage("该商品以存在");
        return flag?R.ok():r;
    }

    @DeleteMapping("delete")
    public R deleteGood(String goodId){
        return goodsService.removeById(goodId)?R.ok():R.error();
    }

    @GetMapping("list")
    public R list(){
        List<Goods> list = goodsService.list(null);
        return R.ok().data("list",list);
    }

}

