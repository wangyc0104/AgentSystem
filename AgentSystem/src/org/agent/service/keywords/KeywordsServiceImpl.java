package org.agent.service.keywords;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.agent.dao.account.AccountMapper;
import org.agent.dao.accountdetail.AccountDetailMapper;
import org.agent.dao.keywords.KeywordsMapper;
import org.agent.dao.logs.LogsMapper;
import org.agent.dao.systemconfig.SystemConfigMapper;
import org.agent.pojo.Account;
import org.agent.pojo.AccountDetail;
import org.agent.pojo.Keywords;
import org.agent.pojo.Logs;
import org.agent.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("keywordsService")
public class KeywordsServiceImpl implements KeywordsService {

	@Autowired
	private KeywordsMapper mapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountDetailMapper accountDetailMapper;
	@Autowired
	private LogsMapper logsMapper;
	@Autowired
	private SystemConfigMapper systemConfigMapper;
	
	public KeywordsMapper getMapper() {
		return mapper;
	}
	public void setMapper(KeywordsMapper mapper) {
		this.mapper = mapper;
	}
	public AccountMapper getAccountMapper() {
		return accountMapper;
	}
	public void setAccountMapper(AccountMapper accountMapper) {
		this.accountMapper = accountMapper;
	}
	public LogsMapper getLogsMapper() {
		return logsMapper;
	}
	public void setLogsMapper(LogsMapper logsMapper) {
		this.logsMapper = logsMapper;
	}
	public AccountDetailMapper getAccountDetailMapper() {
		return accountDetailMapper;
	}
	public void setAccountDetailMapper(AccountDetailMapper accountDetailMapper) {
		this.accountDetailMapper = accountDetailMapper;
	}
	public SystemConfigMapper getSystemConfigMapper() {
		return systemConfigMapper;
	}
	public void setSystemConfigMapper(SystemConfigMapper systemConfigMapper) {
		this.systemConfigMapper = systemConfigMapper;
	}
	
	@Override
	public int addKeywords(Keywords keywords) {
		return mapper.addKeywords(keywords);
	}

	@Override
	public int modifyKeywords(Keywords keywords) {
		return mapper.modifyKeywords(keywords);
	}

	@Override
	public int deleteKeywords(Keywords keywords) {
		return mapper.deleteKeywords(keywords);
	}

	@Override
	public List<Keywords> getList(Keywords keywords) {
		return mapper.getList(keywords);
	}

	@Override
	public List<Keywords> getKeywordsBySearch(Keywords keywords) {
		return mapper.getKeywordsBySearch(keywords);
	}

	@Override
	public int count(Keywords keywords) {
		return mapper.count(keywords);
	}

	@Override
	public Keywords getKeywordsById(Keywords keywords) {
		return mapper.getKeywordsById(keywords);
	}

	@Override
	public Keywords getKeywordsByKeyword(Keywords keywords) {
		return mapper.getKeywordsByKeyword(keywords);
	}

	/**
	 * 保存关键词事务
	 */
	@Override
	public void tx_SaveKeywords(Keywords keywords, User user, Date date) {
		// 保存关键字
		this.mapper.addKeywords(keywords);
		// 修改账户金额
		Account account = new Account();
		account.setUserId(user.getId());
		account = accountMapper.getAccount(account);
		account.setMoney(account.getMoney().subtract(keywords.getPreRegFrozenMoney()));
		account.setMoneyBak(account.getMoney());
		accountMapper.modifyAccount(account);
		/*
		 * 账户流水
		 * 返回预注册冻结资金 类型ID号为9998 
		 * 扣除申请关键词的所有资金 类型ID号为9997 
		 * 扣除关键词续费资金 类型ID号为9996
		 * （预注册冻结资金）关键词申请扣款 类型ID号为9999
		 * 以上这四种类型是不记录在as_systemconfig表中维护，在程序中定义，不可维护。
		 */
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setUserId(user.getId());
		accountDetail.setDetailType(9999); // 设计文档里的要求
		accountDetail.setDetailTypeName("预注册冻结资金");
		accountDetail.setMoney(new BigDecimal(0).subtract(keywords.getPreRegFrozenMoney()));
		accountDetail.setAccountMoney(account.getMoney());
		accountDetail.setMemo(user.getUserName() + "对" + keywords.getKeywords() + "进行关键词申请预注册操作，冻结资金：" + keywords.getPreRegFrozenMoney().toString());
		accountDetail.setDetailDateTime(date);
		accountDetailMapper.addAccountDetail(accountDetail);
		// 记录日志
		Logs logs = new Logs();
		logs.setUserId(user.getId());
		logs.setUserName(user.getUserName());
		logs.setOperateDatetime(date);
		logs.setOperateInfo(user.getUserName() + "对" + keywords.getKeywords() + "进行关键词申请预注册操作，冻结资金：" + keywords.getPreRegFrozenMoney().toString());
		logsMapper.addLogs(logs);
	}
	
