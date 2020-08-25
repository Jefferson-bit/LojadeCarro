package com.crash.domain.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.crash.domain.Veiculo;

public class VeiculoDTO implements Serializable {

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
	@Length(min = 5, max = 150, message = "Minimo de 5 a 150 caracteres")
	private String tipoVeiculo;
	
	private String cor;
	private Double kmRodado;
	private Integer portas;
	private String cambio;
	private String informacoes;
	private Integer categoriaId;
	
	public VeiculoDTO() {
	}
	
	public VeiculoDTO(Veiculo obj) {
		id = obj.getId();
		modelo = obj.getModelo();
		ano = obj.getAno();
		preco = obj.getPreco();
		tipoVeiculo = obj.getTipoVeiculo();
		cor = obj.getDetalhes().getCor();
		kmRodado = obj.getDetalhes().getKmRodado();
		portas = obj.getDetalhes().getPortas();
		cambio = obj.getDetalhes().getCambio();
		informacoes = obj.getDetalhes().getInformacoes();
		categoriaId = obj.getCategorias().getId();
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

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	
	
}
