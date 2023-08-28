package com.lm.dianping.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.dianping.dto.Result;
import com.lm.dianping.entity.ShopType;
import com.lm.dianping.mapper.ShopTypeMapper;
import com.lm.dianping.service.IShopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.lm.dianping.utils.RedisConstants.SHOP_TYPE_LIST_KEY;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public Result queryShopTye() {
        String key = SHOP_TYPE_LIST_KEY + RandomUtil.randomString(6);
        // 查询缓存
        List<String> typelist = stringRedisTemplate.opsForList().range(key, 0, 9);
        // 存在，则直接返回
        if(typelist!=null&&!typelist.isEmpty()){
            ArrayList<ShopType> shopTypeArrayList = new ArrayList<>();
            for(String list:typelist){
                shopTypeArrayList.add(JSONUtil.toBean(list, ShopType.class));
            }
            return Result.ok(shopTypeArrayList);
        }

        // 若缓存不存在，则查询数据库
        List<ShopType> shopTypes = query().orderByAsc("sort").list();
        // 若数据库中不存在
        if(shopTypes==null||shopTypes.isEmpty()){
            return Result.fail("商品分类不存在！");
        }
        // 否则更新缓存
        for(ShopType shopType:shopTypes){
            stringRedisTemplate.opsForList().rightPush(key,JSONUtil.toJsonStr(shopType));
        }

        // 返回查询结果
        return Result.ok(shopTypes);
    }
}
