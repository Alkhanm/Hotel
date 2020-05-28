package br.com.faculdadedelta.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.com.faculdadedelta.hotel.modelo.Quarto;
import br.com.faculdadedelta.hotel.util.Conexao;

public class QuartoDAO {

	
	public void incluir(Quarto quarto) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO quarto (numero, reservado) "
				+ " VALUES (?,?) ";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setInt(1, quarto.getNumero());
			ps.setBoolean(2, quarto.isReservado());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	
	public void excluir(Quarto quarto) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM quartos WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, quarto.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public Quarto pesquisarPorId(Long id) throws Exception  {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, numero, reservado FROM quarto WHERE id = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		Quarto resultado =  new Quarto();
		
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				resultado.setId(rs.getLong("id"));
				resultado.setNumero(rs.getInt("numero"));
				resultado.setReservado(rs.getBoolean("reservado"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally{
			Conexao.fecharConexao(conn, ps, rs);
		}
		return resultado;
	}

	public List<Quarto> listar() throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, numero, reservado FROM quarto WHERE reservado = false";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		List<Quarto> listaRetorno = new ArrayList<>();
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				Quarto quarto = new Quarto();
				quarto.setId(rs.getLong("id"));
				quarto.setNumero(rs.getInt("numero"));
				quarto.setReservado(rs.getBoolean("reservado"));
				listaRetorno.add(quarto);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}
	
	
}







