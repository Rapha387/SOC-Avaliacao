<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
	<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
</head>
<body class="bg-secondary">
		<div class="container">
			<s:form action="/novoExamesRealizados.action" onsubmit="return verificarInputsExameRealizado()">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosExamesRealizados" var="todos"/>
								<a href="${todos}" class="btn btn-success" >Exames dos Funcion�rios</a>
							</div>
							
							<div class="col-sm">
								<h5 class="card-title">Novo Exame do Funcion�rio</h5>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<div class="row align-items-center">
							<label for="id" class="col-sm-2 col-form-label">
								C�digo Exame:
							</label>	

							<div class="col-sm-2">
								<s:textfield type="number" cssClass="form-control" id="txtIdExame" name="exameRealizadoVo.exameVo.rowid"/>							
							</div>	
							
							<span id="erroIdExame" class="col-sm-5 text-danger"></span>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="nome" class="col-sm-2 col-form-label">
								C�digo Funcion�rio:
							</label>	

							<div class="col-sm-2">
								<s:textfield type="number" cssClass="form-control" id="txtIdFuncionario" name="exameRealizadoVo.funcionarioVo.rowid"/>							
							</div>	
							
							<span id="erroIdFuncionario" class="col-sm-5 text-danger"></span>	
						</div>
						<div class="row align-items-center mt-3">
							<label for="nome" class="col-sm-2 col-form-label">
								Data do Exame:
							</label>	

							<div class="col-sm-2">
								<s:textfield type="date" cssClass="form-control" id="txtData" name="exameRealizadoVo.dataExame"/>							
							</div>	
							
							<span id="erroData" class="col-sm-5 text-danger"></span>	
						</div>
					</div>

					<div class="card-footer">
						<div class="form-row">
							<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
							<button type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">Limpar Formulario</button>
						</div>
					</div>
				</div>
			</s:form>			
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript" src="js/verificarInputs/exameRealizado.js"></script>
	</body>
</html>