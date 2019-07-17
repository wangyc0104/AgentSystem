import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperRunManager;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReportTest {

	@SuppressWarnings("resource")
	@Test
	public void test() {
		File reportFile = new File("D:\\test\\ReportTest\\accountBalance.jasper");
		if (reportFile != null && reportFile.exists()) {
			try {
				FileOutputStream fos = new FileOutputStream("D:\\test\\ReportTest\\output\\test.pdf");
				Connection conn = null;
				ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext-default.xml");
				DataSource dataSource = atx.getBean("dataSource", DataSource.class);
				conn = dataSource.getConnection();
				byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), null, conn);
				fos.write(bytes);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (JRException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("resource")
	@Test
	public void testYfk() {
		File reportFile = new File("D:\\test\\ReportTest\\yufukuanReport.jasper");
		if (reportFile != null && reportFile.exists()) {
			try {
				FileOutputStream fos = new FileOutputStream("D:\\test\\ReportTest\\output\\testYfk.pdf");
				Connection conn = null;
				ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext-default.xml");
				DataSource dataSource = atx.getBean("dataSource", DataSource.class);
				conn = dataSource.getConnection();
				ResultSet rs = conn.prepareStatement("select d.*, u.userName from as_accountdetail as d, as_user as u where d.userId=u.id and detailType=9999").executeQuery();
				byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), null, new JRResultSetDataSource(rs));
				fos.write(bytes);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (JRException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("resource")
	@Test
	public void testAgent() {
		File reportFile = new File("D:\\test\\ReportTest\\agentReport.jasper");
		if (reportFile != null && reportFile.exists()) {
			try {
				FileOutputStream fos = new FileOutputStream("D:\\test\\ReportTest\\output\\testAgent.pdf");
				Connection conn = null;
				ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext-default.xml");
				DataSource dataSource = atx.getBean("dataSource", DataSource.class);
				conn = dataSource.getConnection();
				ResultSet rs = conn.prepareStatement("select d.*, u.userName from as_accountdetail as d, as_user as u where d.userId=u.id").executeQuery();
				byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), null, new JRResultSetDataSource(rs));
				fos.write(bytes);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (JRException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("resource")
	@Test
	public void testProduct() {
		File reportFile = new File("D:\\test\\ReportTest\\productReport.jasper");
		if (reportFile != null && reportFile.exists()) {
			try {
				FileOutputStream fos = new FileOutputStream("D:\\test\\ReportTest\\output\\testProduct.pdf");
				Connection conn = null;
				ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext-default.xml");
				DataSource dataSource = atx.getBean("dataSource", DataSource.class);
				conn = dataSource.getConnection();
				ResultSet rs = conn.prepareStatement("select count(1) as number, sum(price) as price, configTypeName from as_keywords, as_systemconfig where as_keywords.productType=as_systemconfig.id GROUP BY productType").executeQuery();
				Map<String, Object> params = new HashMap();
				params.put("title", "产品分类/金额汇总");
				byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getAbsolutePath(), params, new JRResultSetDataSource(rs));
				fos.write(bytes);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
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
