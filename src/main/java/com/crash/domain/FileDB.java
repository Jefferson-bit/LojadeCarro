package com.crash.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_file")
public class FileDB  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name ="uuid", strategy = "uuid2")
	private String id;
	private String nome;
	private String type;
	@Lob
	private byte[] data;
	
	public FileDB() {
	}

	public FileDB( String nome, String type, byte[] data) {
		this.nome = nome;
		this.type = type;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void String(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
