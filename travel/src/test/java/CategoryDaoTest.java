import com.huike.travel.dao.CategoryDao;
import com.huike.travel.dao.impl.CategoryDaoImpl;
import com.huike.travel.service.RouteService;
import com.huike.travel.service.impl.RouteServiceImpl;
import org.junit.Test;

public class CategoryDaoTest {
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private RouteService routeService = new RouteServiceImpl();

    @Test
    public void test01(){
    System.out.println(categoryDao.findAll());
    System.out.println(categoryDao.findCategoryByCid(1));

    System.out.println(routeService.findRouteByRid(8));
    }

}
