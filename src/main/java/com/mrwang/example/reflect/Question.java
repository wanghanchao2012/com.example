package com.mrwang.example.reflect;

public class Question {
	private String title;
	private String answer;
	private Integer age;

	public Question() {
		super();
	}

	public Question(String title, String answer) {
		super();
		this.title = title;
		this.answer = answer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}