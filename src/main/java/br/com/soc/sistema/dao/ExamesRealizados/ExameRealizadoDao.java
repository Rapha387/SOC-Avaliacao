package br.com.soc.sistema.dao.ExamesRealizados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameRealizadoVo;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class ExameRealizadoDao extends Dao{

	public List<ExameRealizadoVo> findAll() {
		List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
		SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
		 
		StringBuilder querry = new StringBuilder("select ef.rowid_funcionario, f.nm_funcionario, ef.rowid_exame, e.nm_exame, FORMATDATETIME(ef.dt_exame, 'dd/MM/yyyy') as dt_exame")
									.append(" from exame_funcionario ef")
									.append(" JOIN funcionario f on (ef.rowid_funcionario = f.rowid)")
									.append(" JOIN exame e on (ef.rowid_exame = e.rowid)")
									.append(" order by ef.rowid_funcionario");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(querry.toString());
			ResultSet rs = ps.executeQuery()){
		
			while(rs.next()) {
				ExameRealizadoVo exameRealizadoVo = new ExameRealizadoVo();
				exameRealizadoVo.setExameVo(new ExameVo(rs.getString("rowid_exame"), rs.getString("nm_exame")));
				exameRealizadoVo.setFuncionarioVo(new FuncionarioVo(rs.getString("rowid_funcionario"), rs.getString("nm_funcionario")));
				exameRealizadoVo.setDataExame(formatoBrasileiro.parse(rs.getString("dt_exame")));
				
				examesRealizados.add(exameRealizadoVo);
			}
		}catch(SQLException | ParseException e) {
			e.printStackTrace();
		}
		
		return examesRealizados;
	}

	public void insertExameRealizado(Integer codigoExame, Integer codigoFuncionario, String dataExame) {
		StringBuilder query = new StringBuilder("INSERT INTO exame_funcionario (rowid_funcionario, rowid_exame, dt_exame) values (?, ?, ?)");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			ps.setInt(1, codigoFuncionario);
			ps.setInt(2, codigoExame);
			ps.setString(3, dataExame);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void deleteExameRealizado(Integer codigoExame, Integer codigoFuncionario, String dataExame) {
		StringBuilder query = new StringBuilder("delete from exame_funcionario where rowid_funcionario = ?")
											.append(" and rowid_exame = ? and dt_exame = ?");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			ps.setInt(1, codigoFuncionario);
			ps.setInt(2, codigoExame);
			ps.setString(3, dataExame);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
