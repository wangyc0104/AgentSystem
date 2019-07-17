package org.agent.service.systemconfig;

import java.util.List;

import org.agent.pojo.SystemConfig;
import org.apache.ibatis.annotations.Param;

/**
 * 系统配置Service
 * @author Yicheng Wang
 */
public interface SystemConfigService {

	/**
	 * 获取特定的系统配置
	 * @param systemConfig
	 * @return
	 */
	public List<SystemConfig> getSystemConfigs(SystemConfig systemConfig);

	/**
	 * 查询已启用的数据项
	 * @param systemConfig #{configValue}
	 * @return
	 */
	public List<SystemConfig> getSystemConfigsIsStart(SystemConfig systemConfig);

	/**
	 * 添加系统配置项
	 * @param systemConfig
	 * @return
	 */
	public int addSystemConfig(SystemConfig systemConfig);

	/**
	 * 修改系统配置项
	 * @param systemConfig
	 * @return
	 */
	public int modifySystemConfig(SystemConfig systemConfig);

	/**
	 * 判断系统配置是否重复
	 * @param systemConfig
	 * @return
	 */
	public int isPeatConfig(SystemConfig systemConfig);

	/**
	 * 
	 * @param systemConfig
	 * @return
	 */
	public int deleteSystemConfig(SystemConfig systemConfig);

	/**
	 * 一级编号固定时，最大的二级编号
	 * @param type 使用方法 #{type} 超过2个以上就需要使用@Param
	 * @return
	 */
	public int maxTypeValueByType(@Param("type") int type);
	
}
