package model;
// TODO: Auto-generated Javadoc

/**
 * The Class JavaBeans.
 */
//variaveis q serão encapsuladas -> de acordo com o bd
public class JavaBeans {
	
	/** The email. */
	private String idcon, nome, fone, email;

	
	/**
	 * Instantiates a new java beans.
	 */
	public JavaBeans() { //Construtor1
		super();
		
	}

/**
 * Instantiates a new java beans.
 *
 * @param idcon the idcon
 * @param nome the nome
 * @param fone the fone
 * @param email the email
 */
//Construtores gerados altomaticamente -> source
	public JavaBeans(String idcon, String nome, String fone, String email) { //Construtor2
		super();
		this.idcon = idcon;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}

	/**
	 * Gets the idcon.
	 *
	 * @return the idcon
	 */
	//Getters e setter
	public String getIdcon() {
		return idcon;
	}

	/**
	 * Sets the idcon.
	 *
	 * @param idcon the new idcon
	 */
	public void setIdcon(String idcon) {
		this.idcon = idcon;
	}

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the fone.
	 *
	 * @return the fone
	 */
	public String getFone() {
		return fone;
	}

	/**
	 * Sets the fone.
	 *
	 * @param fone the new fone
	 */
	public void setFone(String fone) {
		this.fone = fone;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}

