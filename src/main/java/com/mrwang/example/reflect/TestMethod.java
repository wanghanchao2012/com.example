package com.mrwang.example.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestMethod {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		Class<Person> clazz = Person.class;
		/**
		 * 通过clazz获取其中的方法，通过方法名称定位方法，通过方法参数类型对应的Class来寻找
		 */
		Method method = clazz.getMethod("eat", String.class);
		int modifiers = method.getModifiers();// 获取方法的修饰符（权限+特征）
		Class<?> returnType = method.getReturnType();// 获取返回值数据类型
		String name = method.getName();// 获取方法名称
		Class<?>[] parameterTypes = method.getParameterTypes();// 获取方参数列表的类型
		Class<?>[] exceptionTypes = method.getExceptionTypes();// 获取方法抛出的异常

		// 方法的执行
		Class<Person> clazz1 = Person.class;
		Person person = (Person) clazz1.newInstance();
		/**
		 * 通过clazz获取其中的方法 通过方法名定位方法，通过方法参数类型对应Class来寻找
		 */
		Method method2 = clazz1.getMethod("eat", String.class);
		String result = (String) method2.invoke(person, "参数参数");
		System.out.println(result);
		Method method3 = clazz1.getMethod("sleep");
		method3.invoke(person);
		Method declaredMethod = clazz1.getDeclaredMethod("testPrivate");
		declaredMethod.setAccessible(true);
		declaredMethod.invoke(person);
	}

}
