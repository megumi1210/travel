package com.huike.travel.dao.impl;

import com.huike.travel.dao.SellerDao;
import com.huike.travel.domain.Seller;
import com.huike.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SellerDaoImpl implements SellerDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findSellerBySid(int sid) {
        String sql = "select * from tab_seller where sid=?";
       List<Seller> sellers=jdbcTemplate.query(
                sql,
                new Object[]{sid},
               (resultSet, i) -> {
                  Seller s = new Seller();
                  s.setSid(resultSet.getInt("sid"));
                  s.setSname(resultSet.getString("sname"));
                  s.setAddress(resultSet.getString("address"));
                  s.setConsphone(resultSet.getString("consphone"));
                  return  s;
               });

       return  sellers.size() == 1 ? sellers.get(0) : null;
    }
}
