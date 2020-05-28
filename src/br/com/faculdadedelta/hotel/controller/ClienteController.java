package br.com.faculdadedelta.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.com.faculdadedelta.hotel.dao.ClienteDAO;
import br.com.faculdadedelta.hotel.modelo.Cliente;

@ManagedBean
@SessionScoped
public class ClienteController {

	private Cliente cliente = new Cliente();
	private ClienteDAO dao = new ClienteDAO();



	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public void limparCampos() {
		cliente = new Cliente();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			dao.incluir(cliente);
			limparCampos();
			exibirMensagem("Inclusão realizada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação." + " Tente novamente mais tarde. " + e.getMessage());
		}
		return "cadastro.xhtml";
	}
	
	public List<Cliente> getListar(){
		List<Cliente> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realiza ao listar.");
		}
		
		return listaRetorno;
	}
	
}







