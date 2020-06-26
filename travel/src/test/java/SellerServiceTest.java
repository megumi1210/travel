import com.huike.travel.domain.Seller;
import com.huike.travel.service.SellerService;
import com.huike.travel.service.impl.SellerServiceImpl;
import org.junit.Test;

public class SellerServiceTest {
    private SellerService sellerService = new SellerServiceImpl();

    @Test
    public void test01(){
    System.out.println(sellerService.findSellerBySid(1));
    System.out.println(sellerService.findSellerBySid(-1));
    }
}
