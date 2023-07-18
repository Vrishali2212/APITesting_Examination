package com.APITestingExamination.POJOClasses;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersResponsePojo {
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<EMPData> getData() {
		return data;
	}
	public void setData(List<EMPData> data) {
		this.data = data;
	}
	private String status;
	private String message;
	private List<EMPData> data ; 
	
	
}

class EMPData {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public long getEmployee_salary() {
		return employee_salary;
	}
	public void setEmployee_salary(long employee_salary) {
		this.employee_salary = employee_salary;
	}
	public int getEmployee_age() {
		return employee_age;
	}
	public void setEmployee_age(int employee_age) {
		this.employee_age = employee_age;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	private int id;
	private String employee_name ; 
	private long employee_salary;
	private int employee_age;
	private String profile_image;
	
}
