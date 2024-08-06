<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title><s:text name="label.titulo.pagina.consulta"/></title>
	<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body class="bg-secondary">	
	
		<jsp:include page="/components/header.jsp"/>
			
		<div class="container">
			<div class="row mt-5 mb-2">
				<div class="col-sm p-0">
					<s:form action="/filtrarExamesRealizados.action">
						<div class="input-group">
							<span class="input-group-text">
								<strong><s:text name="label.buscar.por"/> DATA</strong>
							</span>	
							
							<span class="input-group-text">
								<strong>DE:</strong>
							</span>	

							<s:textfield type="date" cssClass="form-control" id="nome" name="valorBuscaDataInicio"/>
							
							<span class="input-group-text">
								<strong>ATÉ:</strong>
							</span>	
							
							<s:textfield type="date"  cssClass="form-control" id="nome" name="valorBuscaDataFim"/>
							
							<button class="btn btn-primary" type="submit"><s:text name="label.pesquisar"/></button>
						</div>
					</s:form>			
				</div>				
			</div>

			<div class="row">
				<table class="table table-light table-striped align-middle">
					<thead>
						<tr>
							<th>FUNCIONÁRIO</th>
							<th>EXAME</th>
							<th>DATA EXAME</th>
							<th class="text-end mt-5"><s:text name="label.acao"/></th>
						</tr>
					</thead>
					
					<tbody>
						<s:iterator value="examesRealizados" >
							<tr>
								<td>${funcionarioVo.nome}</td>
								<td>${exameVo.nome}</td>
								<td><fmt:formatDate value="${dataExame}" type="date" pattern="dd/MM/yyyy"/></td>
								<td class="text-end">
									<s:url action="excluirExamesRealizados" var="editar">
										<s:param name="exameRealizadoVo.exameVo.rowid" value="%{exameVo.rowid}"></s:param>
										<s:param name="exameRealizadoVo.funcionarioVo.rowid" value="%{funcionarioVo.rowid}"></s:param>
										<s:param name="dataExame">
											<fmt:formatDate value="${dataExame}" type="date" pattern="dd-MM-yyyy"/>
										</s:param>
									</s:url>
									

									<a href="#" id="${editar}" onclick="pegarIdBotao(this)" class="btn btn-danger btnExcluir" data-bs-toggle="modal" data-bs-target="#confirmarExclusao">
										<s:text name="label.excluir"/>
									</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
					
					<tfoot class="table-secondary">
						<tr>
							<td colspan="4">
								<s:url action="novoExamesRealizados" var="novo" />
									
								<a href="${novo}" class="btn btn-success">
									MARCAR <s:text name="label.novo"/> EXAME
								</a>
								
								<s:url action="baixarRelatorioExamesRealizados" var="baixarRelatorio" />
								
								<a href="${baixarRelatorio}" class="btn btn-success">
									BAIXAR RELATÓRIO
								</a>
							</td>
						</tr>
					</tfoot>				
				</table>
			</div>

			<div class="row">
			
			</div>
		</div>
		
		<div  class="modal fade" id="confirmarExclusao" 
			data-bs-backdrop="static" data-bs-keyboard="false"
			tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title"><s:text name="label.modal.titulo"/></h5>
		        
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <div class="modal-body">
		      	<span><s:text name="label.modal.corpo"/></span>
		      </div>
		      
		      <div class="modal-footer">
	        	<a class="btn btn-secondary" data-bs-dismiss="modal" aria-label="Close">
					<s:text name="label.nao"/>
				</a>
	        	
	        	<s:url action="excluirExamesRealizados" var="excluir">
	        		<s:param name="exameVo.rowid" value="rowid"></s:param>
				</s:url>
	        	
				<a href="${excluir}" id="excluir" class="btn btn-primary btnConfirmaExclusao" style="width: 75px;">
					<s:text name="label.sim"/>
				</a>						
		      </div>
		    </div>		    
		  </div>
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
		
		<script>
			const btnConfirmaExclusao = document.querySelector('.btnConfirmaExclusao');	
			const href = btnConfirmaExclusao.getAttribute('href');
		
			function pegarIdBotao(e){
				btnConfirmaExclusao.setAttribute('href', e.getAttribute('id'));
			}
		</script>
		
	</body>
</html>