package org.agent.service.provinceandcity;

import java.util.List;

import org.agent.pojo.Area;
import org.agent.pojo.City;
import org.agent.pojo.Province;

/**
 * 地址信息Service
 * @author Yicheng Wang
 *
 */
public interface ProvinceAndCityService {

	/**
	 * 获取省份列表
	 * @return
	 */
	public List<Province> getProvinceList();

	/**
	 * 获取城市列表
	 * @param province
	 * @return
	 */
	public List<City> getCitys(Province province);

	/**
	 * 获取区县列表
	 * @param city
	 * @return
	 */
	public List<Area> getAreas(City city);

	/**
	 * 根据id获取省份
	 * @param province
	 * @return
	 */
	public Province getProvinceById(Province province);

	/**
	 * 根据id获取城市
	 * @param city
	 * @return
	 */
	public City getCityById(City city);
	
	/**
	 * 根据id获取区县
	 * @param area
	 * @return
	 */
	public Area getAreaById(Area area);
	
}
