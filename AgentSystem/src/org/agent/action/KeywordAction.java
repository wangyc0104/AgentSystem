package org.agent.action;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.agent.common.Constants;
import org.agent.common.SQLTools;
import org.agent.pojo.Account;
import org.agent.pojo.Custom;
import org.agent.pojo.Keywords;
import org.agent.pojo.SystemConfig;
import org.agent.service.account.AccountService;
import org.agent.service.custom.CustomService;
import org.agent.service.keywords.KeywordsService;
import org.agent.service.systemconfig.SystemConfigService;
import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.Action;

/**
 * 关键词管理Action
 * @author Yicheng Wang
 *
 */
@SuppressWarnings("serial")
public class KeywordAction extends BaseAction {
	
	/** 由Struts管理 */
	private Keywords keywords;
	private List<Keywords> keywordsList;
	private Custom custom;
	private List<Custom> customList;
	private Account account;
	private String p;
	
	/** IOC管理 */
	private KeywordsService keywordsService;
	private CustomService customService;
	private AccountService accountService;
	private SystemConfigService systemConfigService;
	
	/** 手动管理 */
	private SystemConfig maxServiceYears;
	private List<SystemConfig> serviceType;
	private List<SystemConfig> youhuiType;
	private SystemConfig systemConfig;
	
	public String init() {
		serviceType = Constants.serviceConfigList;
		youhuiType = Constants.youhuiConfigList;
		maxServiceYears = Constants.maxServiceYears;
		account = new Account();
		account.setUserId(this.getCurrentUser().getId());
		account = this.getAccountService().getAccount(account);
		return Action.SUCCESS;
	}

	public void searchCustom() {
		custom.setAgentId(this.getCurrentUser().getId());
		custom.setCustomStatus(1);
		customList = this.getCustomService().getCustomBySearch(custom);
		this.getOut().print(JSONArray.fromObject(customList).toString());
	}
	
	/**
	 * 验证关键词是否被占用
	 */
	public void valiKeywords() {
		String result = "failed";
		Keywords _k = this.keywordsService.getKeywordsByKeyword(keywords);
		if (_k == null)
			result = "success";
		this.getOut().print(result);
	}
	
	/**
	 * 自动计算金额（根据服务类型与服务年限）
	 */
	public void jisuan() {
		String result = "exception";
		result = price();
		this.getOut().print(result);
	}
	
	/**
	 * @return "exception":发生错误
	 */
	private String price() {
		String result = "exception";
		// 计算
		Integer serviceTypeId = 0;
		Integer serviceYears = 0;
		if (p != null) {
			String[] params = StringUtils.split(p, "-");
			// 获取服务类型中的单价
			serviceTypeId = Integer.valueOf(params[0]);
			SystemConfig systemConfig = new SystemConfig();
			systemConfig.setId(serviceTypeId);
			List<SystemConfig> serviceTypeConfigList = this.systemConfigService.getSystemConfigs(systemConfig);
			BigDecimal st = new BigDecimal(serviceTypeConfigList.get(0).getConfigValue()); // <--这一步获取服务单价
			// 获取服务年限
			if (!params[1].contains("id_")) {
				serviceYears = Integer.valueOf(params[1]);
				BigDecimal years = new BigDecimal(serviceYears);
				result = st.multiply(years).toString();
			} else {
				String[] youhuiId = StringUtils.split(params[1], "_");
				systemConfig.setId(Integer.valueOf(youhuiId[1]));
				List<SystemConfig> youhuiConfigList = systemConfigService.getSystemConfigs(systemConfig);
				if (youhuiConfigList.size() == 1) {
					serviceYears = Integer.valueOf(youhuiConfigList.get(0).getConfigValue());
					BigDecimal years = new BigDecimal(serviceYears);
					result = (st.multiply(years)).toString();
				}
			}
		}
		return result;
	}

	public Keywords getKeywords() {
		return keywords;
	}

