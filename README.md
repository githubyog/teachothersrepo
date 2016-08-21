# egen-be-challenge


Start the Mongodb and supply the Mongo DB server details as given below

This apllication expects the following values as dynamic parameters. Mongodb Server, Port and the DB Name

First perform Maven clean install and then execute the class with main method to start the Metrics and Alert API.

mvn clean install

mvn exec:java -Dexec.mainClass="io.egen.challenge.Application" -Dmongodb.serverHost="localhost" -Dmongodb.schema="morphia_datastore" -Dmongodb.serverPort=27017

Execute the sensor-emulator to create Metrics at the regular interval.
