package com.mrwang.example.thrift.service;

import java.util.Collections;
import java.util.List;

import org.apache.thrift.TException;

import com.mrwang.example.thrift.dto.ProjectInfo;
import com.mrwang.example.thrift.dto.ProjectInfoQuery;
import com.mrwang.example.thrift.dto.ProjectInfoService;

public class ProjectInfoServiceImp implements ProjectInfoService.Iface {

	@Override
	public List<ProjectInfo> getProjectInfos(ProjectInfoQuery query) throws TException {
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setContent("as you to see!");
		return Collections.singletonList(projectInfo);
	}

}
