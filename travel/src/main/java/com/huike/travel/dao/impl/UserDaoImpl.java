package com.huike.travel.dao.impl;

import com.huike.travel.dao.UserDao;
import com.huike.travel.domain.User;
import com.huike.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

  private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByUid(int uid) {
        String sql = "select * from tab_user where uid=?";
        List<User> users =query(sql,new Object[]{uid});
        return  users.size() == 1 ? users.get(0): null  ;
    }

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
      List<User> users = query(sql,new Object[]{name});

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


    @Override
    public int  updateUser(User user) {
        StringBuilder  baseSql = new StringBuilder("update  tab_user ");
        assert  user != null;
        assert  user.getUid() != 0;
        StringBuilder sql = new StringBuilder();

        if(user.getUsername() != null){
            sql.append(",username =").append("'").append(user.getUsername()).append("' ");
        }
        if(user.getPassword() != null){
            sql.append(",password =").append("'").append(user.getPassword()).append("' ");
        }
        if(user.getBirthday() != null){
            sql.append(",birthday =").append("'").append(user.getBirthday()).append("' ");
        }
        if(user.getEmail() != null){
            sql.append(",email =").append("'").append(user.getEmail()).append("' ");
        }
        if(user.getName() !=null){
            sql.append(",name =").append("'").append(user.getName()).append("' ");
        }
        if(user.getSex() != null){
            sql.append(",sex =").append("'").append(user.getSex()).append("' ");
        }
        if(user.getCode() != null){
            sql.append(",code =").append("'").append(user.getCode()).append("' ");
        }
        if(user.getStatus() != null){
            sql.append(",status =").append("'").append(user.getStatus()).append("' ");
        }
        if(user.getTelephone() != null){
            sql.append(",telephone =").append("'").append(user.getTelephone()).append("' ");
        }
        if(sql.toString().startsWith(",")){
           baseSql.append(" set ").append(sql.toString().substring(1));
        }
        else {
            return  1; //什么也不更新
        }
        String sqlBound= baseSql .append("where uid=").append(user.getUid()).toString();

        return  jdbcTemplate.update(sqlBound);
    }

    private List<User> query(String sql, Object[] args){
      return jdbcTemplate.query(
              sql,
              args,
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
  }
}
