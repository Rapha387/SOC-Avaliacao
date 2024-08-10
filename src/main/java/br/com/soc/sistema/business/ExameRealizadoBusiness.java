package br.com.soc.sistema.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.soc.sistema.dao.ExamesRealizados.ExameRealizadoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoBusiness {
	private ExameRealizadoDao dao;
	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";

	public ExameRealizadoBusiness() {
		this.dao = new ExameRealizadoDao();
	}
	
	public List<ExameRealizadoVo> trazerTodosOsExamesRealizados() {
		try {
			return dao.findAll();
		}catch(Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	public void salvarExameRealizado(ExameRealizadoVo exameRealizadoVo) {
		try {
			Integer codigoExame = Integer.parseInt(exameRealizadoVo.getExameVo().getRowid());
			Integer codigoFuncionario = Integer.parseInt(exameRealizadoVo.getFuncionarioVo().getRowid());
			SimpleDateFormat formatoAmericano = new SimpleDateFormat("yyyy-MM-dd");
			String dataExame = formatoAmericano.format(exameRealizadoVo.getDataExame());
			
			dao.insertExameRealizado(codigoExame, codigoFuncionario, dataExame);
		}catch(NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}catch(Exception e) {
			throw new BusinessException("Não foi possível realizar o cadastro. Verifique se este casdatro já existe");
		}
	}

	public void excluirExameRealizado(ExameRealizadoVo exameRealizado) {
		try {
			SimpleDateFormat formatoAmericano = new SimpleDateFormat("yyyy-MM-dd");
			Integer codigoExame = Integer.parseInt(exameRealizado.getExameVo().getRowid());
			Integer codigoFuncionario = Integer.parseInt(exameRealizado.getFuncionarioVo().getRowid());
			String dataExame = formatoAmericano.format(exameRealizado.getDataExame());
			dao.deleteExameRealizado(codigoExame, codigoFuncionario, dataExame);
		}catch(NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}catch(Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}

	public List<ExameRealizadoVo> filtrarExamesRealizadosPorDatas(String valorBuscaDataInicio, String valorBuscaDataFim) {
		try {	
			return dao.findByDates(valorBuscaDataInicio, valorBuscaDataFim);
		}catch(Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}
}
