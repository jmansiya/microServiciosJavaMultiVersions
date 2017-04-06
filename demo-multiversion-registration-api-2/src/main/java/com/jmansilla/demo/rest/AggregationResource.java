package com.jmansilla.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.jmansilla.demo.model.v1.Actor;

@RestController
@RequestMapping(value = "/aggregation", produces = "application/json")
public class AggregationResource {
  private static final String ACTORS_SERVICE_ID_V1 = "demo-multiversion-registration-api1-v1";
  private static final String ACTORS_SERVICE_ID_V2 = "demo-multiversion-registration-api1-v2";
  private static final String ACTORS_SERVICE_ID_PING = "services-v1";
  private static final String ACTORS_SERVICE_PING = "demo-multiversion-registration-api-1";

  @Autowired
  private RestTemplate loadBalancedRestTemplate;

  @RequestMapping(value = "/v1/actors/{id}", method = RequestMethod.GET)
  public Actor findActorV1(@PathVariable(value = "id") String id) {
    String url = String.format("http://%s/v1/actors/{id}", ACTORS_SERVICE_ID_V1);
    return this.loadBalancedRestTemplate.getForObject(url, Actor.class, id);
  }

  @RequestMapping(value = "/v2/actors/{id}", method = RequestMethod.GET)
  public com.jmansilla.demo.model.v2.Actor findActorV2(@PathVariable(value = "id") String id) {
    String url = String.format("http://%s/v2/actors/{id}", ACTORS_SERVICE_ID_V2);
    return this.loadBalancedRestTemplate.getForObject(url, com.jmansilla.demo.model.v2.Actor.class, id);
  }
  
  @RequestMapping(value = "/v1/actorsPing/{id}", method = RequestMethod.GET)
  public Actor findActorPing(@PathVariable(value = "id") String id) {
    String url = String.format("http://%s/v1/actors/{id}", ACTORS_SERVICE_ID_PING);
    return this.loadBalancedRestTemplate.getForObject(url, Actor.class, id);
  }
}
