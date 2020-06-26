package com.huike.travel.dao.impl;

import com.huike.travel.dao.FavoriteDao;
import com.huike.travel.dao.RouteDao;
import com.huike.travel.dao.UserDao;
import com.huike.travel.domain.Favorite;
import com.huike.travel.domain.PageParam;
import com.huike.travel.domain.Route;
import com.huike.travel.domain.User;
import com.huike.travel.service.RouteService;
import com.huike.travel.service.impl.RouteServiceImpl;
import com.huike.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;


import java.sql.*;

import java.util.List;

public class FavoriteDaoImpl implements FavoriteDao {

    private final UserDao userDao = new UserDaoImpl();

    private final RouteService routeService = new RouteServiceImpl();

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Favorite> findFavoriteByPage(int uid , PageParam pageParam) {
        StringBuilder sql  = new StringBuilder("select * from tab_favorite where uid =? ");
        if(pageParam != null){
            sql.append(" limit ").append(pageParam.getStart()).append(" , ").append(pageParam.getPageSize());
        }
        return jdbcTemplate.query(sql.toString(),
                new Object[]{uid},
                (resultSet, i) -> {
                   Favorite f = new Favorite();
                    int uid1 = resultSet.getInt("uid");
                    int rid = resultSet.getInt("rid");
                    Date date =resultSet.getDate("date");

                    User user = userDao.findUserByUid(uid1);
                    Route route = routeService.findRouteByRid(rid);
                    f.setDate(date.toString());
                    f.setRoute(route);
                    f.setUser(user);
                    return  f;
                });
    }

    @Override
    public int getFavoriteTotalByUid(int uid) {
        String sql  =" select * from tab_favorite where uid =" + uid;
        return  countTemplate(sql);

    }


    @Override
    public int insertRecord(int rid, int uid) {
        String sql = "insert into tab_favorite(rid,date,uid) values(?,?,?)";
        java.util.Date  now = new java.util.Date();
        Date date = new Date(now.getTime());
        try{
            return jdbcTemplate.update(sql,rid,date,uid);
        }catch (Exception e){
            e.printStackTrace();
            return  0;
        }
    }

    @Override
    public int findFavorite(int rid, int uid) {
        String sql = "select * from tab_favorite where rid ="+ rid +" and uid= "+uid;
        return countTemplate(sql);
    }



    private  int countTemplate(String sql){  //嵌套查询数量的模板
        String countSql = "select count(*)  as total from( " + sql +" ) as total ";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs  = null;
        try {
            conn = jdbcTemplate.getDataSource().getConnection();
            stmt = conn.createStatement();
            rs =stmt.executeQuery(countSql);
            if (rs.next()) {return rs.getInt("total");}
            return  0;

        } catch (Exception e){
            e.printStackTrace();
            return 0;
        } finally {

            try {
                if(rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
