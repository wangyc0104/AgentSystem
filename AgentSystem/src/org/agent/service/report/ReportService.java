package org.agent.service.report;

import java.util.List;

import org.agent.pojo.Account;
import org.agent.pojo.ReportProduct;

/**
 * 报表Service
 * @author Yicheng Wang
 */
public interface ReportService {
	
	/**
	 * 代理商余额报表
	 * @param account
	 * @return
	 */
	public List<Account> accountBalance(Account account);
	
	/**
	 * 报表输出
	 * @param reportProduct
	 * @return
	 */
	public List<ReportProduct> reportProduct(ReportProduct reportProduct);
}
