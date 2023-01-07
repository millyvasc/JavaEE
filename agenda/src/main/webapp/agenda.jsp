<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="model.JavaBeans" %>
    <%@ page import="java.util.ArrayList" %>
    <% 
    //ignorando o aviso
    @ SuppressWarnings("unchecked")
    	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)request.getAttribute("contatos"); //Essa linha recebe o objeto lista
    	//for(int i=0; i<lista.size();i++){
    		//out.println(lista.get(i).getIdcon()); //Usa out pq é pra ele exibir dentro do documento agenda.jsp e nao no console
    		//out.println(lista.get(i).getNome());
    		//out.println(lista.get(i).getFone());
    	//	out.println(lista.get(i).getEmail());
    	//}
    %>
<!DOCTYPE html>
<html lang = "pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/phone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="Botao1">Novo contato</a>
	<a href="report" class="Botao2">Relatório</a>
	<table id="tabela"> <!-- Tabela -->
		<thead> <!-- -Cabeçalho da tabela -->
			<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Fone</th>
			<th>Email</th>
			<th>Opções</th>
			</tr>
		</thead> 
		<tbody> <!-- Corpo da tabela -->
			<%for(int i =0; i<lista.size(); i++){ %> <!-- trabalahdno com html e scriplet -->
			<tr>
				<td><%=lista.get(i).getIdcon() %></td>
				<td><%=lista.get(i).getNome() %></td><!-- nao coloca ; -->
				<td><%=lista.get(i).getFone() %></td>
				<td><%=lista.get(i).getEmail() %></td>
				<td><a href="select?idcon=<%=lista.get(i).getIdcon() %>" class="Botao1">Editar</a>
				<a href="javascript: confirmar(<%=lista.get(i).getIdcon() %>)" class="Botao2">Excluir</a>
				</td> 
			</tr>
			<%} %>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>