

function verificarInputsFuncionario(){
	limpar();
	
	if(txtNome.value == ""){
		erroNome.textContent = "O nome nao pode estar vazio";
		return false;
	}
	return true;
}

function limpar(){
	erroBanco.textContent = "";
	erroNome.textContent = "";
}

