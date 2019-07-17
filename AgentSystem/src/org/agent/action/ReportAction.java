package org.agent.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.agent.common.Constants;
import org.agent.pojo.Account;
import org.agent.pojo.AccountDetail;
import org.agent.pojo.ReportProduct;
import org.agent.service.accountdetail.AccountDetailService;
import org.agent.service.report.ReportService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.Action;

/**
 * 报表Action
 * @author Yicheng Wang
 */
@SuppressWarnings("serial")
public class ReportAction extends BaseAction {

	private Integer reportType;
	private List<Account> accountList;
	private Date startTime;
	private Date endTime;
	private ReportService reportService;
	private List<AccountDetail> accountDetailList;
	private AccountDetailService accountDetailService;
	private List<ReportProduct> reportProductList;
	
	public Integer getReportType() {
		return reportType;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public List<AccountDetail> getAccountDetailList() {
		return accountDetailList;
	}

	public void setAccountDetailList(List<AccountDetail> accountDetailList) {
		this.accountDetailList = accountDetailList;
	}

	public AccountDetailService getAccountDetailService() {
		return accountDetailService;
	}

	public void setAccountDetailService(AccountDetailService accountDetailService) {
		this.accountDetailService = accountDetailService;
	}

	public List<ReportProduct> getReportProductList() {
		return reportProductList;
	}

	public void setReportProductList(List<ReportProduct> reportProductList) {
		this.reportProductList = reportProductList;
	}

	public String report() {
		if (reportType != null) {
			// 查询
			if (reportType == 1) {
				Account account = new Account();
				accountList = reportService.accountBalance(account);
			} else if (reportType == 2) {
				// 预付款流水报表
				AccountDetail accountDetail = new AccountDetail();
				accountDetail.setStartTime(startTime);
				if (null != endTime) {
					DateFormat df = DateFormat.getDateInstance();
					String dfString = df.format(endTime) + " 23:59:59";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						accountDetail.setEndTime(sdf.parse(dfString));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				accountDetail.setDetailType(9999);
				accountDetail.setStartNum(0);
				accountDetail.setPageSize(9999);
				accountDetailList = accountDetailService.getAccountDetailList(accountDetail);
			} else if (reportType == 3) {
				// 代理商流水报表
				AccountDetail accountDetail = new AccountDetail();
				accountDetail.setStartTime(startTime);
				if (null != endTime) {
					DateFormat df = DateFormat.getDateInstance();
					String dfString = df.format(endTime) + " 23:59:59";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						accountDetail.setEndTime(sdf.parse(dfString));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				accountDetail.setStartNum(0);
				accountDetail.setPageSize(9999);
				accountDetailList = accountDetailService.getAccountDetailList(accountDetail);
			} else if (reportType == 4) {
				// 产品分类数量/金额汇总
				ReportProduct reportProduct = new ReportProduct();
				reportProduct.setStartTime(startTime);
				if (null != endTime) {
					DateFormat df = DateFormat.getDateInstance();
					String dfString = df.format(endTime) + " 23:59:59";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						reportProduct.setEndTime(sdf.parse(dfString));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				reportProductList = reportService.reportProduct(reportProduct);
			}
		}
		return Action.SUCCESS;
	}
	
	public void reportFile() {
		// 输出流
		try {
			Connection conn = null;
			DataSource dataSource = Constants.ctx.getBean("dataSource",DataSource.class);
			conn = dataSource.getConnection();
			File reportFile = new File(this.getRequest().getRealPath("/reports/accountBalance.jasper"));
			byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), null, conn);
			this.getResponse().setContentType("application/pdf");
			this.getResponse().setContentLength(bytes.length);
			this.getResponse().setContentType("text/html;charset=utf-8");
			this.getResponse().setHeader("Content-Disposition", "attachment;filename=AccountBalanceReport.pdf");
			/*
			 * this.getResponse().getOutputStream() 针对字节byte[]输出
			 * this.getResponse().getWriter() 针对字符进行输出
			 * 在Servlet的HttpServletResponse中两者会存在冲突，解决方法：在执行的方法的作用域期间两者只调用一个（要么输出字符，要么输出字节）
			 */
			this.getResponse().getOutputStream().write(bytes);
			this.getResponse().getOutputStream().flush();
			this.getResponse().getOutputStream().close();
			if (conn != null) {
				conn.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	public void reportFileExcel() {
		File reportFile = new File(this.getRequest().getRealPath("/reports/accountBalance.jasper"));
		if (reportFile != null && reportFile.exists()) {
			Connection conn = null;
			DataSource dataSource = Constants.ctx.getBean("dataSource",DataSource.class);
			try {
				conn = dataSource.getConnection();
				byte[] bytes = null;
				JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), null, conn);
				JRXlsExporter exporter = new JRXlsExporter();
				// 输入流
				exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
				// 输出流
				ByteArrayOutputStream xlsOs = new ByteArrayOutputStream();
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsOs);
				/**
				 * 格式
				 */
				exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); // 删除记录最下面的空行
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE); // 删除多余的ColumnHeader
				exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				// 输出
				exporter.exportReport();
				bytes = xlsOs.toByteArray();
				if (bytes != null && bytes.length > 0) {
					this.getResponse().setContentType("application/vnd.ms-excel");
					this.getResponse().setContentLength(bytes.length);
					this.getResponse().setContentType("text/html;charset=utf-8");
					this.getResponse().setHeader("Content-Disposition", "attachment;filename=AccountBalanceReport.xls");
					this.getResponse().getOutputStream().write(bytes);
					this.getResponse().getOutputStream().flush();
					this.getResponse().getOutputStream().close();
					if (conn != null) {
						conn.close();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (JRException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 预付款报表PDF下载
	 */
	public void reportYfkpdf() {
		File reportFile = new File(this.getRequest().getRealPath("/reports/yufukuanReport.jasper"));
		if (reportFile != null && reportFile.exists()) {
			Connection conn = null;
			DataSource dataSource = Constants.ctx.getBean("dataSource",DataSource.class);
			try {
				conn = dataSource.getConnection();
				String sql = "select d.*, u.userName from as_accountdetail as d, as_user as u where d.userId=u.id and detailType=9999";
				if (startTime != null)
					sql += (" and d.detailDateTime>='" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(startTime) + "'");
				if (endTime != null)
					sql += (" and d.detailDateTime<='" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(endTime) + " 23:59:59'");
				sql += " order by d.detailDateTime desc ";
				ResultSet resultSet = conn.prepareStatement(sql).executeQuery();
				byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), null, new JRResultSetDataSource(resultSet));
				if (bytes != null && bytes.length > 0) {
					this.getResponse().setContentType("application/pdf");
					this.getResponse().setContentLength(bytes.length);
					this.getResponse().setContentType("text/html;charset=utf-8");
					this.getResponse().setHeader("Content-Disposition", "attachment;filename=yfkReport.pdf");
					this.getResponse().getOutputStream().write(bytes);
					this.getResponse().getOutputStream().flush();
					this.getResponse().getOutputStream().close();
					if (conn != null) {
						conn.close();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 预付款报表Excel下载
	 */
	public void reportYfkxls() {
		File reportFile = new File(this.getRequest().getRealPath("/reports/yufukuanReport.jasper"));
		if (reportFile != null && reportFile.exists()) {
			Connection conn = null;
			DataSource dataSource = Constants.ctx.getBean("dataSource",DataSource.class);
			try {
				conn = dataSource.getConnection();
				String sql = "select d.*, u.userName from as_accountdetail as d, as_user as u where d.userId=u.id and detailType=9999";
				byte[] bytes = null;
				if (startTime != null)
					sql += (" and d.detailDateTime>='" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(startTime) + "'");
				if (endTime != null)
					sql += (" and d.detailDateTime<='" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(endTime) + " 23:59:59'");
				sql += " order by d.detailDateTime desc ";
				ResultSet resultSet = conn.prepareStatement(sql).executeQuery();
				JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), null, new JRResultSetDataSource(resultSet));
				JRXlsExporter exporter = new JRXlsExporter();
				// 输入流
				exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
				// 输出流
				ByteArrayOutputStream xlsOs = new ByteArrayOutputStream();
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsOs);
				/**
				 * 格式
				 */
				exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); // 删除记录最下面的空行
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE); // 删除多余的ColumnHeader
				exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				// 输出
				exporter.exportReport();
				bytes = xlsOs.toByteArray();
				if (bytes != null && bytes.length > 0) {
					this.getResponse().setContentType("application/vnd.ms-excel");
					this.getResponse().setContentLength(bytes.length);
					this.getResponse().setContentType("text/html;charset=utf-8");
					this.getResponse().setHeader("Content-Disposition", "attachment;filename=yfkReport.xls");
					this.getResponse().getOutputStream().write(bytes);
					this.getResponse().getOutputStream().flush();
					this.getResponse().getOutputStream().close();
					if (conn != null) {
						conn.close();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (JRException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 代理商报表PDF下载
	 */
	public void reportDlspdf() {
		File reportFile = new File(this.getRequest().getRealPath("/reports/agentReport.jasper"));
		if (reportFile != null && reportFile.exists()) {
			Connection conn = null;
			DataSource dataSource = Constants.ctx.getBean("dataSource",DataSource.class);
			try {
				conn = dataSource.getConnection();
				String sql = "select d.*, u.userName from as_accountdetail as d, as_user as u where d.userId=u.id";
				if (startTime != null)
					sql += (" and d.detailDateTime>='" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(startTime) + "'");
				if (endTime != null)
					sql += (" and d.detailDateTime<='" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(endTime) + " 23:59:59'");
				sql += " order by d.detailDateTime desc ";
				ResultSet resultSet = conn.prepareStatement(sql).executeQuery();
				byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), null, new JRResultSetDataSource(resultSet));
				if (bytes != null && bytes.length > 0) {
					this.getResponse().setContentType("application/pdf");
					this.getResponse().setContentLength(bytes.length);
					this.getResponse().setContentType("text/html;charset=utf-8");
					this.getResponse().setHeader("Content-Disposition", "attachment;filename=agentReport.pdf");
					this.getResponse().getOutputStream().write(bytes);
					this.getResponse().getOutputStream().flush();
					this.getResponse().getOutputStream().close();
					if (conn != null) {
						conn.close();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void reportDlsxls() {
		File reportFile = new File(this.getRequest().getRealPath("/reports/agentReport.jasper"));
		if (reportFile != null && reportFile.exists()) {
			Connection conn = null;
			DataSource dataSource = Constants.ctx.getBean("dataSource",DataSource.class);
			try {
				conn = dataSource.getConnection();
				String sql = "select d.*, u.userName from as_accountdetail as d, as_user as u where d.userId=u.id";
				byte[] bytes = null;
				if (startTime != null)
					sql += (" and d.detailDateTime>='" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(startTime) + "'");
				if (endTime != null)
					sql += (" and d.detailDateTime<='" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(endTime) + " 23:59:59'");
				sql += " order by d.detailDateTime desc ";
				ResultSet resultSet = conn.prepareStatement(sql).executeQuery();
				JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), null, new JRResultSetDataSource(resultSet));
				JRXlsExporter exporter = new JRXlsExporter();
				// 输入流
				exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
				// 输出流
				ByteArrayOutputStream xlsOs = new ByteArrayOutputStream();
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsOs);
				/**
				 * 格式
				 */
				exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); // 删除记录最下面的空行
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE); // 删除多余的ColumnHeader
				exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				// 输出
				exporter.exportReport();
				bytes = xlsOs.toByteArray();
				if (bytes != null && bytes.length > 0) {
					this.getResponse().setContentType("application/vnd.ms-excel");
					this.getResponse().setContentLength(bytes.length);
					this.getResponse().setContentType("text/html;charset=utf-8");
					this.getResponse().setHeader("Content-Disposition", "attachment;filename=agentReport.xls");
					this.getResponse().getOutputStream().write(bytes);
					this.getResponse().getOutputStream().flush();
					this.getResponse().getOutputStream().close();
					if (conn != null) {
						conn.close();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (JRException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 产品分类/数量金额汇总PDF下载
	 */
	public void reportProductpdf() {
		File reportFile = new File(this.getRequest().getRealPath("/reports/productReport.jasper"));
		if (reportFile != null && reportFile.exists()) {
			Connection conn = null;
			DataSource dataSource = Constants.ctx.getBean("dataSource",DataSource.class);
			try {
				conn = dataSource.getConnection();
				String sql = "select count(1) as number, sum(price) as price, configTypeName from as_keywords, as_systemconfig where as_keywords.productType=as_systemconfig.id";
				if (startTime != null)
					sql += (" and as_keywords.regDatetime>='" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(startTime) + "'");
				if (endTime != null)
					sql += (" and as_keywords.regDatetime<='" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(endTime) + " 23:59:59'");
				sql += " GROUP BY productType order by as_keywords.regDatetime desc ";
				ResultSet resultSet = conn.prepareStatement(sql).executeQuery();
				Map<String, Object> params = new HashMap();
				params.put("title", "产品分类/金额汇总");
				byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), params, new JRResultSetDataSource(resultSet));
				if (bytes != null && bytes.length > 0) {
					this.getResponse().setContentType("application/pdf");
					this.getResponse().setContentLength(bytes.length);
					this.getResponse().setContentType("text/html;charset=utf-8");
					this.getResponse().setHeader("Content-Disposition", "attachment;filename=productReport.pdf");
					this.getResponse().getOutputStream().write(bytes);
					this.getResponse().getOutputStream().flush();
					this.getResponse().getOutputStream().close();
					if (conn != null) {
						conn.close();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 产品分类/数量金额汇总Excel下载
	 */
	public void reportProductxls() {
		File reportFile = new File(this.getRequest().getRealPath("/reports/productReport.jasper"));
		if (reportFile != null && reportFile.exists()) {
			Connection conn = null;
			DataSource dataSource = Constants.ctx.getBean("dataSource",DataSource.class);
			try {
				conn = dataSource.getConnection();
				byte[] bytes = null;
				String sql = "select count(1) as number, sum(price) as price, configTypeName from as_keywords, as_systemconfig where as_keywords.productType=as_systemconfig.id ";
				if (startTime != null)
					sql += (" and as_keywords.regDatetime>='" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(startTime) + "'");
				if (endTime != null)
					sql += (" and as_keywords.regDatetime<='" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(endTime) + " 23:59:59'");
				sql += " GROUP BY productType order by as_keywords.regDatetime desc ";
				Map<String, Object> params = new HashMap();
				params.put("title", "产品分类/金额汇总");
				ResultSet resultSet = conn.prepareStatement(sql).executeQuery();
				JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), params, new JRResultSetDataSource(resultSet));
				JRXlsExporter exporter = new JRXlsExporter();
				// 输入流
				exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
				// 输出流
				ByteArrayOutputStream xlsOs = new ByteArrayOutputStream();
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsOs);
				/**
				 * 格式
				 */
				exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); // 删除记录最下面的空行
				exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE); // 删除多余的ColumnHeader
				exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				// 输出
				exporter.exportReport();
				bytes = xlsOs.toByteArray();
				if (bytes != null && bytes.length > 0) {
					this.getResponse().setContentType("application/vnd.ms-excel");
					this.getResponse().setContentLength(bytes.length);
					this.getResponse().setContentType("text/html;charset=utf-8");
					this.getResponse().setHeader("Content-Disposition", "attachment;filename=productReport.xls");
					this.getResponse().getOutputStream().write(bytes);
					this.getResponse().getOutputStream().flush();
					this.getResponse().getOutputStream().close();
					if (conn != null) {
						conn.close();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (JRException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
