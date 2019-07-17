package org.agent.service.account;

import java.util.List;

import org.agent.pojo.Account;
import org.agent.pojo.AccountDetail;
import org.agent.pojo.Logs;

/**
 * 账户Service
 * @author Yicheng Wang
 *
 */
public interface AccountService {
	
	/**
	 * 操作用户的账户资金
	 * @param account
	 * @return
	 */
	public List<Account> getAccountList(Account account);
	
	/**
	 * 获取账户对象
	 * @param account
	 * @return
	 */
	public Account getAccount(Account account);
	
	/**
	 * 添加账户
	 * @param account
	 * @return
	 */
	public int addAccount (Account account);
	
	/**
	 * 修改账户
	 * @param account
	 * @return
	 */
	public int modifyAccount(Account account);
	
	/**
	 * 删除账户
	 * @param account
	 * @return
	 */
	public int deleteAccount(Account account);
	
	/**
	 * 操作账户资金
	 * @return
	 */
	public boolean tx_operationAccount(Account oldAccount, Account newAccount, AccountDetail accountDetail, Logs logs);
	
}
