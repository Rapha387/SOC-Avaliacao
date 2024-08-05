package br.com.soc.sistema.action.exameRealizado;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.ExameRealizadoBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoAction extends Action{
	private ExameRealizadoBusiness business = new ExameRealizadoBusiness();
	
	private List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
	private ExameRealizadoVo exameRealizadoVo = new ExameRealizadoVo();
	
	public String todos() {
		this.examesRealizados.addAll(business.trazerTodosOsExamesRealizados());
		
		return SUCCESS;
	}

	public String editar() {
		
		
		return EDITAR;
	}
	
	public String novo() {
		if(this.exameRealizadoVo.getExameVo().getRowid() == null || this.exameRealizadoVo.getFuncionarioVo().getRowid() == null) {
			return INPUT;
		}
		
		business.salvarExameRealizado(exameRealizadoVo);
		
		return REDIRECT;
	}
	
	
	public ExameRealizadoVo getExameRealizadoVo() {
		return exameRealizadoVo;
	}

	public void setExameRealizadoVo(ExameRealizadoVo exameRealizadoVo) {
		this.exameRealizadoVo = exameRealizadoVo;
	}

	public List<ExameRealizadoVo> getExamesRealizados() {
		return examesRealizados;
	}

	public void setExamesRealizados(List<ExameRealizadoVo> examesRealizados) {
		this.examesRealizados = examesRealizados;
	}	
}
