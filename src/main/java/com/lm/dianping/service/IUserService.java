package com.lm.dianping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.dianping.dto.LoginFormDTO;
import com.lm.dianping.dto.Result;
import com.lm.dianping.entity.User;
import jakarta.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
public interface IUserService extends IService<User> {

    Result sendCode(String phone, HttpSession session);

    Result login(LoginFormDTO loginForm, HttpSession session);

    Result loginOut();

    Result sign();

    Result signCount();

}
