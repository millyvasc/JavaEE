package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;//important importar essa classe para q o servelet possa usar o metodo teste de conexão
import model.JavaBeans; //pacote.arquivo

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/controller", "/main", "/insert", "/select", "/update", "/delete", "/report" }) // com o
																												// /insert
																												// a
// camada
// controler consegue
// receber os
// dados do form
public class Controller extends HttpServlet { 
 /** The Constant serialVersionUID. */
 // classe principal do servlet
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contato. */
	JavaBeans contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // metodo principal do servlet

		String action = request.getServletPath(); // a variavel action vai armazenar o caminha da requisição
		//System.out.println(action);
		if (action.equals("/main")) {// se o conteudo da variavel action for igual a /main
			contatos(request, response);
		} else if (action.equals("/insert")) { // se o conteudo da variavel action agr for insert, vai redirecionar ao
												// // metodo responsavel por encaminha essa função a camada model
			adicionarContato(request, response);
		} else if (action.equals("/select")) { // se o conteudo da variavel action agr for insert, vai redirecionar ao
												// // metodo responsavel por encaminha essa função a camada model
			listarContato(request, response);
		} else if (action.equals("/update")) { // se o conteudo da variavel action agr for insert, vai redirecionar ao
			// // metodo responsavel por encaminha essa função a camada model
			editarContato(request, response);
		} else if (action.equals("/delete")) { // se o conteudo da variavel action agr for insert, vai redirecionar ao
			// // metodo responsavel por encaminha essa função a camada model
			removerContato(request, response);
		} else if (action.equals("/report")) { // se o conteudo da variavel action agr for insert, vai redirecionar ao
			// // metodo responsavel por encaminha essa função a camada model
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");// se n conhecer o action, redirecione para o index.html
		}
		// teste de conexão
//		dao.testeConexao();//ele vai imprimir a string de conexão se tudo estiver ok, e se der problema ele vai mostrar o erro
	}

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// msm coisa do metodo princiapl, so troca o nome
		// Criando um objeto q ira receber os dados javabeans
		ArrayList<JavaBeans> lista = dao.listarContatos();// criando objetoq executa o metodo listar contatos -> passo 2
		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response); // encaminha o objeto lista ao documento agenda .jsp -> passo 7

		// teste de recebimento da lista
//		for(int i=0; i<lista.size(); i++) {
//			System.out.println(lista.get(i).getIdcon());
//			System.out.println(lista.get(i).getNome());
//			System.out.println(lista.get(i).getFone());
//		 	System.out.println(lista.get(i).getEmail());
//		}
	}

	/**
	 * Adicionar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Novo contato
	protected void adicionarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {// msm coisa do metodo princiapl, so troca o nome

		// teste de recebimento dos dados do form
//		System.out.println(request.getParameter("nome"));
//		System.out.println(request.getParameter("fone")); //capturando os dados do form, cada parametro é o nome de uma caixa de texto
//		System.out.println(request.getParameter("email"));

		// setar as variaveis JavaBeans
		contato.setNome(request.getParameter("nome")); // o objeto contato consegue atraver do set nome armazenar o
														// valor q ele ta recebendo do form
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// invocar o metodo inserirContato passando o objeto contato
		dao.inserirContato(contato);

		// Redirecionar para o documento agenda.jsp
		response.sendRedirect("main");

	}

	/**
	 * Listar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Editar contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { // metodo principal do servlet


		// System.out.println(idcon);

		// Recebimento fo id do contato q será editado -> passo 1
		// Setar a variavel JavaBeans -> passo 2
		contato.setIdcon(request.getParameter("idcon"));

		// Executar o metodo selecionar contato(DAO)
		dao.selecionarContato(contato);// passo 3

		// teste de recebimento
		// System.out.println(contato.getIdcon());
		// System.out.println(contato.getNome());
		// System.out.println(contato.getFone());
		// System.out.println(contato.getEmail());

		// setar os atributos do formulario com o conteudo JavaNean
		request.setAttribute("idcon", contato.getIdcon()); // o idcon é do documento editar.jsp. Setando ele com o valor
															// do idcon da classe java beans
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		// Encaminhar ao documento editar.jsp -> passo 10
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// teste de recebimento
		// System.out.println(request.getParameter("idcon"));//vai receber do formulario
		// de contato indentificado por name = "idcon"
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("fone"));
		// System.out.println(request.getParameter("email"));

		// setar as variaveis javaBeans
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));// passo 13 e 14
		contato.setEmail(request.getParameter("email"));

		// executar o metodo alterar contato
		dao.alterarContato(contato);// passo 15

		// redirecionar para o documento agenda.jsp (atualizando as alterações
		response.sendRedirect("main");
	}

	/**
	 * Remover contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Remover um contato
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// System.out.println(idcon); Teste de recebimento

		// recebimento do id do contatoa ser excluido( validador.js)
		// setar a variavel idcon Javabeans -> passo 3
		contato.setIdcon(request.getParameter("idcon"));
		// executar o metodo deletarcontato(dao) passando o objeto contato como
		// parametro
		dao.deletarContato(contato); // passo4
		// redirecionar para o documento agenda.jsp (atualizando as alterações
		response.sendRedirect("main");// main- o servlet executa novamente seu metodo princiapl e tras do bd a lista
										// de tds os contatos ja atualizada
	}
	
	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Gerar relatorio em pdf
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			//tipo de conteudo
			response.setContentType("apllication/pdf");
			//nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" +  "contatos.pdf");//documento pdp de nome "contatos.pdf"
			//criar documento
			PdfWriter.getInstance(documento, response.getOutputStream());//cria um documento pdf
			//abrir o documento -> conteúdo
			documento.open();
			documento.add(new Paragraph("Lista de contatos"));//adicionando novo paragrafo
			documento.add(new Paragraph(" "));//quebra de linha
			//criar uma tabela
			PdfPTable tabela = new PdfPTable(3); //3-> 3 colunas
			//cabeçalho
			PdfPCell col1= new PdfPCell(new Paragraph("Nome"));//coluna 1
			PdfPCell col2= new PdfPCell(new Paragraph("Fone"));//coluna2 //tabela estatitca
			PdfPCell col3= new PdfPCell(new Paragraph("Email"));//coluna3
			tabela.addCell(col1);
			tabela.addCell(col2);//adicionando as colunas
			tabela.addCell(col3);
			//popular a tabela com os contato
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for(int i=0; i<lista.size();i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone()); //tabela dinamica
				tabela.addCell(lista.get(i).getEmail());
			}
			documento.add(tabela);//adicionando tabela
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();//finalizando o documento
		}
	}
}
