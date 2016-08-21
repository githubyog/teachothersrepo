package io.egen.challenge.dao;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.egen.challenge.vo.BaseVO;
import io.egen.challenge.vo.Metrics;

@Component
public class MongoDBDAOImpl implements MongoDBDAO {
	
	@Autowired
	private Datastore mongoDBDataStore;

	public void save(BaseVO baseVO) {
		mongoDBDataStore.save(baseVO);
	}
	
	public <T extends BaseVO> List<T> getCollectionData(Class<T> type){
		final Query<T> query = mongoDBDataStore.createQuery(type);
		final List<T> collectionsData = query.asList();
		return collectionsData;
	}
	
	public <T extends BaseVO> List<T> getCollectionDataWithinTimeRange(Class<T> type, 
			                                                           String field,
																	   Long startTime, 
																	   Long endTime){
		Criteria greaterThanCriteria = mongoDBDataStore.createQuery(type).criteria(field).greaterThanOrEq(startTime);
		Criteria lesserThanCriteria = mongoDBDataStore.createQuery(type).criteria(field).lessThanOrEq(endTime);
		final Query<T> query = mongoDBDataStore.createQuery(type);
					   query.and(greaterThanCriteria, lesserThanCriteria);
		final List<T> collectionsData = query.asList();
		return collectionsData;
	}
	
	public Integer getBaseValueForMetrics(){
		Query<Metrics> query = mongoDBDataStore.find(Metrics.class).order("timeStamp").limit(1);
		Metrics metrics = query.get();
		return metrics.getValue();
	}
}