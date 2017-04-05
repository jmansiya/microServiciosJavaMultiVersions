package com.jmansilla.demo.rest.v1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jmansilla.demo.model.v1.Actor;


@RestController(value = "actorResourceV1")
@RequestMapping(produces = "application/json")
public class ActorResource {

		@RequestMapping(value="/v1/actors/{id}", method = RequestMethod.GET)
		public Actor getActorVersion1InUrl(@PathVariable("id") String id, HttpServletRequest request){
			return this.buildV1Actor(id, request.getServerName(), String.valueOf(request.getServerPort()));
		}
		
		private com.jmansilla.demo.model.v1.Actor buildV1Actor(String id) {
			return this.buildV1Actor(id, String.format("%s", id), String.format("%s", id));
		}

		private com.jmansilla.demo.model.v1.Actor buildV1Actor(String id, String firstName, String lastName) {
			com.jmansilla.demo.model.v1.Actor result = new com.jmansilla.demo.model.v1.Actor();
			result.setActorId(id);
			result.setFirstName(firstName);
			result.setLastName(lastName);
			return result;
		}
}
