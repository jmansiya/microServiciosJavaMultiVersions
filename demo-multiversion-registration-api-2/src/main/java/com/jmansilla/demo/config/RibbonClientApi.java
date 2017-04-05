package com.jmansilla.demo.config;

public enum RibbonClientApi {
	DEMO_REGISTRATION_API1_V1("demo-multiversion-registration-api-1", "v1"),
	DEMO_REGISTRATION_API1_V2("demo-multiversion-registration-api-1", "v2");
	
	private final String nameServiceEurekaName;
	private final String versionServiceEureka;
	
	private RibbonClientApi(String name, String version) {
		// TODO Auto-generated constructor stub
		this.nameServiceEurekaName = name;
		this.versionServiceEureka = version;
	}

	public String getNameServiceEurekaName() {
		return nameServiceEurekaName;
	}

	public String getVersionServiceEureka() {
		return versionServiceEureka;
	}
	
}
