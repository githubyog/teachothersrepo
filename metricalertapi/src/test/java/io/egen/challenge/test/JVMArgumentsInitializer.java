package io.egen.challenge.test;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class JVMArgumentsInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{

	public void initialize(ConfigurableApplicationContext applicationContext) {
		System.setProperty("mongodb.serverHost", "localhost");
		System.setProperty("mongodb.serverPort", "27017");
		System.setProperty("mongodb.schema", "morphia_datastore");
	}

}
