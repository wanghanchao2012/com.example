package com.mrwang.example.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.channels.FileChannel;
import java.util.Scanner;

import scala.collection.mutable.StringBuilder;

public class MySpring {
	public Object getBean(String className) {
		Object object = null;
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("请给" + className + "类的对象赋值");
		try {
			Class<?> clazzClass = Class.forName(className);
			object = clazzClass.newInstance();
			// 做一个自动DI 注入，对象中的所有属性set
			// 找到每一个不同对象中的所有set方法给属性赋值
			// 自己通过拼接的方式处理名称
			// 通过找属性的名字获取set名字
			Field[] declaredFields = clazzClass.getDeclaredFields();
			for (Field field : declaredFields) {
				// 获取属性名字
				String fieldName = field.getName();
				// 2手动拼串儿
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String otherLetters = fieldName.substring(1);// 属性除了首字母之外的字母
				StringBuilder builder = new StringBuilder("set");
				builder.append(firstLetter);
				builder.append(otherLetters);
				Class<?> type = field.getType();
				// 通过处理好的方法，寻找类中的set方法
				Method method = clazzClass.getMethod(builder.toString(), type);
				Constructor<?> constructor = type.getConstructor(String.class);
				// 找到setMethod执行，赋值成功
				System.out.println("请给" + fieldName + "赋值");
				String nextLine = inputScanner.nextLine();
				method.invoke(object, constructor.newInstance(nextLine));

				;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
}
