package com.mrwang.example.reflect.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MySpring {
	public Object getBean(String bean) throws Exception {
		Class<?> forName = Class.forName(bean);
		Constructor<?> constructor = forName.getConstructor();
		Object newInstance = constructor.newInstance();

		Annotation annotation = constructor.getAnnotation(MyAnnotation.class);
		Method method = annotation.getClass().getMethod("value");
		String[] record = (String[]) method.invoke(annotation);
		Field[] fs = forName.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			Class<?> type = f.getType();
			StringBuilder sb = new StringBuilder("set");
			sb.append(f.getName().substring(0, 1).toUpperCase().concat(f.getName().substring(1)));
			Method method2 = forName.getMethod(sb.toString(), type);
			method2.invoke(newInstance, type.getConstructor(String.class).newInstance(record[i]));
		}
		return newInstance;
	}

	public static void main(String[] args) {
		try {
			Person person = (Person) new MySpring().getBean("com.mrwang.example.reflect.annotation.Person");
			System.out.println(person.getAddress() + "--" + person.getName() + "--" + person.getAge());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
