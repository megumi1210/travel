package com.huike.travel.dao.impl;

import com.huike.travel.dao.RouteImgDao;
import com.huike.travel.domain.RouteImg;
import com.huike.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RouteImgDaoImpl implements RouteImgDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImg> getRouteImgByRid(int rid) {

        String sql = "select * from tab_route_img where rid =?";

        return jdbcTemplate.query(sql,
                new Object[]{rid}, (resultSet, i) -> {
                    RouteImg   img = new RouteImg();
                    img.setRgid(resultSet.getInt("rgid"));
                    img.setRid(rid);
                    img.setBigPic(resultSet.getString("bigPic"));
                    img.setSmallPic(resultSet.getString("smallPic"));
                    return  img;
                });
    }
}