	/**
	 * 提交关键词申请
	 */
	@SuppressWarnings("deprecation")
	public void submitKeyword() {
		String result = "exception";
		account = new Account();
		account.setUserId(getCurrentUser().getId());
		account = accountService.getAccount(account);
		String price = price();
		if (!price.equals("exception")) {
			// 余额是否满足申请的价格
			if (account.getMoney() != null) {
				if (account.getMoney().doubleValue() >= new BigDecimal(price).doubleValue()) {
					// 完善keywords的属性
					// "p": servicetype + "-" + serviceyear,
					// "keywords.keywords": $("#keyword").val(),
					// "keywords.customId": customID,
					// "keywords.customName": customName
					keywords.setAgentId(this.getCurrentUser().getId());
					keywords.setAgentName(this.getCurrentUser().getUserName());
					// 预注册冻结款
					String[] params = StringUtils.split(p, "-");
					Integer serviceTypeId = Integer.valueOf(params[0]);
					SystemConfig sc = new SystemConfig();
					sc.setId(serviceTypeId);
					List<SystemConfig> serviceTypeConfigList = systemConfigService
							.getSystemConfigs(sc);
					if (serviceTypeConfigList.size() == 1) {
						keywords.setPreRegFrozenMoney(new BigDecimal(
								serviceTypeConfigList.get(0).getConfigValue()));
					}
					// price
					keywords.setPrice(new BigDecimal(price));
					// productType
					keywords.setProductType(serviceTypeConfigList.get(0).getId());
					// serviceYear
					if (!params[1].contains("id_"))
						keywords.setServiceYears(Integer.valueOf(params[1]));
					else {
						String[] youhuiId = StringUtils.split(params[1], "_");
						sc.setId(Integer.valueOf(youhuiId[1]));
						List<SystemConfig> youhuiConfigList = systemConfigService
								.getSystemConfigs(sc);
						if (youhuiConfigList.size() == 1) {
							// 买二送一对应的是数据库SystemConfig的ConfigTypeValue值，项目设计中约定如此
							keywords.setServiceYears(Integer
									.valueOf(youhuiConfigList.get(0)
											.getConfigTypeValue()));
						}
					}
					if (keywords.getServiceYears() > 0) {
						Date date = new Date();
						// 预注册时间
						keywords.setPreRegDatetime(date);
						// 预注册到期时间
						keywords.setPreRegPassDatetime(new Date(date.getTime() + Constants.DAYS_5));
						// 注册时间
						keywords.setRegDatetime(date);
						Date regPassDatetime = new Date(date.getTime());
						regPassDatetime.setYear(regPassDatetime.getYear() + keywords.getServiceYears());
						// 注册到期日
						keywords.setRegPassDatetime(regPassDatetime);
						// 设置关键词状态
						keywords.setIsPass(0);
						keywords.setIsUse(1);
						keywords.setCheckStatus(0);
						keywords.setOpenApp(0);
						// 保存keywords
						this.keywordsService.tx_SaveKeywords(keywords, getCurrentUser(), date);
						result = "success";
					}
				} else {
					result = "nomoney";
				}
			}
		}
		this.getOut().print(result);
	}
	
