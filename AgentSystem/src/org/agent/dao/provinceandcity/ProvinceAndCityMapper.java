package org.agent.dao.provinceandcity;

import java.util.List;

import org.agent.pojo.Area;
import org.agent.pojo.City;
import org.agent.pojo.Province;

public interface ProvinceAndCityMapper {

	public List<Province> getProvinceList();

	public List<City> getCitys(Province province);

	public List<Area> getAreas(City city);

	public Province getProvinceById(Province province);

	public City getCityById(City city);
	
	public Area getAreaById(Area area);
	
}
