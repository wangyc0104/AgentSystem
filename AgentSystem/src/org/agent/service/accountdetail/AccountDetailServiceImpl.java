package org.agent.service.accountdetail;

import java.util.List;

import org.agent.dao.accountdetail.AccountDetailMapper;
import org.agent.pojo.AccountDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountDetailService")
public class AccountDetailServiceImpl implements AccountDetailService {

	@Autowired
	private AccountDetailMapper mapper;
	
	public AccountDetailMapper getMapper() {
		return mapper;
	}

	public void setMapper(AccountDetailMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<AccountDetail> getAccountDetailList(AccountDetail accountDetail) {
		return mapper.getAccountDetailList(accountDetail);
	}

	@Override
	public Integer count(AccountDetail accountDetail) {
		return mapper.count(accountDetail);
	}

	@Override
	public int addAccountDetail(AccountDetail accountDetail) {
		return mapper.addAccountDetail(accountDetail);
	}

	@Override
	public int modifyAccountDetail(AccountDetail accountDetail) {
		return mapper.modifyAccountDetail(accountDetail);
	}

	@Override
	public int deleteAccountDetail(AccountDetail accountDetail) {
		return mapper.deleteAccountDetail(accountDetail);
	}

}
