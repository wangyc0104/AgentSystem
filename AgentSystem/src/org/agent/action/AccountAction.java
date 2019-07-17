package org.agent.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.agent.common.Constants;
import org.agent.common.PageSupport;
import org.agent.pojo.Account;
import org.agent.pojo.AccountDetail;
import org.agent.pojo.Logs;
import org.agent.pojo.SystemConfig;
import org.agent.pojo.User;
import org.agent.service.account.AccountService;
import org.agent.service.accountdetail.AccountDetailService;

import com.opensymphony.xwork2.Action;

/**
 * 账户Action
 * @author Yicheng Wang
 */
@SuppressWarnings("serial")
public class AccountAction extends BaseAction {

	private Account account;
	private AccountDetail accountDetail; // 接收到的查询条件
	// IOC注入
	private AccountService accountService;
	private AccountDetailService accountDetailService;
	private User user;
	private List<User> userList;
	private List<SystemConfig> accountConfigList;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public AccountDetail getAccountDetail() {
		return accountDetail;
	}

	public void setAccountDetail(AccountDetail accountDetail) {
		this.accountDetail = accountDetail;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public AccountDetailService getAccountDetailService() {
		return accountDetailService;
	}

	public void setAccountDetailService(
			AccountDetailService accountDetailService) {
		this.accountDetailService = accountDetailService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<SystemConfig> getAccountConfigList() {
		return accountConfigList;
	}

	public void setAccountConfigList(List<SystemConfig> accountConfigList) {
		this.accountConfigList = accountConfigList;
	}

	public String caiwu() {
		this.accountConfigList = Constants.accountConfigList;
		return SUCCESS;
	}

	/**
	 * 输出 List<User> json对象
	 */
	public void searchUser() {
		if (user == null)
			user = new User();
		user.setIsStart(1);
		if (userList == null)
			userList = new ArrayList<User>();
		userList = this.getUserService().getUserList(user);
		// java对象转json字符串 json
		// example:
		// [{'name':'aa','age':'18','sex':'female'},{'name':'bb','age':'20','sex':'male'}]
		String jsonStr = JSONArray.fromObject(userList).toString();
		this.getOut().print(jsonStr);
	}

	/**
	 * 对用户账户资金操作
	 */
	public void operateAccount() {
		Account dbAccount = accountService.getAccount(account);
		try {
			if (null != dbAccount) {
				Account oldAccount = dbAccount;
				Account newAccount = account;
				// init （初始化）流水
				accountDetail.setAccountMoney(oldAccount.getMoney().add(
						newAccount.getMoney())); // 流水余额：完成当前流水后的余额
				accountDetail.setMoney(newAccount.getMoney());
				accountDetail.setUserId(newAccount.getUserId());
				accountDetail.setDetailDateTime(new Date());
				if (accountDetail.getMemo() == null)
					accountDetail.setMemo("");
				// init （初始化）logs
				Logs logs = new Logs();
				logs.setUserId(this.getCurrentUser().getId());
				logs.setUserName(this.getCurrentUser().getUserCode());
				logs.setOperateInfo(logs.getUserName() + "对"
						+ newAccount.getUserName() + "进行"
						+ accountDetail.getDetailTypeName() + "操作.流水金额："
						+ newAccount.getMoney());
				logs.setOperateDatetime(new Date());
				this.getAccountService().tx_operationAccount(oldAccount,
						newAccount, accountDetail, logs);
				this.getOut().print("success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.getOut().print("failed");
		}
	}

	/**
	 * 按用户ID查询账户明细
	 * 
	 * @return
	 */
	public String accountDetailByUserId() {
		if (accountDetail == null)
			accountDetail = new AccountDetail();
		if (user != null && user.getId() != null) {
			accountDetail.setUserId(user.getId());
		} else {
			accountDetail.setUserId(this.getCurrentUser().getId());
		}
		if (this.getPager() == null)
			this.setPager(new PageSupport());
		/******************分页*****************/
		// 设置从返回结果的第一条记录的编号
		accountDetail.setStartNum((this.getPager().getPage() - 1) * this.getPager().getPageSize());
		// 设置每页取多少条
		accountDetail.setPageSize(this.getPager().getPageSize());
		// 得到totalCount, pageCount
		Integer totalCount = this.getAccountDetailService().count(accountDetail);
		this.getPager().setTotalCount(totalCount);
		this.getPager().setItems(this.getAccountDetailService().getAccountDetailList(accountDetail));
		return Action.SUCCESS;
	}

	/**
	 * 返回当前登录用户的账户余额
	 */
	public void getCurrentUserAccount() {
		String result = "failed";
		this.account = new Account();
		account.setUserId(this.getCurrentUser().getId());
		account = this.accountService.getAccount(account);
		if (account != null) {
			result = account.getMoney().toString();
		}
		this.getOut().print(result);
	}
	
	/**
	 * 查询代理商预付款流水
	 */
	public String yfk() {
		if (null != accountDetail) {
			if (null != accountDetail.getEndTime()) {
				DateFormat df = DateFormat.getDateInstance();
				String dfString = df.format(accountDetail.getEndTime()) + (" 23:59:59");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					accountDetail.setEndTime(sdf.parse(dfString));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		} else {
			new AccountDetail();
		}
		caiwu(); // 得到账户类型配置项accountConfigTypeList
		accountDetailByUserId();
		return Action.SUCCESS;
	}
}
