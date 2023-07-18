package com.APITestingExamination.testScripts;

import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.APITestingExamination.POJOClasses.CreateNewUserPojo;
import com.APITestingExamination.POJOClasses.CreateNewUserResponsePojo;
import com.APITestingExamination.POJOClasses.GetAllUsersResponsePojo;
import com.APITestingExamination.POJOClasses.UserDataPojo;
import com.APITestingExamination.helperClasses.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Listeners(com.APITestingExamination.Utilities.Listener.class)
public class AutomationScript extends BaseClass {
	private int new_emp_id  ;
	
	/*
	  Testcase 1:
	  	resouce endpoint = /employees
		Http method=get
		1. get the reponse in POJO
		validate fallowing
		2. status code is 200
		3. response body status is "success"
		4. fetch the number of data records and print it to the console
	 */
	@Test
	public void testcase_1() {
		Response response = getUsersData();
		int expected_response_statuscode = 200 ;
		int actual_response_statuscode = getResponseStatus(response);
		
	//	boolean isStatusCodeMathcing = validateResponseCode(response,expected_response_statuscode ) ;
	//	Assert.assertEquals(isStatusCodeMathcing, true);
	//	log.info("Pass: Response code validation done using baseclass method.");
		
		response.then().statusCode(expected_response_statuscode) 
		.body("status", Matchers.is("success")); 
		log.info("Validation Passed : Response status is : success");
		String responsestatus = response.body().jsonPath().getString("status") ;
		log.info("Response status = " + responsestatus) ; 
		int noOfRecords = response.body().jsonPath().get("data.size()") ; 
		log.info("No. of data Records = " + noOfRecords); 
		
//	System.out.println(response.body().jsonPath().getList("data"));
	GetAllUsersResponsePojo allusersresponse = response.as(GetAllUsersResponsePojo.class) ;
	log.info("Response status from pojo = " +allusersresponse.getStatus());
	log.info("Response Message from pojo = " +allusersresponse.getMessage());
	 
	
	}
	
	@Test
	public void testcase_2() {
		CreateNewUserPojo newuser = new CreateNewUserPojo();
		newuser.setName("Arjun");
		newuser.setAge(25);
		newuser.setSalary(10000);
		
			Response response = RestAssured.given()
			.contentType(ContentType.JSON)
			.body(newuser)
			.when()
			.post("/create") ;
			
			log.info("Request sent: POST create ");
			response.then().statusCode(200);
			log.info("Verified : Status code is 200");
			response.then().body("status", Matchers.is("success"));
			log.info("Verified: Response body status is : success");
			
			int newuserid = response.body().jsonPath().get("data.id");
			String newusername=response.body().jsonPath().get("data.name");
			int newusersalary = response.body().jsonPath().get("data.salary");
			int newuserage=response.body().jsonPath().get("data.age");
			log.info("New user id = "+newuserid);
			this.new_emp_id = newuserid;
			 SoftAssert softAssert = new SoftAssert();
			 softAssert.assertEquals(newusername, newuser.getName());
			// softAssert.assertEquals(newusername, "Bheem");
			 softAssert.assertEquals(newusersalary, newuser.getSalary());
			 softAssert.assertEquals(newuserage, newuser.getAge());
			 softAssert.assertAll();
			log.info("Verified : name,salary and age data from response is as same as in the request");
			
			// Validation using POJO classes 
			CreateNewUserResponsePojo crtusr_response = response.body().as(CreateNewUserResponsePojo.class);
			int newuserid1 = crtusr_response.getDataId();
			String newusername1=crtusr_response.getDataName();
			int newusersalar1 = crtusr_response.getDataSalary();
			int newuserage1= crtusr_response.getDataAge();
			
			log.info("Created New User with details :");
			log.info(newusername1);
			log.info(newusersalar1);
			log.info(newuserage1);
			
		}
	
	@Test(dependsOnMethods = ("testcase_2"))
	public void testcase_3() {
		int id = new_emp_id;
		//int id = 7999;
		Response response = deleteUser(id);
		//Response response = RestAssured.given().delete("/delete/"+Integer.toString(id));
		log.info("Response status code = " + getResponseStatus(response));
		response.then().statusCode(200);
		log.info("Validated successfully : Status code is 200") ;
		String responseBodyStatus = response.body().jsonPath().getString("status");
		log.info("Response body status = " +responseBodyStatus );
		String response_id = response.body().jsonPath().get("data");
		Assert.assertEquals(response_id, id);
		log.info("Passed Record deleted for the same id we entered : "+response_id);
		String response_message=response.body().jsonPath().getString("message") ;
		log.info("Response message data = "+response_message);
	}
	
	@Test
public void testcase_4() {
		int id = 0 ;
		Response response = deleteUser(id);
		int response_statuscode = getResponseStatus(response);
		response.then().statusCode(400);
		log.info("Validated : Response status code is 400") ;
		String response_body_status = response.body().jsonPath().getString("status");
		log.info("Response body status = "+response_body_status);
		Assert.assertEquals(response_body_status, "error");
		String response_message = response.body().jsonPath().get("message");
		log.info("Response message data = "+response_message);
		
	}
	
	@Test
	public void testcase_5() {
		int id = 2;
		Response response = getUserData(id);
		response.then().statusCode(200);
		log.info("PASS: Response Status code 200 validated");
		response.then().contentType(ContentType.JSON);
		log.info("PASS: Content Type JSON validated");
		String name=response.body().jsonPath().get("data.employee_name");
		int salary=response.body().jsonPath().get("data.employee_salary");
		int age=response.body().jsonPath().get("data.employee_age");
		Assert.assertEquals(name, "Garrett Winters");
		Assert.assertEquals(salary, 170750);
		Assert.assertEquals(age, 63);
		log.info("PASS: Employee name, salary and age is as expected for emp id, "+id);
		
		
		
	}
	
	
	
	

}
