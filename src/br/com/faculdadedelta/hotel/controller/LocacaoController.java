package br.com.faculdadedelta.hotel.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.hotel.dao.LocacaoDAO;
import br.com.faculdadedelta.hotel.modelo.Cliente;
import br.com.faculdadedelta.hotel.modelo.Locacao;
import br.com.faculdadedelta.hotel.modelo.Quarto;
import br.com.faculdadedelta.hotel.util.Conexao;

@ManagedBean
@ViewScoped
public class LocacaoController {

	private Locacao locacao = new Locacao();
	private Cliente clienteSelecionado = new Cliente();
	private Quarto quartoSelecionado = new Quarto();
	private LocacaoDAO dao = new LocacaoDAO();
	private List<Locacao> lista = new ArrayList<>();
	
	
	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		
		
		this.locacao = locacao;
	}

	public void limparCampos() {
	  locacao = new Locacao();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
				// Verifica se esse quarto já está reservado por alguém.
					locacao.setCliente(clienteSelecionado);
					locacao.setQuarto(quartoSelecionado);
					dao.incluir(locacao);
					limparCampos();
					exibirMensagem("Locação realizada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação." + " Tente novamente mais tarde. " + e.getMessage());
		}
		return "gerenciamento.xhtml";
	}

	public String finalizar() {
		try {
			dao.excluir(locacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		locacao = new Locacao();
		return "";
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public Quarto getQuartoSelecionado() {
		return quartoSelecionado;
	}

	public void setQuartoSelecionado(Quarto quartoSelecionado) {
		this.quartoSelecionado = quartoSelecionado;
	}

	public List<Locacao> getLista() {
		
		try {
			lista = dao.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	

}
