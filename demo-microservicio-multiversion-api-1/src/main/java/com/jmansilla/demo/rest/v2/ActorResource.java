package com.jmansilla.demo.rest.v2;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jmansilla.demo.model.Actor;

@RestController(value = "actorResourceV2")
@RequestMapping(produces = "application/json")
public class ActorResource {

		@RequestMapping(value="/v2/actors/{id}", method = RequestMethod.GET)
		public Actor getActorVersion1InUrl(@PathVariable("id") String id, HttpServletRequest request){
			Actor nActor = new Actor();
			List<String> peliculas =  new ArrayList<String>();
			peliculas.add("La niña de tus ojos");
			peliculas.add("Celda 211");
			peliculas.add("La caja 507");
			
			nActor.setNombre("Antonio Resines");
			nActor.setPremiado(true);
			nActor.setPeliculas(peliculas);
			
			return nActor;
		}
}
