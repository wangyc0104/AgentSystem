package org.agent.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户明细
 * @author Yicheng Wang
 */
public class AccountDetail extends Base {

	private Integer userId;
	private Integer detailType; // as_systemconfig中configType=1的所有configTypeValue
	private String detailTypeName; // as_systemconfig中configType=1的所有configTypeName
	private BigDecimal money; // 一次流水的金额（+为进账，-为出账）
	private BigDecimal accountMoney; // 账户余额
	private String memo; // 备注信息
	private Date detailDateTime; // 明细交易时间
	private String userName;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDetailType() {
		return detailType;
	}
	public void setDetailType(Integer detailType) {
		this.detailType = detailType;
	}
	public String getDetailTypeName() {
		return detailTypeName;
	}
	public void setDetailTypeName(String detailTypeName) {
		this.detailTypeName = detailTypeName;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public BigDecimal getAccountMoney() {
		return accountMoney;
	}
	public void setAccountMoney(BigDecimal accountMoney) {
		this.accountMoney = accountMoney;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getDetailDateTime() {
		return detailDateTime;
	}
	public void setDetailDateTime(Date detailDateTime) {
		this.detailDateTime = detailDateTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
