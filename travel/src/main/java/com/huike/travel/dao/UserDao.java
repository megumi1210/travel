package com.huike.travel.dao;

import com.huike.travel.domain.User;

/**
 * 该接口用于访问数据库user
 */
public interface UserDao {


    User findUserByUid(int uid);

    /**
     * 通过用户名查找用户
     * @param name 用户名
     * @return 代表数据库返回的user对象
     */
    User findByUserName(String name);


    /**
     * 用于增加数据库表user的数据
     * @param user
     * @return
     */
    boolean save(User user);


    int updateUser(User user);

}
