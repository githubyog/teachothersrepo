package io.egen.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.egen.challenge.dao.MongoDBDAO;
import io.egen.challenge.vo.Alert;

@RestController
public class AlertsController {
	
	@Autowired
	private MongoDBDAO mongoDBDAO;

	@RequestMapping(method=RequestMethod.GET, 
				path="/getAlerts", 
				produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Alert> getAllMetrics(){
		return mongoDBDAO.getCollectionData(Alert.class);
	}
	
	@RequestMapping(method=RequestMethod.GET, 
			path="/getAlertsForTimeRange", 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Alert> getAlertsWithinTimeRange(@RequestParam("startTime") Long startTime,
																 @RequestParam("endTime") Long endTime){
		return mongoDBDAO.getCollectionDataWithinTimeRange(Alert.class, "timeStamp", startTime, endTime);
	}
	
}
