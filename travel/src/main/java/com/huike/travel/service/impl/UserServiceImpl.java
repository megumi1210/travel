package com.huike.travel.service.impl;

import com.huike.travel.dao.UserDao;
import com.huike.travel.dao.impl.UserDaoImpl;
import com.huike.travel.domain.User;
import com.huike.travel.service.UserService;
import com.huike.travel.util.Md5Util;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 该方法写真正的注册相关的逻辑代码
     * @param user 前段页面注册用户相关的数据
     * @return
     */
    @Override
    public boolean registerUser(User user) {
        //TODO逻辑代码  调用userDao中代码
        try{

            assert  user.getPassword() != null;
            String encodePass = Md5Util.encodeByMd5(user.getPassword());
            user.setPassword(encodePass);

            return userDao.save(user);
        }catch (Exception e){
            return false;
        }

    }





    @Override
    public boolean validUsername(String username) {
         assert  username != null;
         return  userDao.findByUserName(username) == null;
    }

    @Override
    public boolean login(String username, String password) {


        try{
            assert username!=null;
            assert password!=null;
            String encodePass = Md5Util.encodeByMd5(password);

            User user = userDao.findByUserName(username);
            if(user != null){
                return  user.getPassword().equals(encodePass);
            }else {
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public User findUserInfoBy(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User findUserByUid(int uid) {
        return userDao.findUserByUid(uid);
    }

    @Override
    public boolean activeUser(int uid, String code) {
        User user  = new User();
        user.setStatus("Y");
        user.setCode(code);
        user.setUid(uid);
        return  userDao.updateUser(user) == 1;
    }
}
