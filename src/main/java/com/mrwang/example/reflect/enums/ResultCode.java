package com.mrwang.example.reflect.enums;

public enum ResultCode {
	CODE_5200("8200", "认证失败"), CODE_5201("8201", "用户不存在"), CODE_5202("8202", "Token验证失败"),
	CODE_5203("8203", "Token刷新失败");
	private String code;
	private String name;

	private ResultCode(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}