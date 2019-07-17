package org.agent.action;

import java.util.List;

import org.agent.common.Constants;
import org.agent.pojo.SystemConfig;
import org.agent.service.systemconfig.SystemConfigService;
import org.apache.log4j.Logger;

/**
 * 系统配置Action
 * @author Yicheng Wang
 *
 */
@SuppressWarnings("serial")
public class SystemConfigAction extends BaseAction {

	private Logger logger = Logger.getLogger(SystemConfigAction.class);

	// 由struts2-spring-plugin插件完成属性装配
	private SystemConfigService systemConfigService;

	public void setSystemConfigService(SystemConfigService systemConfigService) {
		this.systemConfigService = systemConfigService;
	}

	private Integer configType; // 当前的配置类型
	private String configTypeName;
	private SystemConfig systemConfig;
	private List<SystemConfig> systemConfigList;
	private List<SystemConfig> systemConfigListIsStart;

	public Integer getConfigType() {
		return configType;
	}

	public void setConfigType(Integer configType) {
		this.configType = configType;
	}

	public String getConfigTypeName() {
		return configTypeName;
	}

	public void setConfigTypeName(String configTypeName) {
		this.configTypeName = configTypeName;
	}

	public SystemConfig getSystemConfig() {
		return systemConfig;
	}

	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}

	public List<SystemConfig> getSystemConfigList() {
		return systemConfigList;
	}

	public void setSystemConfigList(List<SystemConfig> systemConfigList) {
		this.systemConfigList = systemConfigList;
	}

	public List<SystemConfig> getSystemConfigListIsStart() {
		return systemConfigListIsStart;
	}

	public void setSystemConfigListIsStart(
			List<SystemConfig> systemConfigListIsStart) {
		this.systemConfigListIsStart = systemConfigListIsStart;
	}

	private void loadConfig(Integer configType, String configTypeName, String info) {
		logger.info(info);
		SystemConfig _systemConfig = new SystemConfig();
		_systemConfig.setConfigType(configType);
		_systemConfig.setIsStart(1);
		this.systemConfigList = systemConfigService.getSystemConfigs(_systemConfig);
		this.systemConfigListIsStart = systemConfigService.getSystemConfigsIsStart(_systemConfig);
		this.configType = configType; // 在上下文中设置的configType
		this.configTypeName = configTypeName;
		if (this.systemConfigList != null && this.systemConfigList.size() == 1) {
			// 如果某一配置项有多个子配置的话，systemConfig无效
			this.systemConfig = this.systemConfigList.get(0);
		}
		// 保持Constants里的配置项为最新
		Constants.configSystemData();
	}

	public String caiwuType() {
		loadConfig(1, "财务类型", "查看财务类型配置信息");
		return SUCCESS;
	}

	public String serviceType() {
		loadConfig(2, "服务类型", "查看服务类型配置信息");
		return SUCCESS;
	}

	public String serviceYears() {
		loadConfig(3, "服务年限", "查看服务年限配置信息");
		return SUCCESS;
	}

	public String appUrl() {
		loadConfig(4, "APP地址", "查看APP地址配置信息");
		return SUCCESS;
	}

	public String customType() {
		loadConfig(5, "用户类型", "查看用户类型配置信息");
		return SUCCESS;
	}

	public String cardType() {
		loadConfig(6, "证件类型", "查看证件类型配置信息");
		return SUCCESS;
	}

	public String youhuiType() {
		loadConfig(7, "优惠类型", "查看优惠类型配置信息");
		return SUCCESS;
	}

	// 用于ajax请求
	public void addConfig() {
		if (this.systemConfigService.isPeatConfig(systemConfig) > 0)
			this.getOut().print("peat");
		else {
			Integer maxConfigTypeValue = this.systemConfigService
					.maxTypeValueByType(systemConfig.getConfigType());
			systemConfig.setConfigTypeValue(maxConfigTypeValue + 1);
			this.systemConfigService.addSystemConfig(systemConfig);
			this.getOut().print("success");
		}
	}

	// 删除
	public void deleteConfig() {
		if(this.systemConfigService.deleteSystemConfig(systemConfig)>0)
			this.getOut().print("success");
		else
			this.getOut().print("failed");
	}

	// 修改
	public void modifyConfig() {
		this.systemConfigService.modifySystemConfig(systemConfig);
		this.getOut().print("success");
	}
}
