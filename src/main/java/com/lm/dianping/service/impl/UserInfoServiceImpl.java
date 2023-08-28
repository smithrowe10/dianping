package com.lm.dianping.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.dianping.entity.UserInfo;
import com.lm.dianping.mapper.UserInfoMapper;
import com.lm.dianping.service.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-24
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
