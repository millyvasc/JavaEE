/**
 * Condirmaçãp de exclusão de um contato -> parametro: idcon
 */
 
 function confirmar(idcon){
	let resposta = confirm("Confirma a exclusão desse contato? ") // criando uma variavel q vai mostrar uma imagem assim qclicar no tbotao exclui contato
	if(resposta === true){
		//alert(idcon)
		window.location.href= "delete?idcon=" + idcon//redirecionar para o servlet - passo 2
	}
}