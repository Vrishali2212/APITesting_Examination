package com.APITestingExamination.testScripts;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAllData {
	
	public static void main(String[] args) {
		//String BaseURI  = "https://dummy.restapiexample.com/api/v1/employees" ;
		RestAssured.baseURI= "https://dummy.restapiexample.com/api/v1/" ;
		Response response = RestAssured.given().get("employees") ;
		response.then().statusCode(200) ; 
		System.out.println("Got status code 200");
		 int noOfRecords = response.body().jsonPath().get("data.size()") ; 
		 System.out.println("No. of Records = " + noOfRecords); 
		 String responsestatus = response.body().jsonPath().getString("status") ;
		 System.out.println("Response Status code = "+responsestatus);
		 
	}


}
