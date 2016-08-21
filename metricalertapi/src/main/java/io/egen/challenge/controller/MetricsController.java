package io.egen.challenge.controller;

import java.util.List;

import org.easyrules.api.RulesEngine;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.egen.challenge.dao.MongoDBDAO;
import io.egen.challenge.rules.OverWeightRule;
import io.egen.challenge.rules.UnderWeightRule;
import io.egen.challenge.vo.Metrics;

@RestController
public class MetricsController {
	
	@Autowired
	private ObjectFactory<UnderWeightRule> underWeightRuleprototypeFactory;
	
	@Autowired
	private ObjectFactory<OverWeightRule> overWeightRuleprototypeFactory;
	
	@Autowired
	private ObjectFactory<RulesEngine> rulesEngineprototypeFactory;

	@Autowired
	private MongoDBDAO mongoDBDAO;

	@RequestMapping(method=RequestMethod.POST, 
					path="/createMetrics", 
					consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, 
					produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Metrics createMetrics(@RequestBody Metrics metrics){
		mongoDBDAO.save(metrics);
		applyTrigger(metrics);
		return metrics;
	}
	
	@RequestMapping(method=RequestMethod.GET, 
				path="/getMetrics", 
				produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Metrics> getAllMetrics(){
		return mongoDBDAO.getCollectionData(Metrics.class);
	}
	
	@RequestMapping(method=RequestMethod.GET, 
			path="/getMetricsForTimeRange", 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Metrics> getMetricsWithinTimeRange(@RequestParam("startTime") Long startTime,
																 @RequestParam("endTime") Long endTime){
		return mongoDBDAO.getCollectionDataWithinTimeRange(Metrics.class, "timeStamp", startTime, endTime);
	}
	
	private void applyTrigger(Metrics metrics){
		UnderWeightRule underWeightRule = underWeightRuleprototypeFactory.getObject();
		OverWeightRule overWeightRule = overWeightRuleprototypeFactory.getObject();
		RulesEngine rulesEngine = rulesEngineprototypeFactory.getObject();
		underWeightRule.setMetrics(metrics);
		overWeightRule.setMetrics(metrics);
		rulesEngine.registerRule(underWeightRule);
		rulesEngine.registerRule(overWeightRule);
		rulesEngine.fireRules();
	}
}
