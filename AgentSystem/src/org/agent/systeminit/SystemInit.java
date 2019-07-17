package org.agent.systeminit;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.agent.common.Constants;
import org.agent.pojo.Function;
import org.agent.pojo.Permission;
import org.agent.pojo.Role;
import org.agent.pojo.RoleFunctions;
import org.agent.pojo.SystemConfig;
import org.agent.service.function.FunctionService;
import org.agent.service.permission.PermissionService;
import org.agent.service.role.RoleService;
import org.agent.service.systemconfig.SystemConfigService;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 系统初始化
 * @author Yicheng Wang
 *
 */
public class SystemInit implements ServletContextListener {

	private Logger logger = Logger.getLogger(SystemInit.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		/**************** 系统配置项 ***************/
		// 1.拿到IOC容器WebApplicationContext
		System.out.println("初始化 Spring WebApplicationContext 开始");
		Constants.ctx = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		if (null == Constants.ctx)
			System.out.println("初始化 Spring WebApplicationContext 失败");
		else
			System.out.println("初始化 Spring WebApplicationContext 完成");
		// 2.拿到systemConfigService
		if (Constants.ctx != null) {
			System.out.println("初始化配置项开始");
			SystemConfigService systemConfigService = (SystemConfigService) Constants.ctx.getBean("systemConfigService");
			SystemConfig systemConfig = new SystemConfig();
			systemConfig.setIsStart(1); // 仅使用已启用的配置项
			Constants.systemConfigList = systemConfigService.getSystemConfigsIsStart(systemConfig);
			Constants.configSystemData();
			logger.info("-------->" + Constants.accountConfigList.size());
			logger.info("-------->" + Constants.serviceConfigList.size());
			logger.info("-------->" + Constants.customTypeConfigList.size());
			logger.info("-------->" + Constants.cardTypeConfigList.size());
			logger.info("-------->" + Constants.youhuiConfigList.size());
			System.out.println("初始化配置项结束");

			System.out.println("初始化菜单项开始");
			RoleService roleService = Constants.ctx.getBean("roleService", RoleService.class);
			FunctionService functionService = Constants.ctx.getBean("functionService", FunctionService.class);
			PermissionService permissionService = Constants.ctx.getBean("permissionService", PermissionService.class);
			List<Role> roleList = roleService.getRoleIdAndNameList();
			List<Function> funcList; // 表示这个角色拥有的所有功能
			List<Function> menuFunctionList = functionService.getMenuFunction(); // 主菜单
			ArrayList<RoleFunctions> roleFunctionsList; // 每个角色拥有多个不同的菜单
			
			for (Role role : roleList) { 
				roleFunctionsList = new ArrayList<RoleFunctions>();
				// 角色 -> 权限 -> 功能
				funcList = new ArrayList<Function>();
				Permission permission = new Permission();
				permission.setRoleId(role.getId());
				permission.setIsStart(1);
				List<Permission> permissionList = permissionService.getList(permission);
				for (Permission p : permissionList) {
					Function function = new Function();
					function.setId(p.getFunctionId());
					function = functionService.getFunctionById(function);
					funcList.add(function);
				}
				List<Function> subFunction;
				for (Function mf : menuFunctionList) {
					RoleFunctions roleFunctions = new RoleFunctions();
					// 查找主菜单
					roleFunctions.setMainFunction(mf);
					// 查找子菜单
					subFunction = new ArrayList<Function>();
					if (funcList!=null && funcList.size()>0) {
						for (Function f : funcList) {
							if (f.getParentId().equals(mf.getId())) {
								subFunction.add(f);
							}
						}
					}
					roleFunctions.setSubFunctions(subFunction);
					// 将该角色的组合菜单放到组合菜单列表中
					roleFunctionsList.add(roleFunctions);
				}
				Constants.MENU.put(role.getId(), roleFunctionsList);
			}
			System.out.println("初始化菜单项结束");
		}
	}
}
