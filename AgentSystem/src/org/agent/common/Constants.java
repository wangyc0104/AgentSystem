package org.agent.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.agent.pojo.RoleFunctions;
import org.agent.pojo.SystemConfig;
import org.springframework.web.context.WebApplicationContext;

/**
 * 常数项
 * @author Yicheng Wang
 */
public class Constants {

	public static final long DAYS_5 = 24*60*60*1000*5L;
	public static WebApplicationContext ctx; // IOC容器
	public static final String SESSION_USER = "userSession";

	/**************** 系统日志 ***************/
	public static final String OPERATE_INFO_USER_LOGIN_SUCCESS = "用户进行登录操作成功";
	public static final String OPERATE_INFO_USER_LOGIN_FAILED = "用户进行登录操作失败";
	public static final String OPERATE_INFO_USER_LOGOUT_SUCCESS = "用户进行注销操作成功";
	public static final String OPERATE_INFO_USER_MODIFY_PASSWORD = "用户修改密码成功";
	
	/**************** 菜单 ***************/
	public static HashMap<Integer, ArrayList<RoleFunctions>> MENU = new HashMap<Integer, ArrayList<RoleFunctions>>();
	
	/**************** 系统配置项 ***************/
	// 所有配置项
	public static List<SystemConfig> systemConfigList;
	// 账务类型1
	public static List<SystemConfig> accountConfigList = new ArrayList<SystemConfig>();
	// 服务类型2
	public static List<SystemConfig> serviceConfigList = new ArrayList<SystemConfig>();
	// 服务年限3
	public static SystemConfig maxServiceYears;
	// APP地址4
	public static SystemConfig appMakeUrlConfig;
	// 客户类型5
	public static List<SystemConfig> customTypeConfigList = new ArrayList<SystemConfig>();
	// 证件类型6
	public static List<SystemConfig> cardTypeConfigList = new ArrayList<SystemConfig>();
	// 优惠类型7
	public static List<SystemConfig> youhuiConfigList = new ArrayList<SystemConfig>();

	// 遍历所有配置项，并按不同配置类型归类
	public static void configSystemData() {
		// 为了防止重复添加
		accountConfigList.clear();
		serviceConfigList.clear();
		customTypeConfigList.clear();
		cardTypeConfigList.clear();
		youhuiConfigList.clear();
		for (SystemConfig config : systemConfigList) {
			switch (config.getConfigType()) {
			case 1:
				accountConfigList.add(config);
				break;
			case 2:
				serviceConfigList.add(config);
				break;
			case 3:
				maxServiceYears=config;
				break;
			case 4:
				appMakeUrlConfig=config;
				break;
			case 5:
				customTypeConfigList.add(config);
				break;
			case 6:
				cardTypeConfigList.add(config);
				break;
			case 7:
				youhuiConfigList.add(config);
				break;
			}
		}
	}
}
