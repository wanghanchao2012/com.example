package com.mrwang.example.reflect.annotation;

public class Person {
	@MyAnnotation({"chaohanwang","19","monggola"})
	public Person() {
		// TODO Auto-generated constructor stub
	}
	@MyAnnotation({"whc","18","monggola"})
	private String name;
	private Integer age;
	private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
