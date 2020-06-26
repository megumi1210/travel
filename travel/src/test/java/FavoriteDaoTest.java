import com.huike.travel.dao.FavoriteDao;
import com.huike.travel.dao.impl.FavoriteDaoImpl;
import com.huike.travel.domain.PageParam;
import com.huike.travel.service.FavoriteService;
import com.huike.travel.service.impl.FavoriteServiceImpl;
import org.junit.Test;

public class FavoriteDaoTest {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    @Test
    public void testFavorite(){
    System.out.println(favoriteDao.findFavoriteByPage(21,null));
    System.err.println(favoriteDao.getFavoriteTotalByUid(0));
    System.out.println(favoriteDao.getFavoriteTotalByUid(21));
        PageParam pageParam = new PageParam();
        pageParam.setPageNum(1);
        pageParam.setPageSize(2);
    System.out.println(favoriteDao.findFavoriteByPage(21,pageParam));

    System.out.println(favoriteDao.findFavoriteByPage(1,pageParam));
    System.out.println(favoriteService.findFavoriteRoutesByPage(1,null));
    System.out.println(favoriteService.findFavoriteRoutesByPage(21,pageParam));

    System.out.println(favoriteService.insertRecord(7,21));

    }

    @Test
    public void test02(){
    System.out.println(favoriteService.alreadyStared(1,21));
    System.out.println(favoriteService.alreadyStared(12,21));
    System.out.println(favoriteService.alreadyStared(1,-1));
    }
}
