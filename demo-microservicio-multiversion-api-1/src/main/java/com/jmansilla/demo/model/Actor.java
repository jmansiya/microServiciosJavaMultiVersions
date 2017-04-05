package com.jmansilla.demo.model;

import java.util.List;

public class Actor {
	private String nombre;
	private boolean premiado;
	private List<String> peliculas;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isPremiado() {
		return premiado;
	}
	public void setPremiado(boolean premiado) {
		this.premiado = premiado;
	}
	public List<String> getPeliculas() {
		return peliculas;
	}
	public void setPeliculas(List<String> peliculas) {
		this.peliculas = peliculas;
	}
	
}
