package com.APITestingExamination.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtility {
	public static ExtentReports reports ; 
	public static ExtentSparkReporter spark ;
	public static ExtentTest testlogger ; 
	private static ExtentReportUtility ExtentReportObject ; 
	
	private void ExtentReportUtility() {
		// Private constructor for Singletone implementation
	}
	
	public static ExtentReportUtility getInstance() {
		if (ExtentReportObject== null) {
			ExtentReportObject = new ExtentReportUtility () ; 
		}
		return ExtentReportObject ; 
	}

	public void initializeExtentReport() {
		reports  = new ExtentReports() ;
		spark = new ExtentSparkReporter("APIExtentReport.html") ; 
		reports.attachReporter(spark);
		 
		reports.setSystemInfo("Host Name" , "Host for API Testing") ; 
		reports.setSystemInfo("Environment" , "QA");
		reports.setSystemInfo("User Name", "Vrishali ");
		reports.setSystemInfo("mykey" , "myvalue") ; 
		
		spark.config().setDocumentTitle("API Testing QA");
		spark.config().setReportName("Extent Report for API Testing QA  ");
		spark.config().setTheme(Theme.DARK) ;
		
	}
	
	public void createNewTest(String methodName ) {
		testlogger = reports.createTest(methodName) ; 
	}
	
	public void flushExtentReport() {
		reports.flush();  
	} 
	public void logInfo(String methodname, String message) {
		testlogger.log(Status.INFO, MarkupHelper.createLabel(methodname+" : "+message, ExtentColor.CYAN) ) ; 
	}
	public void logInfo(String message) {
		testlogger.log(Status.INFO, MarkupHelper.createLabel(message, ExtentColor.CYAN) ) ;
	}
	public void logSuccess(String methodname) {
		
		testlogger.log(Status.PASS, MarkupHelper.createLabel("PASS :  "+methodname, ExtentColor.GREEN)) ;
		}
	public void logFailure(String methodname, Throwable t) {
		testlogger.fail(t);
		testlogger.log(Status.FAIL, MarkupHelper.createLabel("FAIL :  "+methodname, ExtentColor.RED)) ;
	}
	public void logFailure(String methodname) {
		testlogger.log(Status.FAIL, MarkupHelper.createLabel("FAIL :  "+methodname, ExtentColor.RED)) ;
	}
	public void logFailure( Throwable t) {
		testlogger.fail(t);
		//testlogger.log(Status.FAIL, MarkupHelper.createLabel("FAIL :  "+methodname, ExtentColor.RED)) ;
	}
	/*
	public void logFailWithException( String testcaseName, Throwable e) {
		//testLogger.log(Status.FAIL, text ) ; 
		exlogger.log(Status.FAIL, MarkupHelper.createLabel(testcaseName+" failed. ", ExtentColor.RED));
		exlogger.log(Status.FAIL, e) ; 
	}
	public void logWarning(String text) {
		exlogger.log(Status.WARNING, text ) ; 
	}
	public void logSkip(String text) {
		exlogger.log(Status.SKIP, text ) ; 
	}
	*/
	
 }