	/**
	 * 1.修改关键词的状态为审核通过status==2
	 * 2.返回预注册的冻结资金
	 * 3.在总账户中扣除正式注册的所需的所有资金
	 * 4.记录流水和日志（流水要记录两次：步骤2的流水、步骤3的流水）
	 */
	@Override
	public void tx_ChangeStatusToOk(Keywords keywords, User user, Date date) {
		int status = keywords.getCheckStatus();
		if (status != 2)
			throw new RuntimeException("通过审核的状态必须为2");
		keywords = mapper.getKeywordsById(keywords);
		System.out.println("tx_ChangeStatusToOk从数据库里拿到的keywords的checkStatus是：" + keywords.getCheckStatus());
		System.out.println("tx_ChangeStatusToOk要把keywords的checkStatus值改为：" + status);
		keywords.setCheckStatus(status);
		mapper.modifyKeywords(keywords);
		// 返还冻结资金
		Account account = new Account();
		account.setUserId(keywords.getAgentId());
		account = accountMapper.getAccount(account);
		account.setMoney(account.getMoney().add(keywords.getPreRegFrozenMoney()));
		account.setMoneyBak(account.getMoney());
		// accountMapper.modifyAccount(account); 该步骤可以省略，因为后面有专门进行正式扣款的步骤
		/*
		 * 记录返回预注册的冻结资金账户流水
		 * 返回预注册冻结资金 类型ID号为9998 
		 */
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setUserId(user.getId());
		accountDetail.setDetailType(9998);
		accountDetail.setDetailTypeName("返回预注册冻结资金");
		accountDetail.setMoney(keywords.getPreRegFrozenMoney());
		accountDetail.setAccountMoney(account.getMoney());
		accountDetail.setMemo(user.getUserName() + "对" + keywords.getKeywords() + "进行了关键词审核通过操作，返回冻结资金：" + keywords.getPreRegFrozenMoney() + "元");
		accountDetail.setDetailDateTime(date);
		accountDetailMapper.addAccountDetail(accountDetail);
		// 正式扣款
		account.setMoney(account.getMoney().subtract(keywords.getPrice()));
		account.setMoneyBak(account.getMoney());
		accountMapper.modifyAccount(account);
		/*
		 * 记录正式扣款账户流水
		 * 扣除申请关键词的所有资金 类型ID号为9997 
		 */
		accountDetail = new AccountDetail();
		accountDetail.setUserId(user.getId());
		accountDetail.setDetailType(9997);
		accountDetail.setDetailTypeName("扣除申请关键词" + keywords.getKeywords() + "的所有资金");
		accountDetail.setMoney(new BigDecimal(0).subtract(keywords.getPrice())); // 扣除，为负数
		accountDetail.setAccountMoney(account.getMoney());
		accountDetail.setMemo(user.getUserName() + "对" + keywords.getKeywords() + "进行了自动正式扣款操作，扣除正式资金：" + keywords.getPrice() + "元");
		accountDetail.setDetailDateTime(date);
		accountDetailMapper.addAccountDetail(accountDetail);
		
		Logs logs = new Logs();
		logs.setUserId(user.getId());
		logs.setUserName(user.getUserName());
		logs.setOperateDatetime(date);
		logs.setOperateInfo(user.getUserName() + "对" + keywords.getKeywords() + "进行关键词状态修改，状态修改为审核通过并扣款。");
		logsMapper.addLogs(logs);
	}
	
