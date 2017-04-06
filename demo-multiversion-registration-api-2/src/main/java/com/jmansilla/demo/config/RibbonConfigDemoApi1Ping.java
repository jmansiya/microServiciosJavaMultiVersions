package com.jmansilla.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.jmansilla.demo.niws.loadbalancer.VersionedNIWSServerListFilter;
import com.jmansilla.demo.niws.loadbalancer.VersionedNIWSServerListFilterPing;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerListFilter;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

public class RibbonConfigDemoApi1Ping {

//	  @Autowired
//	  IClientConfig ribbonClientConfig;
//
//	  @Bean
//	  public IPing ribbonPing(IClientConfig config) {
//	    return new PingUrl();
//	  }
private DiscoveryClient discoveryClient;
	
	@Bean
	public ServerListFilter<Server> serverListFilter(){
		return new VersionedNIWSServerListFilterPing<Server>(this.discoveryClient, RibbonClientApi.DEMO_REGISTRATION_API1_V1);
	}
	
	@Autowired
	public void setDiscoveryClient(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}
	 
	@Bean
	public IRule ribbonRule(IClientConfig config) {
		return new RetryRule(new RoundRobinRule(), 50);
	}
	
}
