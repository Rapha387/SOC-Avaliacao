
function verificarInputsExameRealizado(){
	limpar();

	let valido = true;
	
	if(txtIdExame.value == ""){
		erroIdExame.textContent = "O id do exame nao pode estar vazio";
		valido = false
	}
	
	if(txtIdFuncionario.value == ""){
		erroIdFuncionario.textContent = "O id do funcionario nao pode estar vazio";
		valido = false;
	}
	
	if(txtData.value == ""){
		erroData.textContent = "A data nao pode estar vazio";
		valido = false;
	}
	
	return valido;
}

function limpar(){
	erroIdFuncionario.textContent = "";
	erroIdExame.textContent = "";
	erroData.textContent = "";
}

