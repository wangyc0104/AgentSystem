package org.agent.dao.systemconfig;

import java.util.List;

import org.agent.pojo.SystemConfig;
import org.apache.ibatis.annotations.Param;

public interface SystemConfigMapper {

	// 获取查询配置项
	public List<SystemConfig> getSystemConfigs(SystemConfig systemConfig);

	/**
	 * 查询已启用的数据项
	 * 
	 * @param systemConfig
	 *            #{configValue}
	 * @return
	 */
	public List<SystemConfig> getSystemConfigsIsStart(SystemConfig systemConfig);

	public int addSystemConfig(SystemConfig systemConfig);

	public int modifySystemConfig(SystemConfig systemConfig);

	public int isPeatConfig(SystemConfig systemConfig);

	public int deleteSystemConfig(SystemConfig systemConfig);

	/**
	 * 一级编号固定时，最大的二级编号
	 * 
	 * @param type 使用方法 #{type} 超过2个以上就需要使用@Param
	 * @return
	 */
	public int maxTypeValueByType(@Param("type") int type);

}
