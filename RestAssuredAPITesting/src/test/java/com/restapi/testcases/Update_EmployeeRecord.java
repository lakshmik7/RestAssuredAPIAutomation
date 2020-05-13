package com.restapi.testcases;

import javax.swing.text.html.parser.TagElement;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restapi.base.TestBase;
import com.restapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class Update_EmployeeRecord extends TestBase{
	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSalary();
	String empAge = RestUtils.empAge();
	
	@BeforeClass
	void postemployeerecord() throws InterruptedException 
	{
		logger.info("*******Started Update_EmployeeRecord testcase********");
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest= RestAssured.given();
		
		//Creating JSON Object 
		JSONObject jpath = new JSONObject();
		jpath.put("name", empName);
		jpath.put("salary", empSalary);
		jpath.put("age", empAge);
		
		//adding header
		httpRequest.header("Content-Type","application/json");
		//adding json to the request
		httpRequest.body(jpath.toJSONString());
		//posting the request
		response=httpRequest.request(Method.PUT,"/update/"+empID );
		Thread.sleep(5000);
		
	}
	
	@Test
	void checkResponseBody() 
	{
		logger.info("******Checking response body*******");
		String responseBody = response.getBody().asString();
		logger.info("Response Body ==>" + responseBody);
		Assert.assertEquals(responseBody.contains(empName),true);
		Assert.assertEquals(responseBody.contains(empSalary),true);
		Assert.assertEquals(responseBody.contains(empAge),true);
	}
	@Test
	void checkstatusCode() 
	{
		logger.info("******Checking status code******");
		int statuscode = response.getStatusCode();
		logger.info("Status Code ==>" + statuscode);
		Assert.assertEquals(statuscode,200);
	}
	
	@Test
	void checkstatusLine() 
	{
		logger.info("******Checking status line******");
		String statusline = response.getStatusLine();
		logger.info("Status line ==>" + statusline);
		Assert.assertEquals(statusline,"HTTP/1.1 200 OK");
	}
	
	@Test
	void checkresponsetime() 
	{
		logger.info("******Checking response time ******");
		long responsetime = response.getTime();
		logger.info("response time ==>" + responsetime);
		if(responsetime>2000) {
			logger.warn("response time is greater than 2000");
		}
		Assert.assertTrue(responsetime<10000);
	}
	@Test
	void checkCookies() 
	{
		logger.info("******Checking cookies******");
		String cookie = response.cookie("PHPSESSID");
		
	}
	@AfterClass
	void teardown() {
		logger.info("********Finished Update_EmployeeRecord testcse******");
	}
	
}
