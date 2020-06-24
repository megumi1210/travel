package com.huike.travel.dao.impl;

import com.huike.travel.dao.UserDao;
import com.huike.travel.domain.User;
import com.huike.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

  private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

  /**
   * 该方法用于通过用户名查找数据库表user中的数据
   *
   * @param name 用户名
   * @return
   */
  @Override
  public User findByUserName(String name) {

    // TODO 此处写JDBCTemplate操纵数据库的代码
    String sql = "select * from tab_user where username = ?";
    List<User> users =
        jdbcTemplate.query(
            sql,
            new Object[] {name},
                (rs, i) -> {
                  User user = new User();
                  user.setUid(rs.getInt("uid"));
                  user.setUsername(rs.getString("username"));
                  user.setPassword(rs.getString("password"));
                  user.setName(rs.getString("name"));
                  user.setBirthday(rs.getString("birthday"));
                  user.setSex(rs.getString("sex"));
                  user.setCode(rs.getString("code"));
                  user.setEmail(rs.getString("email"));
                  user.setTelephone(rs.getString("telephone"));
                  user.setStatus(rs.getString("status"));
                  return user;
                });

       return  users.size() == 1 ? users.get(0): null  ;
  }


    /**
   * 注册功能:实现保存用户到对应数据库表中的功能
   *
   * @param user
   * @return
   */
  @Override
  public boolean save(User user) {
    String sql =
        "insert into "
            + "tab_user(username,password,name,birthday,sex,telephone,email,status,code)"
            + "values(?,?,?,?,?,?,?,?,?)";
    try {
     int result= jdbcTemplate.update(
          sql,
          user.getUsername(),
          user.getPassword(),
          user.getName(),
          user.getBirthday(),
          user.getSex(),
          user.getTelephone(),
          user.getEmail(),
          user.getStatus(),
          user.getCode());

      return result ==1;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
