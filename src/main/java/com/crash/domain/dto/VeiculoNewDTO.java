package com.crash.domain.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.crash.domain.Veiculo;

public class VeiculoNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "Preenchimento Obrigatorio")
	@Length(min = 5, max = 150, message = "Minimo de 5 a 150 caracteres")
	private String modelo;
	@NotEmpty(message = "Preenchimento Obrigatorio")
	private Date ano;
	@NotEmpty(message = "Preenchimento Obrigatorio")
	private Double preco;
	@NotEmpty(message = "Preenchimento Obrigatorio")
	private String tipoVeiculo;
	
	private String marca;
	private Integer categoriaId;
	private Integer detalhesId;
	
	@NotEmpty(message = "Preenchimento Obrigatorio")
	@Length(min = 5, max = 150, message = "Minimo de 5 a 150 caracteres")
	private String cor;
	@NotEmpty(message = "Preenchimento Obrigatorio")
	@Length(min = 5, max = 150, message = "Minimo de 5 a 150 caracteres")
	private Double kmRodado;
	@NotEmpty(message = "Preenchimento Obrigatorio")
	@Length(min = 2, max = 8, message = "Minimo de 2 a 8 caracteres")
	private Integer portas;
	@NotEmpty(message = "Preenchimento Obrigatorio")
	@Length(min = 5, max = 150, message = "Minimo de 5 a 150 caracteres")
	private String cambio;
	@NotEmpty(message = "Preenchimento Obrigatorio")
	@Length(min = 5, max = 3000, message = "Minimo de 5 a 3000 caracteres")
	private String informacoes;
	
	
	public VeiculoNewDTO() {
	}
	
	public VeiculoNewDTO(Veiculo obj) {
		id = obj.getId();
		modelo = obj.getModelo();
		ano = obj.getAno();
		preco = obj.getPreco();
		tipoVeiculo = obj.getTipoVeiculo();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
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

	public Integer getDetalhesId() {
		return detalhesId;
	}

	public void setDetalhesId(Integer detalhesId) {
		this.detalhesId = detalhesId;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
}
