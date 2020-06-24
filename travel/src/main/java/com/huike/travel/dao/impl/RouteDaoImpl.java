package com.huike.travel.dao.impl;

import com.huike.travel.dao.RouteDao;
import com.huike.travel.domain.PageParam;
import com.huike.travel.domain.PriceParam;
import com.huike.travel.domain.Route;
import com.huike.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class RouteDaoImpl implements RouteDao {

  private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

  private Map<CacheKey, String> cache = new ConcurrentHashMap<>();

  private List<Route> query(String sql) {
    return jdbcTemplate.query(
        sql,
        (rs, i) -> {
          Route r = new Route();
          r.setRid(rs.getInt("rid"));
          r.setRname(rs.getString("rname"));
          r.setPrice(rs.getDouble("price"));
          r.setRouteIntroduce(rs.getString("routeIntroduce"));
          r.setRflag(rs.getString("rflag"));
          r.setRdate(rs.getString("rdate"));
          r.setIsThemeTour(rs.getString("isThemeTour"));
          r.setCount(rs.getInt("count"));
          r.setCid(rs.getInt("cid"));
          r.setRimage(rs.getString("rimage"));
          r.setSid(rs.getInt("sid"));
          r.setSourceId(rs.getString("sourceId"));

          return r;
        });
  }

  @Override
  public List<Route> findRoutesByPage(Route route, PriceParam priceParam, PageParam pageParam) {

    CacheKey key = new CacheKey(route, priceParam, pageParam);
    String sql = cache.get(key);
    if (sql == null) {
      sql = sqlFactory(route, priceParam, pageParam);
      cache.put(key, sql);
    }
    return query(sql);
  }

  public int getTotal(Route route, PriceParam priceParam, PageParam pageParam) {
    CacheKey key = new CacheKey(route, priceParam, null);
    String sql = cache.get(key);
    if (sql == null) {
      sql = sqlFactory(route, priceParam, null);
      cache.put(key, sql);
    }

    String  countSql = "select count(*) as total from ( "+ sql +" ) as total" ;

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

  // 多查询sql 生成工厂
  public String sqlFactory(Route route, PriceParam priceParam, PageParam pageParam) {
    StringBuilder sql = new StringBuilder("select * from tab_route where 1 =1 ");
    if (route != null) {
      if (route.getCid() != 0) { // 通过分类id查找
        sql.append(" and cid = ").append(route.getCid()).append(" ");
      }

      if (route.getRname() != null) { // 根据名称模糊查找
        sql.append(" and rname like concat('%','").append(route.getRname()).append("','%') ");
      }
    }

    if (priceParam != null) {
      if (priceParam.getStart() != 0) {
        sql.append(" and price > ").append(priceParam.getStart());
      }

      if (priceParam.getEnd() != 0) {
        sql.append(" and price < ").append(priceParam.getEnd());
      }
    }
    if (pageParam != null) {
      sql.append(" limit ")
          .append(pageParam.getStart())
          .append(", ")
          .append(pageParam.getPageSize());
    }
    return sql.toString();
  }

  // 缓存key
  private static class CacheKey {

    private Route route;
    private PriceParam priceParam;
    private PageParam pageParam;

    public CacheKey(Route route, PriceParam priceParam, PageParam pageParam) {
      this.route = route;
      this.priceParam = priceParam;
      this.pageParam = pageParam;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CacheKey cacheKey = (CacheKey) o;
      return Objects.equals(route, cacheKey.route)
          && Objects.equals(priceParam, cacheKey.priceParam)
          && Objects.equals(pageParam, cacheKey.pageParam);
    }

    @Override
    public int hashCode() {
      return Objects.hash(route, priceParam, pageParam);
    }
  }
}
