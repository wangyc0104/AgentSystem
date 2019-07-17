package org.agent.action;

import java.util.List;

import net.sf.json.JSONArray;

import org.agent.common.Constants;
import org.agent.pojo.Area;
import org.agent.pojo.City;
import org.agent.pojo.Contact;
import org.agent.pojo.Custom;
import org.agent.pojo.Province;
import org.agent.pojo.SystemConfig;
import org.agent.service.contact.ContactService;
import org.agent.service.custom.CustomService;
import org.agent.service.provinceandcity.ProvinceAndCityService;

import com.opensymphony.xwork2.Action;

/**
 * 商户相关Action
 * @author Yicheng Wang
 */
@SuppressWarnings("serial")
public class CustomAction extends BaseAction {

	private Custom custom;
	private CustomService customService;
	private ContactService contactService;
	private ProvinceAndCityService provinceAndCityService;
	private List<SystemConfig> customTypeList = Constants.customTypeConfigList;
	private List<SystemConfig> cardTypeList = Constants.cardTypeConfigList;
	private List<Custom> customList;
	private List<Contact> contactList; // 从表单上来接收List
	private List<Province> provinceList;
	private List<City> cityList;
	private List<Area> areaList;
	private Province province;
	private City city;
	private Area area;

	public Custom getCustom() {
		return custom;
	}

	public void setCustom(Custom custom) {
		this.custom = custom;
	}

	public CustomService getCustomService() {
		return customService;
	}

	public void setCustomService(CustomService customService) {
		this.customService = customService;
	}

	public ContactService getContactService() {
		return contactService;
	}

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}

	public ProvinceAndCityService getProvinceAndCityService() {
		return provinceAndCityService;
	}

	public void setProvinceAndCityService(
			ProvinceAndCityService provinceAndCityService) {
		this.provinceAndCityService = provinceAndCityService;
	}

	public List<SystemConfig> getCustomTypeList() {
		return customTypeList;
	}

	public void setCustomTypeList(List<SystemConfig> customTypeList) {
		this.customTypeList = customTypeList;
	}

	public List<SystemConfig> getCardTypeList() {
		return cardTypeList;
	}

	public void setCardTypeList(List<SystemConfig> cardTypeList) {
		this.cardTypeList = cardTypeList;
	}

	public List<Custom> getCustomList() {
		return customList;
	}

	public void setCustomList(List<Custom> customList) {
		this.customList = customList;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	public List<Province> getProvinceList() {
		this.provinceList = this.getProvinceAndCityService().getProvinceList();
		return provinceList;
	}

	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String customList() {
		if(custom==null)
			custom = new Custom();
		// 限定当前用户的ID
		custom.setAgentId(this.getCurrentUser().getId());
		this.getPager().setPageSize(5);
		// 搜索用户
		custom.setStartNum((this.getPager().getPage()-1)*this.getPager().getPageSize());
		custom.setPageSize(this.getPager().getPageSize());
		this.getPager().setTotalCount(this.getCustomService().count(custom));
		this.getPager().setItems(this.getCustomService().getList(custom));
		return Action.SUCCESS;
	}

	// 添加客户
	public String addCustom() {
		// 代理商
		custom.setAgentCode(this.getCurrentUser().getUserCode());
		custom.setAgentId(this.getCurrentUser().getId());
		custom.setAgentName(this.getCurrentUser().getUserName());
		try {
			if (null != custom) {
				this.getCustomService().tx_addCustomContact(custom, contactList);
				this.setLog(getCurrentUser(), "用户[" + this.getCurrentUser().getUserCode() + "]进行客户添加操作：添加[" + custom.getCustomName() + "]客户");
			}
			return Action.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.INPUT;
	}

	// 加载城市
	public void loadCity() {
		String cjson = "";
		cityList = this.getProvinceAndCityService().getCitys(province);
		cjson = JSONArray.fromObject(cityList).toString();
		this.getOut().print(cjson);
	}

	// 加载区域
	public void loadArea() {
		String ajson = "";
		areaList = this.getProvinceAndCityService().getAreas(city);
		ajson = JSONArray.fromObject(areaList).toString();
		this.getOut().print(ajson);
	}

	// 验证客户是否存在
	public void isExistCustomName() {
		String result = "nopeat";
		try {
			int i = this.getCustomService().isExistCustomName(custom);
			if (i > 0)
				result = "peat"; // 发现重复
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
		}
		this.getOut().print(result);
	}
	
	// 查看客户信息
	public String viewCustom() {
		// 此处的custom.province、custom.city和custom.area都是ID，需要改成名字
		custom = customService.getCustomById(custom);
		if (province == null)
			province = new Province();
		if (city == null)
			city = new City();
		if (area == null)
			area = new Area();
		if (custom.getProvince().length()==6){
			province.setProvinceID(custom.getProvince());
			custom.setProvince(provinceAndCityService.getProvinceById(province).getProvince());
		}
		if (custom.getCity().length()==6){
			city.setCityID(custom.getCity());
			custom.setCity(provinceAndCityService.getCityById(city).getCity());
		}
		if (custom.getArea().length()==6){
			area.setAreaID(custom.getArea());
			custom.setArea(provinceAndCityService.getAreaById(area).getArea());
		}
		// 设置custom的联系人
		Contact ct = new Contact();
		ct.setCustomId(custom.getId());
		this.contactList = contactService.getContactList(ct);
		return Action.SUCCESS;
	}
	
	private String getCustomContactById() {
		custom = customService.getCustomById(custom);
		Contact ct = new Contact();
		ct.setCustomId(custom.getId());
		this.contactList = contactService.getContactList(ct);
		return Action.SUCCESS;
	}
	
	// 进入修改客户信息的页面
	public String modifyCustom() {
		return this.getCustomContactById();
	}
	
	// 提交修改客户信息
	public String modifySaveCustom() {
		// 保存修改
		// 代理商
		custom.setAgentCode(this.getCurrentUser().getUserCode());
		custom.setAgentId(this.getCurrentUser().getId());
		custom.setAgentName(this.getCurrentUser().getUserName());
		try {
			if (null != custom) {
				this.getCustomService().tx_modifyCustomContact(custom, contactList);
				this.setLog(getCurrentUser(), "用户[" + this.getCurrentUser().getUserCode() + "]进行客户修改操作：修改[" + custom.getCustomName() + "]客户");
			}
			customList();
			return Action.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Action.INPUT;
	}
	
	// 修改客户状态
	public void modifyCustomStatus() {
		this.getCustomService().modifyCustomStatus(custom); // 此处使用custom改变数据库的状态后，当前action中的customStatus仍没有变
		// 记录日志
		this.setLog(
			this.getCurrentUser(), 
			("用户进行客户状态修改：修改客户[" + custom.getCustomName() + "]的状态为：" + (custom.getCustomStatus() == 1 ? "停用" : "启用"))
		);
		this.customList();
		this.getOut().print("success");
	}
}
