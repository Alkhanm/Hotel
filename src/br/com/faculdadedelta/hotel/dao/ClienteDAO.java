package br.com.faculdadedelta.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.hotel.modelo.Cliente;
import br.com.faculdadedelta.hotel.util.Conexao;

public class ClienteDAO {

	public void incluir(Cliente cliente) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO cliente (nome,cpf,email,telefone) "
				+ " VALUES (?,?,?,?) ";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getTelefone());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void alterar(Cliente cliente) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE clientes "
				+ " SET nome = ?, "
				+ " cpf = ?, "
				+ " email = ?"
				+ " telefone = ? "
				+ " WHERE id = ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getTelefone());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}
	
	public void excluir(Cliente cliente) throws Exception {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM clientes WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		try {
			ps.setLong(1, cliente.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, null);
		}
	}

	public Cliente pesquisarCliente(Long id) throws Exception  {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, nome, cpf, email, telefone FROM cliente WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		Cliente resultado = new Cliente();
		try {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				resultado.setId(rs.getLong("id"));
				resultado.setNome(rs.getString("nome"));
				resultado.setCpf(rs.getString("cpf"));
				resultado.setEmail(rs.getString("email"));
				resultado.setTelefone(rs.getString("telefone"));
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally{
			Conexao.fecharConexao(conn, ps, rs);
		}
		return resultado;
	}
	
	public List<Cliente> listar() throws Exception{
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT c.id, nome, cpf, email, telefone " + 
				     " FROM cliente c " + 
				     " LEFT JOIN locacao l" + 
				     " ON c.id = l.id_cliente" +
				     " WHERE l.id_cliente IS NULL;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = null;
		List<Cliente> listaRetorno = new ArrayList<>();
		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getLong("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefone(rs.getString("telefone"));
				listaRetorno.add(cliente);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			Conexao.fecharConexao(conn, ps, rs);
		}
		return listaRetorno;
	}
	
}










