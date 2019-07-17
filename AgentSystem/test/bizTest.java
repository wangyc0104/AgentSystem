import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.agent.dao.function.FunctionMapper;
import org.agent.dao.role.RoleMapper;
import org.agent.dao.systemconfig.SystemConfigMapper;
import org.agent.pojo.Contact;
import org.agent.pojo.Custom;
import org.agent.pojo.Function;
import org.agent.pojo.Logs;
import org.agent.pojo.Permission;
import org.agent.pojo.Role;
import org.agent.pojo.SystemConfig;
import org.agent.service.custom.CustomService;
import org.agent.service.logs.LogsService;
import org.agent.service.permission.PermissionService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.Action;

public class bizTest {

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-default.xml");
		System.out.println("以下是Spring的bean：");
		for (String beanName : ctx.getBeanDefinitionNames())
			System.out.println(beanName);

		SystemConfigMapper mapper = (SystemConfigMapper) ctx.getBean("systemConfigMapper");
		List<SystemConfig> list = mapper.getSystemConfigs(new SystemConfig());
		System.out.println(list.size());

		RoleMapper roleMapper = (RoleMapper) ctx.getBean("roleMapper");
		List<Role> listRole = roleMapper.getRoleIdAndNameList();
		for (Role r : listRole)
			System.out.println(r.getId() + r.getRoleName() + r.getCreatedBy());

		FunctionMapper fMapper = (FunctionMapper) ctx.getBean("functionMapper");
		for (Function f : fMapper.getFunctionList())
			System.out.println(f.getFunctionName());

		PermissionService permissionService = (PermissionService) ctx.getBean("permissionService");
		Permission permission = new Permission();
		permission.setRoleId(1);
		permission.setIsStart(1);
		System.out.println(permissionService.getList(permission).size());

		LogsService logsService = (LogsService) ctx.getBean("logsService");
		Logs logs = new Logs();
		logs.setUserId(2);
		logs.setUserName("admin");
		logs.setOperateDatetime("2018-06-03");
		logs.setOdt("2018-06-03");
		List<Logs> logsList = logsService.getList(logs);
		System.out.println(logsList.size());

		// 添加客户测试
		/**/
		CustomService customService = (CustomService) ctx.getBean("customService");
		Custom custom = new Custom();
		List<Contact> contactList = new ArrayList<Contact>();
		Contact contact = new Contact();
		contact.setContactName("WangYicheng");
		contactList.add(contact);
		custom.setAgentCode("admin");
		custom.setAgentId(2);
		custom.setAgentName("系统管理员");
		// custom.setRegDatetime("2018-6-23");
		customService.tx_addCustomContact(custom, contactList);

	}
}
