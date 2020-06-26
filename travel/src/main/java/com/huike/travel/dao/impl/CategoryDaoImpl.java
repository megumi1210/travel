package com.huike.travel.dao.impl;

import com.huike.travel.dao.CategoryDao;
import com.huike.travel.domain.Category;
import com.huike.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Category findCategoryByCid(int cid) {

        String sql = "select * from tab_category where cid=?";
        List<Category> categoryList = jdbcTemplate.query(
                sql,
                new Object[]{cid},
                (resultSet, i) -> {
                    Category c = new Category();
                    c.setCid(cid);
                    c.setCname(resultSet.getString("cname"));
                    return c;
                }
        );

        return  categoryList.size() ==1 ? categoryList.get(0) : null;
    }


    @Override
    public List<Category> findAll() {

        String sql = "select * from tab_category order by cid ";

        return jdbcTemplate.query(
                sql,
                (resultSet, i) -> {
                    Category c = new Category();
                    c.setCid(resultSet.getInt("cid"));
                    c.setCname(resultSet.getString("cname"));
                    return c;
                }
        );
    }
}
