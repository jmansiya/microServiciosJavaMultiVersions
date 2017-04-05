package com.jmansilla.demo.niws.loadbalancer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.client.ServiceInstance;

import com.jmansilla.demo.config.RibbonClientApi;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DefaultNIWSServerListFilter;

public class VersionedNIWSServerListFilter<T extends Server> extends DefaultNIWSServerListFilter<T> {
	private static final String VERSION_KEY = "versions";
	private final org.springframework.cloud.client.discovery.DiscoveryClient discoveryClient;
	private final RibbonClientApi ribbonClientApi;
	
	public VersionedNIWSServerListFilter(org.springframework.cloud.client.discovery.DiscoveryClient _discoveryClient,
				RibbonClientApi _ribbonClientApi){		
		this.discoveryClient = _discoveryClient;
		this.ribbonClientApi = _ribbonClientApi;
	}

	 @Override
	 public List<T> getFilteredListOfServers(List<T> servers) {
		 List<T> result = new ArrayList<T>();
		 List<ServiceInstance> serviceInstances = this.discoveryClient.getInstances(this.ribbonClientApi.getNameServiceEurekaName());
		 for (ServiceInstance serviceInstance : serviceInstances) {
			 List<String> versions = this.getInstanceVersions(serviceInstance);
			 if (versions.isEmpty() || versions.contains(this.ribbonClientApi.getVersionServiceEureka())) {
				 result.addAll(this.findServerForVersion(servers, serviceInstance));
			 }
		 }
      return result;
    }
  
    private List<String> getInstanceVersions(ServiceInstance serviceInstance) {
      List<String> result = new ArrayList<String>();
      String rawVersions = serviceInstance.getMetadata().get(VERSION_KEY);
      if (StringUtils.isNotBlank(rawVersions)) {
        result.addAll(Arrays.asList(rawVersions.split(",")));
      }
      
      return result;
    }
    
    private List<T> findServerForVersion(List<T> servers, ServiceInstance serviceInstance) {
		List<T> result = new ArrayList<T>();
		for (T server : servers) {
			if (server.getHost().equals(serviceInstance.getHost()) &&
				server.getPort() == serviceInstance.getPort()) {
				result.add(server);
			}
		}
		return result;
	}
}
