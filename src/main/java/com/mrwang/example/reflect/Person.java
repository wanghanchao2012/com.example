package com.mrwang.example.reflect;

import lombok.Data;

@Data
public class Person extends Animal {
	public Person() {
		System.out.println("我是无参数构造方法 ");
	}

	private String name;
	private String address;
	private Integer ageInteger;

	public String eat(String name) {
		System.out.println("eat====>" + name);
		return name;
	}

	private String testPrivate() {
		System.out.println("test private --- is ok");
		return "";
	}

}
