package com.mrwang.example.reflect;

public class TestMain {

	public static void main(String[] args) {
		// 创建一个Question对象，对象的控制权交由别人处理 MySpring类 ，这个叫做 IOC （inversion Of Control）
		MySpring spring = new MySpring();
		Question question = (Question) spring.getBean("com.mrwang.example.reflect.Question");
		System.out.println(question);
		System.out.println(question.getAnswer());
		System.out.println(question.getTitle());
		System.out.println(question.getAge());
		// DI （依赖注入 Dependency Injection） DI 是IOC的一个具体体现，是IOC的一部分
		// 将对象的属性的值赋值 Dependency Injection 对象的控制别人的，别人创建对象的同时，帮我们自动注入值
	}

}
