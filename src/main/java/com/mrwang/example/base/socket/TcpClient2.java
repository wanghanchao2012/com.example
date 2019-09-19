package com.mrwang.example.base.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpClient2 {
	public static void main(String[] args) {
		try {
//1.创建客户端socket，指定服务器地址和端口
			Socket socket = new Socket("localhost", 8080);
//2.获取输出流，向服务器端发送信息
			OutputStream os = socket.getOutputStream();// 字节输出流
			PrintWriter pw = new PrintWriter(os);// 将输出流包装成打印流
			pw.write("用户名：admin1;密码：123");
			pw.flush();
			socket.shutdownOutput();// 关闭输出流
//3.获取输入流。读取服务器端相应信息
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while ((info = br.readLine()) != null)
				System.out.println("我是客户端2，服务器说:" + info);
//4.关闭资源
			br.close();
			is.close();
			pw.flush();
			pw.close();
			os.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
