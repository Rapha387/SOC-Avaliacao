package br.com.soc.sistema.business;

import java.util.List;

import br.com.soc.sistema.dao.funcionarios.FuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioBusinnes {
	
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private FuncionarioDao dao;
	
	public FuncionarioBusinnes() {
		this.dao = new FuncionarioDao();
	}

	public List<FuncionarioVo> TrazerTodosOsFuncionarios() {
		return dao.findAllFuncionarios();
	}

	public FuncionarioVo buscarFuncionarioPorId(String rowid) {
		try {
			Integer codigo = Integer.parseInt(rowid);
			return dao.findById(codigo);
		}catch(NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}

	public void editarFuncionario(FuncionarioVo funcionarioVo) {
		try {
			if(funcionarioVo.getNome().isEmpty())
				throw new IllegalArgumentException("O Nome nao pode estar em branco");
			if(funcionarioVo.getRowid().isEmpty())
				throw new IllegalArgumentException("O Id nao pode estar em branco");
			
			dao.updateFuncionario(funcionarioVo);
		}catch(Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public void excluirFuncionario(String rowid) {
		try {
			Integer codigo = Integer.parseInt(rowid);
			dao.deleteFuncionario(codigo);
		}catch(Exception e){
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
		
	}

	public void salvarFuncionario(FuncionarioVo funcionarioVo) {
		try {
			if(funcionarioVo.getNome().isEmpty())
				throw new IllegalArgumentException("O Nome nao pode estar em branco");
			
			dao.insertFuncionario(funcionarioVo);
		}catch(Exception e) {
			throw new BusinessException("Não foi possivel realizar a inclusão do registro");
		}
		
	}
}
