package org.agent.pojo;

import java.math.BigDecimal;

/**
 * 代理商账户
 * @author Yicheng Wang
 */
public class Account extends Base {

	private Integer userId;
	private BigDecimal money;
	private BigDecimal moneyBak;
	private String userName;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public BigDecimal getMoneyBak() {
		return moneyBak;
	}
	public void setMoneyBak(BigDecimal moneyBak) {
		this.moneyBak = moneyBak;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