	/**
	 * 1.修改关键词的状态为审核通过status==3
	 * 2.返回预注册的冻结资金
	 * 3.记录流水和日志（流水只需记录一次：步骤2的流水）
	 */
	@Override
	public void tx_ChangeStatusToNo(Keywords keywords, User user, Date date) {
		int status = keywords.getCheckStatus();
		if (status != 3)
			throw new RuntimeException("不通过审核的状态必须为3");
		keywords = mapper.getKeywordsById(keywords);
		System.out.println("tx_ChangeStatusToNo从数据库里拿到的keywords的checkStatus是：" + keywords.getCheckStatus());
		System.out.println("tx_ChangeStatusToNo要把keywords的checkStatus值改为：" + status);
		keywords.setCheckStatus(status);
		mapper.modifyKeywords(keywords);
		// 返还冻结资金
		Account account = new Account();
		account.setUserId(keywords.getAgentId());
		account = accountMapper.getAccount(account);
		account.setMoney(account.getMoney().add(keywords.getPreRegFrozenMoney()));
		account.setMoneyBak(account.getMoney());
		accountMapper.modifyAccount(account); // 该步骤不可以省略，因为后面没有专门进行正式扣款的步骤来进行数据库操作
		/*
		 * 记录返回预注册的冻结资金账户流水
		 * 返回预注册冻结资金 类型ID号为9998 
		 */
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setUserId(user.getId());
		accountDetail.setDetailType(9998);
		accountDetail.setDetailTypeName("返回预注册冻结资金");
		accountDetail.setMoney(keywords.getPreRegFrozenMoney());
		accountDetail.setAccountMoney(account.getMoney());
		accountDetail.setMemo(user.getUserName() + "对" + keywords.getKeywords() + "进行了关键词审核不通过操作，返回冻结资金：" + keywords.getPreRegFrozenMoney() + "元");
		accountDetail.setDetailDateTime(date);
		accountDetailMapper.addAccountDetail(accountDetail);
		
		Logs logs = new Logs();
		logs.setUserId(user.getId());
		logs.setUserName(user.getUserName());
		logs.setOperateDatetime(date);
		logs.setOperateInfo(user.getUserName() + "对" + keywords.getKeywords() + "进行关键词状态修改，状态修改为审核不通过并返回预注册冻结资金。");
		logsMapper.addLogs(logs);
	}
	
	/**
	 * 续费事务
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void tx_keywordsXuFei(Keywords keywords, String productType, String xufeiYears, BigDecimal price, Date date) {
		// 1.修改关键词的总价与服务到期日期
		Integer type = Integer.valueOf(productType);
		Integer years = Integer.valueOf(xufeiYears);
		keywords = mapper.getKeywordsById(keywords);
		keywords.setProductType(type);
		keywords.setPrice(keywords.getPrice().add(price));
		keywords.setServiceYears(keywords.getServiceYears() + years);
		Date regPassDatetime = keywords.getRegPassDatetime();
		regPassDatetime.setYear(regPassDatetime.getYear() + years);
		keywords.setRegPassDatetime(regPassDatetime);
		mapper.modifyKeywords(keywords);
		// 2.更新账户余额
		Account account = new Account();
		account.setUserId(keywords.getAgentId());
		account = accountMapper.getAccount(account);
		account.setMoney(account.getMoney().subtract(price));
		account.setMoneyBak(account.getMoneyBak().subtract(price));
		accountMapper.modifyAccount(account);
		// 3.记录流水
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setUserId(keywords.getAgentId());
		accountDetail.setDetailType(9996); //“扣除关键词续费资金”类型ID号为9996
		accountDetail.setDetailTypeName("扣除续费资金"+years+"年"+price+"元");
		accountDetail.setMoney(price);
		accountDetail.setAccountMoney(account.getMoney());
		accountDetail.setMemo(keywords.getAgentName() + "对[" + keywords.getKeywords() + "]进行了扣除续费资金操作，" + years + "年扣除" + price + "元");
		accountDetail.setDetailDateTime(date);
		accountDetailMapper.addAccountDetail(accountDetail);
		// 4.记录操作日志
		Logs logs = new Logs();
		logs.setUserId(keywords.getAgentId());
		logs.setUserName(keywords.getAgentName());
		logs.setOperateDatetime(date);
		logs.setOperateInfo(keywords.getAgentName() + "对[" + keywords.getKeywords() + "]进行了扣除续费资金操作，" + years + "年扣除" + price + "元");
		logsMapper.addLogs(logs);
	}

}
