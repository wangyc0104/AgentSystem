package org.agent.common;

public class SQLTools {

	/**
	 * mybatis模糊查询防止SQL注入
	 * @return
	 */
	public static String transfer(String keyword) {
		if (keyword == null || keyword == "") {
			return "";
		}
		else if (keyword.contains("%") || keyword.contains("_")) {
			// 替换斜杠、百分号、下划线
			keyword = keyword.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\%", "\\\\%").replaceAll("\\_", "\\\\_");
		}
		return keyword;
	}
}
