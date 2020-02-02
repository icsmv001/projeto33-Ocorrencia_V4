package br.com.cursojava.projetofinal.repository;

public class RepositoryException extends Exception {
	
	public RepositoryException(String mensagem) {
		super(mensagem);
				
	}


	public RepositoryException(String mensagem, Exception causa) {
		super(mensagem, causa);
				
	}
	
	
	
}
