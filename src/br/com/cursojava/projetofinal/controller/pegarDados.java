package br.com.cursojava.projetofinal.controller;

class PegarDados {
	   private static String dado;
	   // coloque aqui o m�todo set e get para o atributo (estes m�todos devem tamb�m ser est�ticos)

	public static String getDado() {
		return dado;
	}

	public static void setDado(String dado) {
		PegarDados.dado = dado;
	}
	   
	}
