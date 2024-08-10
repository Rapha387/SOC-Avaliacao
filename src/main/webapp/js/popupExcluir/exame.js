const btnConfirmaExclusao = document.querySelector('.btnConfirmaExclusao');	
const href = btnConfirmaExclusao.getAttribute('href');

function pegarIdBotao(e){
	btnConfirmaExclusao.setAttribute('href', href + e.getAttribute('id'));
}