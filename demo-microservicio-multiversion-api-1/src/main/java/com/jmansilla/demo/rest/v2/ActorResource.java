package com.jmansilla.demo.rest.v2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jmansilla.demo.model.v2.Actor;


@RestController(value = "actorResourceV2")
@RequestMapping(produces = "application/json")
public class ActorResource {

	@RequestMapping(value = "/v2/actors/{id}", method = RequestMethod.GET)
	public Actor getActorVersion2InUrl(@PathVariable("id") String id, HttpServletRequest request) {
		return this.buildV2Actor(id, request.getServerName(), String.valueOf(request.getServerPort()));
	}

	private Actor buildV2Actor(String id) {
		return this.buildV2Actor(id, String.format("First%s", id), String.format("Last%s", id));
	}

	private Actor buildV2Actor(String id, String firstName, String lastName) {
		Actor result = new Actor();
		result.setId(id);
		result.setFirst(firstName);
		result.setLast(lastName);
		return result;
	}
}
