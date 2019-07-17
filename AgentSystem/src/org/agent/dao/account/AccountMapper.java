package org.agent.dao.account;

import java.util.List;

import org.agent.pojo.Account;

public interface AccountMapper {

	/**
	 * 获取所有账户资金列表
	 * @param account
	 * @return
	 */
	public List<Account> getAccountList(Account account);
	
	public Account getAccount(Account account);
	
	public int addAccount (Account account);
	
	public int modifyAccount(Account account);
	
	public int deleteAccount(Account account);
	
}
