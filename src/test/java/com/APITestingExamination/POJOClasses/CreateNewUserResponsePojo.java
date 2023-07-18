package com.APITestingExamination.POJOClasses;

public class CreateNewUserResponsePojo {
	private String status; 
	private Data data ; 
	private String message; 
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDataName() {
		return this.data.getName();
	}
	public int getDataAge () {
		return this.data.getAge();
	}
	public int getDataSalary() {
		return this.data.getSalary();
	}
	public int getDataId() {
		return this.data.getId();
	}

	
}

class Data {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String name ; 
	private int salary ; 
	private int age;
	private int id ;
	
}
