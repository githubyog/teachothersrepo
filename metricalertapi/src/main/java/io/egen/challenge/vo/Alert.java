package io.egen.challenge.vo;


import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

@Entity("alerts")
@Indexes(
	    @Index(fields = @Field("timeStamp"))
	)
public class Alert extends BaseVO {

	private Long timeStamp;
	
	private String alertMessge;

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getAlertMessge() {
		return alertMessge;
	}

	public void setAlertMessge(String alertMessge) {
		this.alertMessge = alertMessge;
	}

}
