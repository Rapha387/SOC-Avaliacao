

function verificarInputsFuncionario(){
	if(txtNome.value == ""){
		erroNome.textContent = "O nome nao pode estar vazio";
		return false;
	}
	return true;
}