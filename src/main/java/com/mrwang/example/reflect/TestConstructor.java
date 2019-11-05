package com.mrwang.example.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestConstructor {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class clazz = Person.class;
		// 找到clazz中的无参数构造方法
		Constructor<Person> connConstructor = clazz.getConstructor();
		// 执行构造方法
		Person person = (Person) connConstructor.newInstance();
		System.out.println(person);
	}

}
