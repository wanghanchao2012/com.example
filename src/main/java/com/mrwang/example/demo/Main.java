package com.mrwang.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.Lists;

public class Main {

	public static void main(String[] args) {
		List<List<Studeng>> l1 =  new ArrayList<List<Studeng>>();
		List<List<Studeng>> l2 =  new ArrayList<List<Studeng>>();
		l1.add(Lists.newArrayList(Studeng.builder().name("aaa").age(12).build(),Studeng.builder().name("bbb").age(12).build()));
		l2.add(Lists.newArrayList(Studeng.builder().name("aaa").age(12).build(),Studeng.builder().name("bbb").age(12).build()));
		System.out.println(l1.equals(l2));
		System.out.println(Objects.deepEquals(l1, l2));;
		Studeng aaStudeng = new Studeng();
		aaStudeng.setAge(222);
		Studeng bbStudeng = new Studeng();
		bbStudeng.setAge(222);
		System.out.println(aaStudeng.canEqual(bbStudeng));
	}
}
