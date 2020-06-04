package br.com.faculdadedelta.hotel.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class SessaoController implements Serializable{

	private static final long serialVersionUID = 1L;
	private String usuario;
	private String senha;
	
	
	public String login() {
		if (usuario.equals("admin") && senha.equals("admin123")) {
			return "hospedagem.xhtml";
		} return null; 
	}
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	

}
