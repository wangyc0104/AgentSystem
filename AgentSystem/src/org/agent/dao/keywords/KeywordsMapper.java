package org.agent.dao.keywords;

import java.util.List;

import org.agent.pojo.Keywords;

public interface KeywordsMapper {

	public int addKeywords(Keywords keywords);
	
	public int modifyKeywords(Keywords keywords);
	
	public int deleteKeywords(Keywords keywords);
	
	public List<Keywords> getList(Keywords keywords);
	
	public List<Keywords> getKeywordsBySearch(Keywords keywords);
	
	public int count(Keywords keywords);
	
	public Keywords getKeywordsById(Keywords keywords);
	
	public Keywords getKeywordsByKeyword(Keywords keywords);
	
}
