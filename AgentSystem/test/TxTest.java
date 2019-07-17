import java.util.Date;

import org.agent.pojo.User;
import org.agent.service.user.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TxTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-default.xml");
		UserService userService = ctx.getBean("userService", UserService.class);
		User user = new User();
		user.setUserCode("test1201");
		user.setUserName("test");
		user.setUserPassword("123");
		user.setCreationTime(new Date());
		user.setCreatedBy("admin");
		user.setRoleId(1);
		userService.tx_AddUser(user);
		System.out.println(user.getId());
	}

}
