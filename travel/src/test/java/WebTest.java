import com.huike.travel.util.WebUtils;
import org.junit.Test;

public class WebTest {
    @Test
    public void test(){
    System.out.println(WebUtils.getPathVariable("username=55","username"));
    }
}
