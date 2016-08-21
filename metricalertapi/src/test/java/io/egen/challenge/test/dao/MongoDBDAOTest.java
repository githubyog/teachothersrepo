package io.egen.challenge.test.dao;


import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.egen.challenge.Application;
import io.egen.challenge.dao.MongoDBDAO;
import io.egen.challenge.test.JVMArgumentsInitializer;
import io.egen.challenge.vo.Metrics;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = { JVMArgumentsInitializer.class }, classes = {Application.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MongoDBDAOTest {
	
	@Autowired
    private MongoDBDAO mongoDBDAO;

	@Test
	public void testMetrics() throws Exception {
		Metrics metrics = new Metrics();
		metrics.setTimeStamp(System.currentTimeMillis());
		metrics.setValue(100);
		mongoDBDAO.save(metrics);
	}
	
	@Test
	public void testMetricsFetchBaseValue() throws Exception {
		Metrics metrics = new Metrics();
		metrics.setTimeStamp(System.currentTimeMillis());
		metrics.setValue(100);
		Integer baseWeight = mongoDBDAO.getBaseValueForMetrics();
		assert(baseWeight != null);
	}
	
	@Test
	public void testMetricsFetch() throws Exception {
		List<Metrics> metricsList = mongoDBDAO.getCollectionData(Metrics.class);
		assert(metricsList != null);
		assert(!metricsList.isEmpty());
	}
	
	@Test
	public void testMetricsFetchWithinTimeRange() throws Exception {
		Long currentTime = System.currentTimeMillis();
		List<Metrics> metricsList = mongoDBDAO.getCollectionDataWithinTimeRange(Metrics.class, "timeStamp", currentTime-50000, currentTime+50000);
		assert(metricsList != null);
		assert(!metricsList.isEmpty());
	}
	
}