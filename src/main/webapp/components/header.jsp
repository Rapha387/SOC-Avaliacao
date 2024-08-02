<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

 <header class="shadow-sm bg-light d-flex flex-wrap justify-content-center py-3 px-5 mb-4 border-bottom">
	<s:url action="todosExames" var="exames"></s:url>
	<s:url action="todosFuncionarios" var="funcionarios"></s:url>
	<s:url action="todosExamesRealizados" var="examesRealizados"></s:url>
	
    <a href="${exames}" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
      <span class="fs-4">Gerenciador</span>
    </a>	
		
	<ul class="nav nav-pills ">
		<li class="nav-item"><a href="${exames}" class="nav-link">Exames</a></li>
		<li class="nav-item"><a href="${funcionarios}" class="nav-link">Funcionários</a></li>
    	<li class="nav-item"><a href="${examesRealizados}" class="nav-link">Exames Realizados</a></li>
	</ul>
</header>