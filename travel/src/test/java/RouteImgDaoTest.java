import com.huike.travel.dao.RouteImgDao;
import com.huike.travel.dao.impl.RouteImgDaoImpl;
import org.junit.Test;

public class RouteImgDaoTest {

    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    @Test
    public void  testSelectByRid(){
    System.out.println(routeImgDao.getRouteImgByRid(1));
    }
}
