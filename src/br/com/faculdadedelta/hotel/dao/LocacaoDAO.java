package br.com.faculdadedelta.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.hotel.modelo.Cliente;
import br.com.faculdadedelta.hotel.modelo.Locacao;
import br.com.faculdadedelta.hotel.modelo.Quarto;
import br.com.faculdadedelta.hotel.util.Conexao;

public class LocacaoDAO {
	
	QuartoDAO qd = new QuartoDAO();
	ClienteDAO cd = new ClienteDAO();
	
	public void incluir(Locacao locacao) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO locacao (id_cliente, id_quarto, casal, data_inicio, data_fim) "
				+ " VALUES (?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, locacao.getCliente().getId());
			ps.setLong(2, locacao.getQuarto().getId());
			ps.setBoolean(3, locacao.isCasal());
			ps.setDate(4, Date.valueOf(locacao.getDataInicio()));
			ps.setDate(5, Date.valueOf(locacao.getDataFim()));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void excluir(Locacao locacao) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM locacao WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, locacao.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public List<Locacao> listar() throws ClassNotFoundException, SQLException{
		List<Locacao> lista = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = " SELECT l.id AS idLocacao, l.data_inicio AS dataI, l.data_fim AS dataF, l.casal AS casal, "
				   + " c.id AS idCliente, c.nome AS nome, "
				   + " q.id AS idQuarto, q.numero AS numero "
				   + " FROM locacao l "
				   + " JOIN cliente c ON l.id_cliente = c.id "
				   + " JOIN quarto  q ON l.id_quarto = q.id; ";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		
		try {
			rs = ps.executeQuery();
			while(rs.next()) {
				Locacao locacao = new Locacao();
				locacao.setId(rs.getLong("idLocacao"));
				locacao.setDataInicio(rs.getDate("dataI").toLocalDate());
				locacao.setDataFim(rs.getDate("dataF").toLocalDate());
				locacao.setCasal(rs.getBoolean("casal"));
				
				Cliente cliente = new Cliente();
				cliente.setId(rs.getLong("idCliente"));
				cliente.setNome(rs.getString("nome"));
				locacao.setCliente(cliente);
				
				Quarto quarto = new Quarto();
				quarto.setId(rs.getLong("idQuarto"));
				quarto.setNumero(rs.getInt("numero"));
				locacao.setQuarto(quarto);
				
				lista.add(locacao);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return lista;
	}
	
	public Locacao pesquisarLocacao(String cliente) throws Exception  {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		Locacao resultado = null;
		
		try {
			ps.setString(1, "");
			rs = ps.executeQuery();
		} catch (Exception e) {
			throw new Exception(e);
		} finally{
			Conexao.fecharConexao(conn, ps, rs);
		}
		return resultado;
	}
}






