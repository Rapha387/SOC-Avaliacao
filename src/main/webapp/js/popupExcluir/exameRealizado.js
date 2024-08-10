const btnConfirmaExclusao = document.querySelector('.btnConfirmaExclusao');	
const href = btnConfirmaExclusao.getAttribute('href');

function pegarIdBotao(e){
	btnConfirmaExclusao.setAttribute('href', e.getAttribute('id'));
}