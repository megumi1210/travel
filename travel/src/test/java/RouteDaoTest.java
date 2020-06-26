import com.huike.travel.dao.RouteDao;
import com.huike.travel.dao.impl.RouteDaoImpl;
import com.huike.travel.domain.Order;
import com.huike.travel.domain.PageParam;
import com.huike.travel.domain.PriceParam;
import com.huike.travel.domain.Route;
import com.huike.travel.service.RouteService;
import com.huike.travel.service.impl.RouteServiceImpl;
import org.junit.Test;

public class RouteDaoTest {

  private RouteDao routeDao = new RouteDaoImpl();

  private RouteService routeService = new RouteServiceImpl();

  @Test
  public void testRouteDao() {
    System.out.println(routeDao.findRoutesByPage(null, null,null));
    System.out.println(routeDao.getTotal(null,null,null));
    Route route = new Route();
    route.setRname("春节");
    route.setCid(5);
    System.out.println(routeDao.findRoutesByPage(route, null,null));


    PriceParam priceParam = new PriceParam(0,300);
    System.err.println(routeDao.findRoutesByPage(null,priceParam,null));

    PageParam pageParam = new PageParam();
    pageParam.setPageSize(1);
    pageParam.setPageNum(-1);
    System.out.println(routeDao.findRoutesByPage(route,null,pageParam));

  }

  @Test
  public void testRouteService(){
    System.out.println(routeService.findRoutByPage(null,null,null));
    Route route = new Route();
    route.setRname("春节");
    route.setCid(5);
    PriceParam priceParam = new PriceParam(0,300);
    System.out.println(routeService.findRoutByPage(null,priceParam,null));

    System.err.println(routeService.findRoutByPage(null,null,new PageParam(5,6)));
  }

  @Test
  public void testOrderBy(){
    PageParam pageParam = new PageParam();
    pageParam.setUseOrderBy(true);
    pageParam.setOrderByName("count");
    pageParam.setOrder(Order.DESC);
    System.out.println(routeDao.findRoutesByPage(null,null,pageParam));
  }

  @Test
  public void testUpdate(){
    System.out.println(routeDao.updateCount(1,1));
    System.out.println(routeDao.updateCount(-1,-1));
    System.out.println(routeDao.updateCount(2,1));
    System.out.println(routeService.updateCount(1,-1));
  }
}
