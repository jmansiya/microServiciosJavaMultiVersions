package com.jmansilla.demo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//Ribbon client name must be the same that there is defined in application.yml.
@Configuration
@RibbonClients(value = {
		@RibbonClient(name = "demo-multiversion-registration-api1-v1", configuration = RibbonConfigDemoApi1V1.class),
		@RibbonClient(name = "demo-multiversion-registration-api1-v2", configuration = RibbonConfigDemoApi1V2.class),
		@RibbonClient(name = "services-v1", configuration = RibbonConfigDemoApi1Ping.class)
		//@RibbonClient(name = "demo-multiversion-registration-api-1", configuration = RibbonConfigDemoApi1Ping.class)
})
public class AppConfig {
	
	@Bean(name = "loadBalancedRestTemplate")
	@LoadBalanced
	public RestTemplate loadBalancedRestTemplate(){
		return new RestTemplate();
	}
}
