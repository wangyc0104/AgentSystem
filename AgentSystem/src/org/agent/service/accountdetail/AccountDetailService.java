package org.agent.service.accountdetail;

import java.util.List;

import org.agent.pojo.AccountDetail;

/**
 * 账户明细Service
 * @author Yicheng Wang
 *
 */
public interface AccountDetailService {

	/**
	 * 获取账户明细列表
	 * @param accountDetail
	 * @return
	 */
	public List<AccountDetail> getAccountDetailList(AccountDetail accountDetail);

	/**
	 * 获取账户明细数量
	 * @param accountDetail
	 * @return
	 */
	public Integer count(AccountDetail accountDetail);

	/**
	 * 添加账户明细
	 * @param accountDetail
	 * @return
	 */
	public int addAccountDetail(AccountDetail accountDetail);

	/**
	 * 修改账户明细
	 * @param accountDetail
	 * @return
	 */
	public int modifyAccountDetail(AccountDetail accountDetail);

	/**
	 * 删除账户明细
	 * @param accountDetail
	 * @return
	 */
	public int deleteAccountDetail(AccountDetail accountDetail);
	
}
