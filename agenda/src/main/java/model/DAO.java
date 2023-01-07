package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; //JDBC
import java.sql.ResultSet; //usado pra armazenar o retorno do BD temporariamente em um objeto
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	// Modulo de conexão
	/** The driver. */
	// Pâmetros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";// com private somente a classe DAO tem acesso a esses parametros
														// de conexao
	// isso so funciona se tiver importado o driver pra pasta lib

	/** The url. */
														private String url = "jdbc:mysql://127.0.0.1:3305/dbagenda?useTimezone=true&serverTimezone=UTC"; // Id do servidor,
																										// nome do
																										// usuario, fuso
																										// horario de
																										/** The user. */
																										// referencia
	private String user = "root"; // User q pode acessar o bd
	
	/** The password. */
	private String password = "Cjcv0204*"; // Senha criada pro mysql e workbench

	// ctrl shit o -> importa classes (atalho)
	// shift alt y -> quebra de linha

	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	// Método de conexão
	private Connection conectar() {
		Connection con = null; // usamos pra reestabelecer sessão com o BD

		// try catch - faz tratamento de exceções em java

		try {// tentativa de conectar com o BD
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);// obtendo os parametrso de conexão
			return con;
		} catch (Exception e) { // caso aconteça alguma excessão
			System.out.println(e); // pra saber qual a excessão
			return null;
		}
		
//		teste de conexão
//		public void testeConexao() { //precisa usar tru cath sempre q quiser conectar com o BD
//			try {
//				Connection con = conectar(); //o con executa o metodo 'conectar()' 
//				System.out.println(con);
//				con.close();
//			} catch (Exception e) {
//				System.out.println(2);
//			}
//			
//		}
	}

	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	// CRUD CREATE
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos (nome,fone,email) values(?,?,?)"; // ? -> parametros q serao substiyuidos
																				// pelo valores das variaveis

		// Sempre precisa do try cathc pra acessa o bd
		try {
			// abrir a conexão
			Connection con = conectar();
			// Preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// SUbstituir os parâmetros (?) pelo conteudo das variaveis JavaBeans
			pst.setString(1, contato.getNome());// 1 -> primeira interrogação
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());

			// Executar a query
			pst.executeUpdate();// comando q insere os dados no banco -> passo 9

			// Encerrar a conexão com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Listar contatos.
	 *
	 * @return the array list
	 */
	// CRUD READ
	public ArrayList<JavaBeans> listarContatos() {
		// Criando um objeto para acessar a classe Javabeans
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		try {
			// abrindo conexão com o bd
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();// Executa o comando q coloquei la em cima -> o resultado desse comando é
												// armazenado teporariamente nesse objeto (rs)
			// o laço abaixo será executado enquanto houver contatos
			while (rs.next()) {
				// variaveis de apoio que recebem os dados do banco
				String idcon = rs.getString(1); // o objeto recebe o primeiro campo do BD
				String nome = rs.getString(2);
				String fone = rs.getString(3);
				String email = rs.getString(4);
				// populando o ArrayList
				contatos.add(new JavaBeans(idcon, nome, fone, email)); // esse objeto adiciona nas variaveis da classe
																		// java beans o conteudo dessas variaveis de
																		// apoio aí em cima q vem do B
			}
			con.close();
			return contatos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
	
	//CRUD UPDATE
	/**
	 * Selecionar contato.
	 *
	 * @param contato the contato
	 */
	//selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contatos where idcon = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getIdcon());//subistituindo o ? pelo idcon -> passo 5 e 4 (respectivamente)
			ResultSet rs = pst.executeQuery();//passo 6
			while(rs.next()){//enquanto tiver dados desse contato
				//setar as variaveis javabeans
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));//eses nmr correpondem aos campos da tabela
				contato.setEmail(rs.getString(4));
			}
			con.close();
				
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Alterar contato.
	 *
	 * @param contato the contato
	 */
	//editar o contato
	public void alterarContato(JavaBeans contato) {
		String update = "update contatos set nome=?,fone=?,email=? where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());//os nmr são as interrogações la em cima.Ex: o fone é a 2 interrogação
			pst.setString(3, contato.getEmail());//passo 16 e 17
			pst.setString(4, contato.getIdcon());
			pst.executeUpdate();//passo 18
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Deletar contato.
	 *
	 * @param contato the contato
	 */
	//CRUD DELETE
	public void deletarContato(JavaBeans contato) {
		String delete = "delete from contatos where idcon=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getIdcon()); //substituindo a ? pelo id do contato -> passos 5 e 6
			pst.executeUpdate();//passo 7
			con.close();//fechando conexão
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
