package br.com.cursojava.projetofinal.controller;

class PegarDados {
	   private static String dado;
	   // coloque aqui o método set e get para o atributo (estes métodos devem também ser estáticos)

	public static String getDado() {
		return dado;
	}

	public static void setDado(String dado) {
		PegarDados.dado = dado;
	}
	   
	}
