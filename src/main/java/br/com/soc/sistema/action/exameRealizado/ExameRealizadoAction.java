package br.com.soc.sistema.action.exameRealizado;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.api.BaixarRelatorioExcel;
import br.com.soc.sistema.business.ExameRealizadoBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoAction extends Action{
	private ExameRealizadoBusiness business = new ExameRealizadoBusiness();
	private BaixarRelatorioExcel baixarRelatorioExcel = new BaixarRelatorioExcel();
	
	private List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
	private ExameRealizadoVo exameRealizadoVo = new ExameRealizadoVo();
	
	private String dataExame;
	
	private String valorBuscaDataInicio;
	private String valorBuscaDataFim;
	
	private String mensagemErro = "";
	
	public String filtrar() {
		try {
			this.examesRealizados = business.filtrarExamesRealizadosPorDatas(valorBuscaDataInicio, valorBuscaDataFim);
		}catch(Exception e) {
			return REDIRECT;
		}
		
		return SUCCESS;
	}
	
	public String todos() {
		
		try {
			this.examesRealizados.addAll(business.trazerTodosOsExamesRealizados());
		}catch(Exception e) {
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String novo() {
		if(this.exameRealizadoVo.getExameVo().getRowid() == null || this.exameRealizadoVo.getFuncionarioVo().getRowid() == null) {
			return INPUT;
		}
		
		try {
			business.salvarExameRealizado(exameRealizadoVo);
		}catch(Exception e) {
			mensagemErro = e.getMessage();
			return INPUT;
		}
		
		return REDIRECT;
	}
	
	
	public String excluir() {
		
		try {
			business.excluirExameRealizado(exameRealizadoVo);
		}catch(Exception e) {
			return REDIRECT;
		}
		
		return REDIRECT;
	}
	

	public String baixarRelatorio(){
		
		try {
			this.examesRealizados = business.filtrarExamesRealizadosPorDatas(valorBuscaDataInicio, valorBuscaDataFim);
			if(this.examesRealizados.isEmpty()) {
				mensagemErro = "Nenhum registro encontrado para efetuar o dowload";
				return SUCCESS;
			}
				
			baixarRelatorioExcel.baixar(examesRealizados);
			mensagemErro = "Dowload efetuado com sucesso";
				
		}catch(Exception e) {
			mensagemErro = "Não foi possível baixar o relatório";
			return SUCCESS; 
		}
		
		return SUCCESS;
	}
	
	
	public String getDataExame() {
		return dataExame;
	}

	public void setDataExame(String dataExame) throws ParseException {
		this.dataExame = dataExame;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		exameRealizadoVo.setDataExame(formatter.parse(this.dataExame)); 
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
		return exameRealizadoVo;
	}

	public void setExameRealizado(ExameRealizadoVo exameRealizado) {
		this.exameRealizadoVo = exameRealizado;
	}

	public String getValorBuscaDataInicio() {
		return valorBuscaDataInicio;
	}

	public void setValorBuscaDataInicio(String valorBuscaDataInicio) {
		this.valorBuscaDataInicio = valorBuscaDataInicio;
	}

	public String getValorBuscaDataFim() {
		return valorBuscaDataFim;
	}

	public void setValorBuscaDataFim(String valorBuscaDataFim) {
		this.valorBuscaDataFim = valorBuscaDataFim;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}	
	
}
