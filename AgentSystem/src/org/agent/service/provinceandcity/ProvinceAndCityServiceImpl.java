package org.agent.service.provinceandcity;

import java.util.List;

import org.agent.dao.provinceandcity.ProvinceAndCityMapper;
import org.agent.pojo.Area;
import org.agent.pojo.City;
import org.agent.pojo.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("provinceAndCityService")
public class ProvinceAndCityServiceImpl implements ProvinceAndCityService {

	@Autowired
	ProvinceAndCityMapper mapper;
	
	public ProvinceAndCityMapper getMapper() {
		return mapper;
	}
	public void setMapper(ProvinceAndCityMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Province> getProvinceList() {
		return mapper.getProvinceList();
	}

	@Override
	public List<City> getCitys(Province province) {
		return mapper.getCitys(province);
	}

	@Override
	public List<Area> getAreas(City city) {
		return mapper.getAreas(city);
	}
	
	@Override
	public Province getProvinceById(Province province) {
		return mapper.getProvinceById(province);
	}
	
	@Override
	public City getCityById(City city) {
		return mapper.getCityById(city);
	}
	
	@Override
	public Area getAreaById(Area area) {
		return mapper.getAreaById(area);
	}
	

}
