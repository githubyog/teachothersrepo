package io.egen.challenge.rules;

import org.easyrules.core.BasicRule;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import io.egen.challenge.dao.MongoDBDAO;
import io.egen.challenge.vo.Alert;
import io.egen.challenge.vo.Metrics;

@SpringRule
@Qualifier("underWeightRule")
public class UnderWeightRule extends BasicRule {
	
	@Autowired
	private MongoDBDAO mongoDBDAO;
	
	@Autowired
	private  MessageSource messageSource;

	private Metrics metrics;
	
	public Metrics getMetrics() {
		return metrics;
	}

	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}

	public UnderWeightRule(){
		super("UnderWeightRule",
	              "Check if metrics is below 10 % lesser than base value", 2);
	}

	@Override
	public boolean evaluate(){
		boolean isUnderWeight = false;
		Integer baseWeight = mongoDBDAO.getBaseValueForMetrics();
		if(baseWeight != null && ((baseWeight - baseWeight * 0.1)>metrics.getValue())){
			isUnderWeight = true;
		}
		return isUnderWeight;
	}
	
	@Override
	public void execute() throws Exception {
		Alert alert = new Alert();
		alert.setTimeStamp(metrics.getTimeStamp());
		alert.setAlertMessge(messageSource.getMessage("underweight.message", null, LocaleContextHolder.getLocale()));
		mongoDBDAO.save(alert);
	}
}
