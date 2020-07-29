package com.crash.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Imagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Lob
	private byte[] fotos;
	
	public Imagem() {
	}
	
	public byte[] getFotos() {
		return fotos;
	}
	public void setFotos(byte[] fotos) {
		this.fotos = fotos;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
}
