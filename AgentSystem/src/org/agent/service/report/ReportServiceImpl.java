package org.agent.service.report;

import java.util.List;

import org.agent.dao.report.ReportMapper;
import org.agent.pojo.Account;
import org.agent.pojo.ReportProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportMapper mapper;
	
	public ReportMapper getMapper() {
		return mapper;
	}

	public void setMapper(ReportMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public List<Account> accountBalance(Account account) {
		return mapper.accountBalance(account);
	}

	@Override
	public List<ReportProduct> reportProduct(ReportProduct reportProduct) {
		return mapper.reportProduct(reportProduct);
	}

}
