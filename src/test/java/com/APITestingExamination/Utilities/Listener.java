package com.APITestingExamination.Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Listener implements ITestListener {
	

	public static ExtentReportUtility extentreport = null ; 
	public static Logger log = null ;

	public void onStart(ITestContext context) {
		log = LogManager.getLogger()  ;
		log.info("log 4 j ready .");
		log.info("Listener.onStart() called");
		extentreport= ExtentReportUtility.getInstance();
		extentreport.initializeExtentReport();
		log.info("Extent Report ready.. ");
	}
	
	public void onTestStart(ITestResult result) {
		log.info("-------------------------------------------");
		log.info("Listener.onTestStart() called");
		log.info("Started Testing ---> "+result.getMethod().getMethodName());
		extentreport.createNewTest(result.getMethod().getMethodName());
		extentreport.logInfo(result.getMethod().getMethodName(),"Starting test :"+result.getMethod().getMethodName()  );
	}

	public void onTestSuccess(ITestResult result) {
		log.info("Listener.onTestSucess() called");
		log.info("PASSED : " + result.getMethod().getMethodName());
		log.info("-------------------------------------------");
		extentreport.logSuccess(result.getMethod().getMethodName());
		
	}

	public void onTestFailure(ITestResult result) {
		log.info("Listener.onTestFailure() called");
		log.error("FAILED : " + result.getMethod().getMethodName());
		log.info("-------------------------------------------");
		
		Throwable t = result.getThrowable();
		if (t !=null) {
		extentreport.logFailure(result.getMethod().getMethodName(), t);
		}else {
			extentreport.logFailure(result.getMethod().getMethodName());
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		log.info("Listener.onTestSkipped() called");
		log.error("SKIPPED : " + result.getMethod().getMethodName());
		log.info("-------------------------------------------");
		extentreport.logInfo(result.getMethod().getMethodName(), "SKIPPED");
	}

	public void onFinish(ITestContext context) {
		log.info("Listener.onFinish() called");
		extentreport.flushExtentReport();
	

}
	
}
