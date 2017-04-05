package com.jmansilla.demo.niws.loadbalancer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jmansilla.demo.config.RibbonClientApi;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DefaultNIWSServerListFilter;

public class VersionedNIWSServerListFilter<T extends Server> extends DefaultNIWSServerListFilter<T> {
	private static final String VERSION_KEY = "versions";
	private final DiscoveryClient discoveryClient;
	private final RibbonClientApi ribbonClientApi;
	
	public VersionedNIWSServerListFilter(DiscoveryClient _discoveryClient,
				RibbonClientApi _ribbonClientApi){		
		this.discoveryClient = _discoveryClient;
		this.ribbonClientApi = _ribbonClientApi;
	}

	 @Override
	 public List<T> getFilteredListOfServers(List<T> servers) {
		 List<T> result = new ArrayList<T>();
		 List<InstanceInfo> serviceInstances = this.discoveryClient.getInstancesById(this.ribbonClientApi.getNameServiceEurekaName());
		 for (InstanceInfo serviceInstance : serviceInstances) {
			 List<String> versions = this.getInstanceVersions(serviceInstance);
			 if (versions.isEmpty() || versions.contains(this.ribbonClientApi.getVersionServiceEureka())) {
				 result.addAll(this.findServerForVersion(servers, serviceInstance));
			 }
		 }
      return result;
    }
  
    private List<String> getInstanceVersions(InstanceInfo serviceInstance) {
      List<String> result = new ArrayList<String>();
      String rawVersions = serviceInstance.getMetadata().get(VERSION_KEY);
      if (StringUtils.isNotBlank(rawVersions)) {
        result.addAll(Arrays.asList(rawVersions.split(",")));
      }
      
      return result;
    }
    
    private List<T> findServerForVersion(List<T> servers, InstanceInfo serviceInstance) {
		List<T> result = new ArrayList<T>();
		for (T server : servers) {
			if (server.getHost().equals(serviceInstance.getHostName()) &&
				server.getPort() == serviceInstance.getPort()) {
				result.add(server);
			}
		}
		return result;
	}
}
