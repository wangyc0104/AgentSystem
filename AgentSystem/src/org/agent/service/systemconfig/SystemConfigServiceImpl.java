package org.agent.service.systemconfig;

import java.util.List;

import org.agent.dao.systemconfig.SystemConfigMapper;
import org.agent.pojo.SystemConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService {

	@Autowired
	private SystemConfigMapper mapper;
	
	public SystemConfigMapper getMapper() {
		return mapper;
	}

	public void setMapper(SystemConfigMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<SystemConfig> getSystemConfigs(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		return mapper.getSystemConfigs(systemConfig);
	}

	@Override
	public List<SystemConfig> getSystemConfigsIsStart(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		return mapper.getSystemConfigsIsStart(systemConfig);
	}

	@Override
	public int addSystemConfig(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		return mapper.addSystemConfig(systemConfig);
	}

	@Override
	public int modifySystemConfig(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		return mapper.modifySystemConfig(systemConfig);
	}

	@Override
	public int isPeatConfig(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		return mapper.isPeatConfig(systemConfig);
	}

	@Override
	public int deleteSystemConfig(SystemConfig systemConfig) {
		// TODO Auto-generated method stub
		return mapper.deleteSystemConfig(systemConfig);
	}

	@Override
	public int maxTypeValueByType(@Param("type") int type) {
		// TODO Auto-generated method stub
		return mapper.maxTypeValueByType(type);
	}

}
