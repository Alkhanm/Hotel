package br.com.faculdadedelta.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.hotel.dao.QuartoDAO;
import br.com.faculdadedelta.hotel.modelo.Quarto;

@ManagedBean
@SessionScoped
public class QuartoController {

	private QuartoDAO dao = new QuartoDAO();
	private Quarto quarto = new Quarto();

	public void limparCampos() {
		quarto = new Quarto();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void salvar() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação." + " Tente novamente mais tarde. " + e.getMessage());
		}
	}
	public List<Quarto> getListar(){
		List<Quarto> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realiza ao listar.");
		}
		return listaRetorno;
	}
	
}
