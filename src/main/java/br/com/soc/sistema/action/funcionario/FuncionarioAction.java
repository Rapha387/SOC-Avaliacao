package br.com.soc.sistema.action.funcionario;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.FuncionarioBusinnes;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioAction extends Action{
	
	private FuncionarioBusinnes business = new FuncionarioBusinnes();
	
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();
	
	public String novo() {
		if(funcionarioVo.getNome() == null)
			return INPUT;
		
		business.salvarFuncionario(funcionarioVo);
		
		return REDIRECT;
	}
	
	public String todos() {
		funcionarios.addAll(business.TrazerTodosOsFuncionarios());
		
		return SUCCESS;
	}
	
	public String editar() {
		if(funcionarioVo.getRowid() == null)
			return ERROR;
		
		try {
			funcionarioVo = business.buscarFuncionarioPorId(funcionarioVo.getRowid());			
		}catch(Exception e) {
			return ERROR;
		}
		
		return EDITAR;
	}
	
	public String salvarEdicao() {
		business.editarFuncionario(funcionarioVo);
		
		return REDIRECT;
	}
	
	public String excluir() {
		if(funcionarioVo.getRowid() == null) {
			return REDIRECT;
		}
		
		try {
			business.excluirFuncionario(funcionarioVo.getRowid());
		}catch(Exception e) {
			return ERROR;
		}
		
		return REDIRECT;
	}

	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}

	public void setFuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}

	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}
}
