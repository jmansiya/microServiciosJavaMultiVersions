package com.jmansilla.demo.rest.v1;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jmansilla.demo.model.Actor;

@RestController(value = "actorResourceV1")
@RequestMapping(produces = "application/json")
public class ActorResource {

		@RequestMapping(value="/v1/actors/{id}", method = RequestMethod.GET)
		public Actor getActorVersion1InUrl(@PathVariable("id") String id, HttpServletRequest request){
			Actor nActor = new Actor();
			List<String> peliculas =  new ArrayList<String>();
			peliculas.add("Desperado");
			peliculas.add("Asesinos");
			peliculas.add("El mexicano");
			
			nActor.setNombre("Antonio Banderas");
			nActor.setPremiado(true);
			nActor.setPeliculas(peliculas);
			
			return nActor;
		}
}
