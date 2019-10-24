package com.mrwang.example.thrift;

import java.util.List;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.mrwang.example.thrift.dto.ProjectInfo;
import com.mrwang.example.thrift.dto.ProjectInfoQuery;
import com.mrwang.example.thrift.dto.ProjectInfoService;

public class NonblockingClient {
	public static void main(String[] args) {
		// 这些与Server的进行对应，跟网络七层协议相似，两边顺序是反的
		TTransport transport = new TFastFramedTransport(new TSocket("localhost", 8899), 600);
		TProtocol protocol = new TCompactProtocol(transport);
		ProjectInfoService.Client client = new ProjectInfoService.Client(protocol);

		try {
			// 开启网络传输
			transport.open();
			/**
			 * 关键：client本来就没有getPersonByUsername方法，这是通过网络传输调用
			 */
			List<ProjectInfo> projectInfos = client.getProjectInfos(new ProjectInfoQuery());
			for (ProjectInfo projectInfo : projectInfos) {
				System.out.println(projectInfo.getContent());
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage(), ex);
		} finally {
			// 最后关闭transport
			transport.close();
		}
	}
}
