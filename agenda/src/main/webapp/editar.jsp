<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="imagens/phone.png">
<!-- Acrescenta um icone de favorito -->
<link rel="stylesheet" href="style.css">
<!-- Linkando pro style css -->
</head>
<body>
	<h1>Editar contato/h1>
	<form name="frmContato" action="update">
		<table>
			<!-- tabela com 4 linhas -->
			<tr>
				<td><input type="text" name="idcon" id="caixa3" readonly value="<%out.print(request.getAttribute("idcon"));%>"></td><!--  ready only, o usuario n pode editar esse campo-->
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1" value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<!-- Esse name são os parametros do form -->
			<tr>
				<td><input type="text" name="fone" class="Caixa2" value="<%out.print(request.getAttribute("fone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="Caixa1" value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1"
			onclick="validar()">
		<!-- assim q clicar no botao vai chamar o validar no javascript, pra ver se os campos tao preenchidos -->
	</form>
	<script src="scripts/validador.js"></script>
	<!-- Criando um link desse html com o java script -->
</body>
</html>