	/**
	 * 关键词检索
	 * @return
	 */
	public String keywordManage() {
		if (null == keywords)
			keywords = new Keywords();
		keywords.setAgentId(this.getCurrentUser().getId());
		if (keywords.getKeywords() != null) {
			if (this.getRequest().getMethod().equals("GET")) {
				try {
					keywords.setKeywords(new String(keywords.getKeywords().getBytes("ISO-8859-1"), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			keywords.setSearchStr("%" + SQLTools.transfer(keywords.getKeywords()) + "%");
		}
		Integer totalCount = keywordsService.count(keywords);
		this.getPager().setTotalCount(totalCount);
		
		if (totalCount > 0) {
			keywords.setStartNum((this.getPager().getPage()-1)*this.getPager().getPageSize());
			keywords.setPageSize(this.getPager().getPageSize());
			this.getPager().setItems(this.keywordsService.getKeywordsBySearch(keywords));
		}
		return Action.SUCCESS;
	}

	/**
	 * 关键词审核
	 */
	public String checkKeyword() {
		if (null == keywords)
			keywords = new Keywords();
		// keywords.setAgentId(this.getCurrentUser().getId()); 不需要按代理商ID来查看
		if (keywords.getKeywords() != null) {
			if (this.getRequest().getMethod().equals("GET")) {
				try {
					keywords.setKeywords(new String(keywords.getKeywords().getBytes("ISO-8859-1"), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			keywords.setSearchStr("%" + SQLTools.transfer(keywords.getKeywords()) + "%");
		}
		Integer totalCount = keywordsService.count(keywords);
		this.getPager().setTotalCount(totalCount);
		
		if (totalCount > 0) {
			keywords.setStartNum((this.getPager().getPage()-1)*this.getPager().getPageSize());
			keywords.setPageSize(this.getPager().getPageSize());
			this.getPager().setItems(this.keywordsService.getKeywordsBySearch(keywords));
		}
		return Action.SUCCESS;
	}
	
	
	/**
	 * 修改keywords状态
	 */
	public void updateKeywords() {
		String result = "exception";
		if (keywords != null) {
			try {
				if (keywords.getCheckStatus() != null) {
					// 修改为审核中checkStatus-->1
					if (keywords.getCheckStatus().equals(1)) {
						this.keywordsService.modifyKeywords(keywords);
						this.setLog(this.getCurrentUser(), "用户进行关键词状态修改，状态修改为审核中");
					}
					// 通过
					else if (keywords.getCheckStatus().equals(2)) {
						this.keywordsService.tx_ChangeStatusToOk(keywords, getCurrentUser(), new Date());
					}
					// 不通过
					else if (keywords.getCheckStatus().equals(3)) {
						this.keywordsService.tx_ChangeStatusToNo(keywords, getCurrentUser(), new Date());
					}
				} else {
					this.keywordsService.modifyKeywords(keywords);
					this.setLog(this.getCurrentUser(), "关键词使用状态被修改为" + keywords.getIsUse());
				}
				result = "success";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.getOut().print(result);
	}
	
	/**
	 * 显示APP开通页面
	 * @return Action.SUCCESS
	 */
	public String openApp() {
		if (null != keywords) {
			keywords = keywordsService.getKeywordsById(keywords);
			systemConfig = new SystemConfig();
			systemConfig.setId(keywords.getProductType());
			serviceType = this.systemConfigService.getSystemConfigs(systemConfig);
			if (serviceType != null)
				systemConfig = serviceType.get(0);
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 开通APP保存
	 * @return
	 */
	public String modifyApp() {
		if (keywords != null 
				&& keywords.getAppUserName() != null
				&& keywords.getAppPassword() != null
				&& (!keywords.getAppUserName().equals(""))
				&& (!keywords.getAppPassword().equals(""))) {
			
			try {
				// 1.userName 2.password 3.loginUrl 4.openApp
				keywords.setLoginUrl(Constants.appMakeUrlConfig.getConfigValue());
				keywordsService.modifyKeywords(keywords);
			} catch (Exception e) {
				e.printStackTrace();
				this.addActionError("对不起，开通APP失败，请重试");
			}
			
		} else {
			this.addActionMessage("登录账号和密码不能为空");
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 显示续费页面
	 * @return
	 */
	public String keywordsXufei() {
		if (null != keywords) {
			keywords = keywordsService.getKeywordsById(keywords);
			// 关键词续费条件：已通过审核&&正在使用
			if (keywords.getCheckStatus() == 2 && keywords.getIsUse() == 1) {
				init();
			}
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 保存续费并提交
	 */
	public void keywordsXufeiSave() {
		String result = "exception";
		this.account = new Account();
		account.setUserId(this.getCurrentUser().getId());
		account = accountService.getAccount(account);
		if (p != null && keywords != null) {
			// 验证当前用户的余额是否充足，如果充足，则进行续费扣费操作
			String price = this.price();
			if (account.getMoney() != null && account.getMoney().doubleValue() >= new BigDecimal(price).doubleValue()) {
				String[] pa = StringUtils.split(p, "-");
				keywordsService.tx_keywordsXuFei(keywords, pa[0], pa[1], new BigDecimal(price), new Date());
				account = accountService.getAccount(account); // <--此处Action中的account余额并没有变化，需要重新从数据库中查询一遍更新后的数值
				result = account.getMoney().toString();
			} else {
				result = "nomoney";
			}
		}
		this.getOut().print(result);
	}
	
	/**
	 * 删除关键词
	 */
	public void deleteKeyword() {
		String result = "failed";
		if (keywords != null) {
			this.keywordsService.deleteKeywords(keywords);
			result = "success";
		}
		this.getOut().print(result);
	}
	
	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
	}

	public List<Keywords> getKeywordsList() {
		return keywordsList;
	}

	public void setKeywordsList(List<Keywords> keywordsList) {
		this.keywordsList = keywordsList;
	}

	public Custom getCustom() {
		return custom;
	}

	public void setCustom(Custom custom) {
		this.custom = custom;
	}

	public List<Custom> getCustomList() {
		return customList;
	}

	public void setCustomList(List<Custom> customList) {
		this.customList = customList;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public SystemConfig getMaxServiceYears() {
		return maxServiceYears;
	}

	public void setMaxServiceYears(SystemConfig maxServiceYears) {
		this.maxServiceYears = maxServiceYears;
	}

	public List<SystemConfig> getServiceType() {
		return serviceType;
	}

	public void setServiceType(List<SystemConfig> serviceType) {
		this.serviceType = serviceType;
	}

	public List<SystemConfig> getYouhuiType() {
		return youhuiType;
	}

	public void setYouhuiType(List<SystemConfig> youhuiType) {
		this.youhuiType = youhuiType;
	}

	public KeywordsService getKeywordsService() {
		return keywordsService;
	}

	public void setKeywordsService(KeywordsService keywordsService) {
		this.keywordsService = keywordsService;
	}

	public CustomService getCustomService() {
		return customService;
	}

	public void setCustomService(CustomService customService) {
		this.customService = customService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public SystemConfig getSystemConfig() {
		return systemConfig;
	}

	public void setSystemConfig(SystemConfig systemConfig) {
		this.systemConfig = systemConfig;
	}

	public SystemConfigService getSystemConfigService() {
		return systemConfigService;
	}

	public void setSystemConfigService(SystemConfigService systemConfigService) {
		this.systemConfigService = systemConfigService;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}
	
}
