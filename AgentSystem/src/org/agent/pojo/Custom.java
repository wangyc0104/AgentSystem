package org.agent.pojo;

import java.util.Date;

/**
 * 用户信息
 * @author Yicheng Wang
 */
public class Custom extends Base {

	// `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	// `agentId` bigint(20) NOT NULL,
	// `agentName` varchar(255) DEFAULT NULL,
	// `customName` varchar(255) DEFAULT NULL,
	// `customType` int(11) DEFAULT NULL,
	// `customTypeName` varchar(50) DEFAULT NULL,
	// `siteUrl` varchar(255) DEFAULT NULL,
	// `customStatus` int(11) DEFAULT '1' COMMENT '1为启动 0 为停用',
	// `bossName` varchar(50) DEFAULT NULL,
	// `cardType` int(11) DEFAULT NULL,
	// `cardTypeName` varchar(20) DEFAULT NULL,
	// `cardNum` varchar(60) DEFAULT NULL,
	// `companyTel` varchar(50) DEFAULT NULL,
	// `companyFax` varchar(50) DEFAULT NULL,
	// `regDatetime` datetime DEFAULT '2012-01-01 00:01:01',
	// `country` varchar(10) DEFAULT '中国',
	// `province` varchar(20) DEFAULT NULL,
	// `city` varchar(20) DEFAULT NULL,
	// `area` varchar(20) DEFAULT NULL,
	// `companyAddress` varchar(255) DEFAULT NULL,
	// `memo` text,
	// `agentCode` varchar(255) DEFAULT NULL,
	// PRIMARY KEY (`id`)

	private Integer agentId;
	private String agentName;
	private String customName;
	private String customType;
	private String customTypeName;
	private String siteUrl;
	private Integer customStatus;
	private String bossName;
	private Integer cardType;
	private String cardTypeName;
	private String cardNum;
	private String companyTel;
	private String companyFax;
	private Date regDatetime;
	private String country;
	private String province;
	private String city;
	private String area;
	private String companyAddress;
	private String memo;
	private String agentCode;

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCustomType() {
		return customType;
	}

	public void setCustomType(String customType) {
		this.customType = customType;
	}

	public String getCustomTypeName() {
		return customTypeName;
	}

	public void setCustomTypeName(String customTypeName) {
		this.customTypeName = customTypeName;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public Integer getCustomStatus() {
		return customStatus;
	}

	public void setCustomStatus(Integer customStatus) {
		this.customStatus = customStatus;
	}

	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getCompanyFax() {
		return companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	public Date getRegDatetime() {
		return regDatetime;
	}

	public void setRegDatetime(Date regDatetime) {
		this.regDatetime = regDatetime;
	}
	
	/*
	public void setRegDatetime(String regDatetime) {
		try {
			this.regDatetime = new SimpleDateFormat("yyyy-MM-dd").parse(regDatetime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	*/
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	@Override
	public String toString() {
		return "Custom [id=" + getId() + ", agentId=" + agentId + ", agentName=" + agentName
				+ ", customName=" + customName + ", customType=" + customType
				+ ", customTypeName=" + customTypeName + ", siteUrl=" + siteUrl
				+ ", customStatus=" + customStatus + ", bossName=" + bossName
				+ ", cardType=" + cardType + ", cardTypeName=" + cardTypeName
				+ ", cardNum=" + cardNum + ", companyTel=" + companyTel
				+ ", companyFax=" + companyFax + ", regDatetime=" + regDatetime
				+ ", country=" + country + ", province=" + province + ", city="
				+ city + ", area=" + area + ", companyAddress="
				+ companyAddress + ", memo=" + memo + ", agentCode="
				+ agentCode + "]";
	}

}
