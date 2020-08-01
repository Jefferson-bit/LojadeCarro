package com.crash.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_detalhes")
public class Detalhes implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String cor;
	private Double kmRodado;
	private Integer portas;
	private String cambio;
	private String informacoes;
	
	@JsonIgnore
	@OneToMany(mappedBy = "detalhes")
	private List<Veiculo> veiculos = new ArrayList<>();
	
	public Detalhes() {
	}

	public Detalhes(Integer id, String cor, Double kmRodado, Integer portas, String cambio, String informacoes) {
		this.id = id;
		this.cor = cor;
		this.kmRodado = kmRodado;
		this.portas = portas;
		this.cambio = cambio;
		this.informacoes = informacoes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Double getKmRodado() {
		return kmRodado;
	}

	public void setKmRodado(Double kmRodado) {
		this.kmRodado = kmRodado;
	}

	public Integer getPortas() {
		return portas;
	}

	public void setPortas(Integer portas) {
		this.portas = portas;
	}

	public String getCambio() {
		return cambio;
	}

	public void setCambio(String cambio) {
		this.cambio = cambio;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Detalhes other = (Detalhes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
