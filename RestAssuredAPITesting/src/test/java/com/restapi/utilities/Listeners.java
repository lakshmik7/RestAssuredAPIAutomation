package com.restapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
public ExtentHtmlReporter htmlReporter;
public ExtentReports extent;
public ExtentTest test;

public void onStart(ITestContext testContext) {
	
	htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/myReport.html");
	htmlReporter.config().setDocumentTitle("Automation Report");//title of report
	htmlReporter.config().setReportName("RestAssured API Testing Report");//name of report
	htmlReporter.config().setTheme(Theme.DARK);
	extent = new ExtentReports();
	extent.attachReporter(htmlReporter);
	extent.setSystemInfo("Host name", "localhost");
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("user", "Lakshmi");
}
public void onTestSuccess(ITestResult result) {
	test = extent.createTest(result.getName());//create new entry in the report
	test.log(Status.PASS, "Test CAse Passed is "+result.getName());
}
public void onTestFailure(ITestResult result) {
	test = extent.createTest(result.getName());//create new entry in the report
	test.log(Status.FAIL, "Test CAse FAiled is "+result.getName());
	test.log(Status.FAIL, "Test CAse FAiled is "+result.getThrowable());
}
public void onTestSkipped(ITestResult result) {
	test = extent.createTest(result.getName());//create new entry in the report
	test.log(Status.SKIP, "Test CAse Skipped is "+result.getName());
}
public void onFinish(ITestContext testcontext) {
	extent.flush();
}
}
