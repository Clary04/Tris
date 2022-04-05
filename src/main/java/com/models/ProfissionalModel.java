package com.models;

public class ProfissionalModel {
	private int id;
	private String login, senha, nome, cpf, grupo;

	public ProfissionalModel(String login, String senha, String nome, String cpf, String grupo) {
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.cpf = cpf;
		this.grupo = grupo;
	}
	
	public ProfissionalModel(){
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getGrupo() {
		return grupo;
	}
	
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
}
