package com.models;

public class TriagemModel {
	private int id;
	private String quadro, descricao;
	
	public TriagemModel(String quadro, String descricao) {
		this.quadro = quadro;
		this.descricao = descricao;
	}
	
	public TriagemModel() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuadro() {
		return quadro;
	}

	public void setQuadro(String quadro) {
		this.quadro = quadro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
