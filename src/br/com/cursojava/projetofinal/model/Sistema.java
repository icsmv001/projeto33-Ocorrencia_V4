package br.com.cursojava.projetofinal.model;

public class Sistema {
	 public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	private int id;
	 private String nome;
	
	 @Override
	 public String toString() {
	 return nome;
	 }
	 
}
