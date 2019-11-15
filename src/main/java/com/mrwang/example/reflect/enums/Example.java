package com.mrwang.example.reflect.enums;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Example {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// 1.得到枚举类对象
		Class<?> clz = ResultCode.class;
		// 2.得到所有枚举常量
		Object[] objects = clz.getEnumConstants();
		Method getCode = clz.getMethod("getCode");
		Method getName = clz.getMethod("getName");
		for (Object obj : objects) {
			System.out.println(obj.getClass());
			// 3.调用对应方法，得到枚举常量中字段的值
			System.out.println("code=" + getCode.invoke(obj) + "; name=" + getName.invoke(obj));
		}
	}
}
