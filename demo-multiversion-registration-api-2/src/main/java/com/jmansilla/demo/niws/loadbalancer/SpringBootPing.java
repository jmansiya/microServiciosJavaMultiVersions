package com.jmansilla.demo.niws.loadbalancer;

import com.netflix.loadbalancer.PingUrl;

public class SpringBootPing extends PingUrl {
	
	@Override
	public String getPingAppendString() {
		// TODO Auto-generated method stub
		return "/info";
	}
}
