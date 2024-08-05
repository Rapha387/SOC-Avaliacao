package br.com.soc.sistema.action.exameRealizado;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.soc.sistema.business.ExameRealizadoBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoAction extends Action{
	private ExameRealizadoBusiness business = new ExameRealizadoBusiness();
	
	private List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
	private ExameRealizadoVo exameRealizadoVo = new ExameRealizadoVo();
	private ExameRealizadoVo exameRealizado = new ExameRealizadoVo();
	
	private String dataExame;
	
	public String todos() {
		this.examesRealizados.addAll(business.trazerTodosOsExamesRealizados());
		
		return SUCCESS;
	}
	
	public String novo() {
		if(this.exameRealizadoVo.getExameVo().getRowid() == null || this.exameRealizadoVo.getFuncionarioVo().getRowid() == null) {
			return INPUT;
		}
		
		business.salvarExameRealizado(exameRealizadoVo);
		
		return REDIRECT;
	}
	
	
	public String excluir() {
		business.excluirExameRealizado(exameRealizado);
		
		return REDIRECT;
	}
	
	
	
	public String getDataExame() {
		return dataExame;
	}

	public void setDataExame(String dataExame) throws ParseException {
		this.dataExame = dataExame;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		exameRealizado.setDataExame(formatter.parse(this.dataExame)); 
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

	public ExameRealizadoVo getExameRealizado() {
		return exameRealizado;
	}

	public void setExameRealizado(ExameRealizadoVo exameRealizado) {
		this.exameRealizado = exameRealizado;
	}	
	
}
