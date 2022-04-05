package com.models;

public class UsuarioModel {
	private String Login, Senha;

	public UsuarioModel(String Login, String Senha) {
		this.Login = Login;
		this.Senha = Senha;
	}

	public UsuarioModel() {
		
	}
	
	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}


}
