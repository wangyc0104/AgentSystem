package org.agent.service.keywords;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.agent.pojo.Keywords;
import org.agent.pojo.User;

/**
 * 关键词Service
 * @author Yicheng Wang
 *
 */
public interface KeywordsService {

	/**
	 * 添加关键词
	 * @param keywords
	 * @return
	 */
	public int addKeywords(Keywords keywords);
	
	/**
	 * 修改关键词
	 * @param keywords
	 * @return
	 */
	public int modifyKeywords(Keywords keywords);
	
	/**
	 * 删除关键词
	 * @param keywords
	 * @return
	 */
	public int deleteKeywords(Keywords keywords);
	
	/**
	 * 获取关键词列表
	 * @param keywords
	 * @return
	 */
	public List<Keywords> getList(Keywords keywords);
	
	/**
	 * 搜索获取相应的关键词
	 * @param keywords
	 * @return
	 */
	public List<Keywords> getKeywordsBySearch(Keywords keywords);
	
	/**
	 * 获取关键词数量
	 * @param keywords
	 * @return
	 */
	public int count(Keywords keywords);
	
	/**
	 * 根据id获取关键词
	 * @param keywords
	 * @return
	 */
	public Keywords getKeywordsById(Keywords keywords);
	
	/**
	 * 根据内容获取关键词
	 * @param keywords
	 * @return
	 */
	public Keywords getKeywordsByKeyword(Keywords keywords);
	
	/**
	 * 保存关键词
	 * @param keywords
	 * @param user
	 * @param date
	 */
	public void tx_SaveKeywords(Keywords keywords, User user, Date date);
	
	/**
	 * 修改关键词状态为审核通过
	 * @param keywords
	 * @param user
	 * @param date
	 */
	public void tx_ChangeStatusToOk(Keywords keywords, User user, Date date);
	
	/**
	 * 修改关键词状态为审核未通过
	 * @param keywords
	 * @param user
	 * @param date
	 */
	public void tx_ChangeStatusToNo(Keywords keywords, User user, Date date);

	/**
	 * 续费操作
	 * @param keywords
	 * @param string
	 * @param string2
	 * @param bigDecimal
	 * @param date
	 */
	public void tx_keywordsXuFei(Keywords keywords, String string, String string2, BigDecimal bigDecimal, Date date);
	
}
