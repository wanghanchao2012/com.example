package com.mrwang.example.reflect.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class<Person> clazz = Person.class;
		Field field = clazz.getDeclaredField("name");
		Annotation annotation = field.getAnnotation(MyAnnotation.class);
		Class<? extends Annotation> class1 = annotation.getClass();
		Method method = class1.getMethod("value");
		String[] records = (String[]) method.invoke(annotation);
		for (String string : records) {
			System.out.println(string);
		}

	}

}
