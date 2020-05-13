package com.restapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID = "2";//hard coded for get and put testcases
	public Logger logger;
	@BeforeClass
	public void setup() {
		//logger instance
		logger = Logger.getLogger("RestAPITesting");
		//configuring log4j properties file
		PropertyConfigurator.configure("log4j.properties");
		//prints debug msgs
		logger.setLevel(Level.DEBUG);
	}
	 
}
