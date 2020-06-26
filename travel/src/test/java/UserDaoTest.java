import com.huike.travel.dao.UserDao;
import com.huike.travel.dao.impl.UserDaoImpl;
import com.huike.travel.domain.User;
import com.huike.travel.service.impl.UserServiceImpl;
import com.huike.travel.util.UuidUtil;
import org.junit.Test;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();


    UserServiceImpl userService = new UserServiceImpl();
    @Test
    public void testInsert(){
          User user = new User();
          user.setName("root");
          user.setUsername("megumi");
          user.setPassword("123456");
          user.setTelephone("123");
          user.setCode("code");
          user.setEmail("123@com");
          user.setBirthday("1997-12-10");
          user.setSex("男");
          user.setStatus("1");
        userDao.save(user);
        // System.out.println( );
    }


    @Test
    public void testSelect(){
    System.out.println(userDao.findByUserName("root"));
    }

    @Test
    public void  testUserService(){
        User user = new User();
        user.setName("root2");
        user.setUsername("megumi2");
        user.setPassword("123456");
        user.setTelephone("123");
        user.setCode("code2");
        user.setEmail("123@com");
        user.setBirthday("1997-12-10");
        user.setSex("男");
        user.setStatus("1");
         userService.registerUser(user);
    }

    @Test
    public void testFindUserService(){
    System.out.println(userService.validUsername("megumi"));
    System.out.println(userService.validUsername("sdfsdfsdf"));
    }

    @Test
    public void testLogin(){
    System.out.println(userService.login("megumi2","123456"));
    System.out.println(userService.findUserByUid(21));

    System.out.println(userService.findUserInfoBy("2984905969@qq.com").getEmail());
    }


    @Test
    public void testUpdate(){
        User user = new User();
        user.setUid(35);
        user.setName("陈科杰");
        user.setSex("女");
        int result = userDao.updateUser(user);
        System.out.println(result);

        User root = new User();
        root.setUid(21);
        root.setStatus("Y");
        root.setCode(UuidUtil.getUuid());
    System.out.println(userDao.updateUser(root));
    }


}
