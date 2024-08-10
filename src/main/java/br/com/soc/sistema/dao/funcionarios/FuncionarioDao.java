package br.com.soc.sistema.dao.funcionarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.dao.ExamesRealizados.ExameRealizadoDao;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioDao extends Dao{

	private ExameRealizadoDao exameRealizadoDao = new ExameRealizadoDao();
	
	public List<FuncionarioVo> findAllFuncionarios() throws SQLException {
		List<FuncionarioVo> funcionarios = new ArrayList<>();
		StringBuilder querry = new StringBuilder("Select rowid, nm_funcionario from funcionario"); 
		
		try(Connection conexao = getConexao();
			PreparedStatement ps = conexao.prepareStatement(querry.toString());
			ResultSet rs = ps.executeQuery()){
			
			while(rs.next()) {
				FuncionarioVo funcionarioVo = new FuncionarioVo();
				funcionarioVo.setRowid(rs.getString("rowid"));
				funcionarioVo.setNome(rs.getString("nm_funcionario"));
				funcionarios.add(funcionarioVo);
			}
		}catch(SQLException e){
			throw new SQLException(e.getMessage());
		}
		
		return funcionarios;
	}

	public FuncionarioVo findById(Integer codigo) throws SQLException {
		FuncionarioVo funcionarioVo = new FuncionarioVo();
		StringBuilder querry = new StringBuilder("Select rowid, nm_funcionario from funcionario ")
											.append("where rowid = ?"); 
		
		try(Connection conexao = getConexao();
				PreparedStatement ps = conexao.prepareStatement(querry.toString())){
			
			ps.setInt(1, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					funcionarioVo.setRowid(rs.getString("rowid"));
					funcionarioVo.setNome(rs.getString("nm_funcionario"));
				}
			}
		}catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		return funcionarioVo;
	}

	public void updateFuncionario(FuncionarioVo funcionarioVo) throws SQLException {
		StringBuilder query = new StringBuilder("update funcionario set nm_funcionario = ? ")
										.append("where rowid = ?");
		try(Connection con = getConexao();
				PreparedStatement  ps = con.prepareStatement(query.toString())){
			ps.setString(1, funcionarioVo.getNome());
			ps.setString(2, funcionarioVo.getRowid());
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}

	public void deleteFuncionario(Integer codigo) throws SQLException {
		try {
			exameRealizadoDao.deleteAllByIdFuncionario(codigo);
			StringBuilder query = new StringBuilder("delete from funcionario where rowid = ?");
			
			try (Connection con = getConexao();
					PreparedStatement  ps = con.prepareStatement(query.toString())){
				ps.setInt(1, codigo);
				ps.executeUpdate();
			}
		}catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}

	public void insertFuncionario(FuncionarioVo funcionarioVo) throws SQLException {
		StringBuilder query = new StringBuilder("insert into funcionario (nm_funcionario) values (?)");
		
		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())){
			ps.setString(1, funcionarioVo.getNome());
			ps.executeUpdate();
		}catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}

	public FuncionarioVo findByCodigo(Integer codigo) throws SQLException {
		StringBuilder query = new StringBuilder("select rowid, nm_funcionario nome from funcionario ")
				.append("WHERE rowid = ?");
		
		FuncionarioVo funcionario = new FuncionarioVo();
		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())){
			
			ps.setInt(1, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				if (rs.next()) {
					funcionario.setRowid(rs.getString("rowid"));
					funcionario.setNome(rs.getString("nome"));	
				}
			}
		}catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}		
		return funcionario;
	}

	public List<FuncionarioVo> findAllByNome(String valorBusca) throws SQLException {
		StringBuilder query = new StringBuilder("select rowid, nm_funcionario nome from funcionario ")
				.append("WHERE lower(nm_funcionario) like lower(?)");
		
		List<FuncionarioVo> funcionarios = new ArrayList<>();
		try(Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString())){
			
			ps.setString(1, "%"+valorBusca+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				while (rs.next()) {
					FuncionarioVo funcionario = new FuncionarioVo();
					funcionario.setRowid(rs.getString("rowid"));
					funcionario.setNome(rs.getString("nome"));	
					funcionarios.add(funcionario);
				}
			}
		}catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}		
		return funcionarios;
	}
}
