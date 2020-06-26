package com.huike.travel.service;

import com.huike.travel.domain.User;

/**
 * 接口:用户接口
 */
public interface UserService {

    /**
     * 该接口用于注册用户
     * @param user 前段页面注册用户相关的数据
     * @return false:该用户已经被注册  true 没有注册
     */
    boolean registerUser(User user);

     boolean validUsername(String username);

    boolean login(String username,String password);

     User findUserInfoBy(String username);

     User findUserByUid(int uid);

     boolean activeUser(int uid , String code);

}
