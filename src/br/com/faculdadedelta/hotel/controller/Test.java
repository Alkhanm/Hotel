package br.com.faculdadedelta.hotel.controller;

import java.util.ArrayList;
import br.com.faculdadedelta.hotel.dao.LocacaoDAO;
import br.com.faculdadedelta.hotel.modelo.Locacao;

public class Test {

	public static void main(String[] args) throws Exception {
		
		LocacaoController lc = new LocacaoController();
		LocacaoDAO ld = new LocacaoDAO();
		
		ArrayList<Locacao> al = (ArrayList<Locacao>) lc.getLista();
		System.out.println(al.get(1));
		
		System.out.println("fim");
	}

}
