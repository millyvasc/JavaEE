/**
 * Validação de formulario
 
 */
 
 function validar(){//em java script pode usar aspas duplas ou simples e n é obrigado colocar ;

	//alert('teste')  // Testando pra ver se ta conectado
	//esse alert eu vou usar pra fazer confirmaçã, tipo" tem certeza q deseja exclui esse evento?"
	let nome = frmContato.nome.value  //uma variavel 'nome' vai receber do form (no campo nome) um valor
	let fone = frmContato.fone.value
	
	if(nome === ""){//comparador de igualdade
		alert('Preencha o campo Nome') //se n tiver preenchido vai mandar um alerta pedindo pra preencher
		frmContato.nome.focus() //pra retornar o cursor dnv pra esse campo(nome)
		return false//se o user n preencher essas info n vao ser enviadar pro controller
	}
	else if(fone === ""){//se fone n for preenchido
		alert('Preencha o campo Fone')
		frmContato.fone.focus()
		return false
	}
	else{
		document.forms["frmContato"].submit() //se o user preencher td issso vai submeter os dados pra camada controller
	}
		
}