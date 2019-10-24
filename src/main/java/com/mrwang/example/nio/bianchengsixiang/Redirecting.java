package com.mrwang.example.nio.bianchengsixiang;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Redirecting {
	public static void main(String[] args) throws IOException {
		PrintStream console = System.out;
		BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("/Users/wanghanchao/temp_file/data.txt"));
		PrintStream outPrintStream = new PrintStream("test.out");
		System.setIn(inputStream);
		System.setErr(outPrintStream);
		System.setOut(outPrintStream);
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String string;
		while ((string = bReader.readLine()) != null) {
			System.out.println(string);
		}
		outPrintStream.close();
		System.setOut(console);
	}
}
