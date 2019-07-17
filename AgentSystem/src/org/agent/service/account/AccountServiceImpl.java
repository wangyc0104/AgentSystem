package org.agent.service.account;

import java.math.BigDecimal;
import java.util.List;

import org.agent.dao.account.AccountMapper;
import org.agent.dao.accountdetail.AccountDetailMapper;
import org.agent.dao.logs.LogsMapper;
import org.agent.pojo.Account;
import org.agent.pojo.AccountDetail;
import org.agent.pojo.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper mapper;
	@Autowired
	private LogsMapper logsMapper;
	@Autowired
	private AccountDetailMapper accountDetailMapper;
	
	public AccountMapper getMapper() {
		return mapper;
	}

	public void setMapper(AccountMapper mapper) {
		this.mapper = mapper;
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

	@Override
	public List<Account> getAccountList(Account account) {
		return mapper.getAccountList(account);
	}

	@Override
	public Account getAccount(Account account) {
		return mapper.getAccount(account);
	}

	@Override
	public int addAccount(Account account) {
		return mapper.addAccount(account);
	}

	@Override
	public int modifyAccount(Account account) {
		return mapper.modifyAccount(account);
	}

	@Override
	public int deleteAccount(Account account) {
		return mapper.deleteAccount(account);
	}

	@Override
	/**
	 * 操作账户资金
	 * @param oldAccount 原始数据（原来账户的金额信息
	 * @param newAccount 新的数据（当前需要增加或修改的金额）
	 * @param accountDetail 当前流水信息
	 * @param logs 日志信息
	 * @return
	 */
	public boolean tx_operationAccount(Account oldAccount, Account newAccount, AccountDetail accountDetail, Logs logs) {
		// 1.资金计算
		BigDecimal money = oldAccount.getMoney().add(newAccount.getMoney());
		BigDecimal moneyBak = oldAccount.getMoneyBak().add(newAccount.getMoney());
		oldAccount.setMoney(money);
		oldAccount.setMoneyBak(moneyBak);
		// 2.修改账户资金
		mapper.modifyAccount(oldAccount);
		// 3.流水记录
		accountDetailMapper.addAccountDetail(accountDetail);
		// 4.记录操作日志信息
		logsMapper.addLogs(logs);
		return false;
	}

}
