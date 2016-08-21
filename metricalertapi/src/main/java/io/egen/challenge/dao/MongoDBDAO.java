package io.egen.challenge.dao;

import java.util.List;

import io.egen.challenge.vo.BaseVO;

public interface MongoDBDAO {
	
	public void save(BaseVO baseVO);
	
	public <T extends BaseVO> List<T> getCollectionData(Class<T> type);
	
	public <T extends BaseVO> List<T> getCollectionDataWithinTimeRange(Class<T> type, 
															           String field,
																	   Long startTime, 
																	   Long endTime);
	
	public Integer getBaseValueForMetrics();

}
