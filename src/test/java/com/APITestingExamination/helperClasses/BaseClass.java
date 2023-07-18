package com.APITestingExamination.helperClasses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

//import com.restTests.helpers.UserServiceHelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseClass {
protected static Logger log =null ;
	
	@BeforeSuite 
	public static void beforesuit() {
		 log  =	LogManager.getLogger()  ;
		 log.info("Log 4 J logger started") ;
	}
	@BeforeClass
	public void beforeclassmethod() {
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1/" ;
	}
	public Response getUsersData() {
		Response response = RestAssured.given().get("employees") ;
		return response;
	}
	
	public Response getUserData(int empid) {
		String endpoint = "/employee/"+Integer.toString(empid) ;
		Response response = RestAssured.given().get(endpoint);
		return response;
	}
	
	public int getResponseStatus(Response response) {
		return response.getStatusCode();
		
	}
	public Response deleteUser(int userid) {
		
		Response response = RestAssured.given().delete("/delete/"+Integer.toString(userid));
		return response;
	}
	/*
	public boolean validateResponseCode(Response response, int expected_statuscode) {
		boolean returnvalue = false;
		int actual_statuscode = response.getStatusCode();
		try {
		response.then().statusCode(expected_statuscode) ;
		returnvalue =  true;
		}
		catch (Exception e) {
			e.printStackTrace();
			returnvalue = false ;
		}
		finally {
			System.out.println("Inside finally block.");
			return returnvalue;
		}
		
	}
	*/

}
