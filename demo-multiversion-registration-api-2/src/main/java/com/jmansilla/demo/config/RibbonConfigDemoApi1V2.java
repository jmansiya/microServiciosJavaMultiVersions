package com.jmansilla.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.jmansilla.demo.niws.loadbalancer.VersionedNIWSServerListFilter;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerListFilter;

public class RibbonConfigDemoApi1V2 {
	private DiscoveryClient discoveryClient;
	
	@Bean
	public ServerListFilter<Server> serverListFilter(){
		return new VersionedNIWSServerListFilter<Server>(this.discoveryClient, RibbonClientApi.DEMO_REGISTRATION_API1_V2);
	}
	
	@Autowired
	public void setDiscoveryClient(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}
}
