package io.egen.challenge.vo;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

@Entity("metrics")
@Indexes(
	    @Index(fields = @Field("timeStamp"))
	)
public class Metrics extends BaseVO {
	
	private Long timeStamp;
	
	private Integer value;

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
		
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Metrics [id=" + getId() + ", timeStamp=" + timeStamp + ", value=" + value + "]";
	}
}