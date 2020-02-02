package br.com.cursojava.projetofinal.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JanelaPrincipalController {

	@FXML
	private BorderPane pnlRaiz;

	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	void cadastroSistemaOnAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CadastroSistema.fxml"));
		Parent parent = loader.load();
		pnlRaiz.setCenter(parent);
		stage.setTitle("Projeto Final - Cadastro de Sistemas");
	}

	@FXML
	void cadastroOcorrenciaOnAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CadastroOcorrencia.fxml"));
		Parent parent = loader.load();
		pnlRaiz.setCenter(parent);
		stage.setTitle("Projeto Final - Cadastro de Ocorrencia");
	}

	@FXML
	void pesquisaSistemaOnAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PesquisaSistema.fxml"));
		Parent parent = loader.load();
		pnlRaiz.setCenter(parent);
		stage.setTitle("Projeto Final - Pesquisa de Sistema");
	}

	@FXML
	void pesquisaOcorrenciaOnAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PesquisaOcorrencia.fxml"));
		Parent parent = loader.load();
		pnlRaiz.setCenter(parent);
		stage.setTitle("Projeto Final - Pesquisa de Ocorrencia");
	}

	@FXML
	void extensoOnAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NumeroExtenso.fxml"));
		Parent parent = loader.load();
		pnlRaiz.setCenter(parent);
		stage.setTitle("Projeto Final - Número por Extenso");
	}

//	@FXML
//	void AlteraOcorrencia(ActionEvent event) throws IOException {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlteraOcorrencia.fxml"));
//		Parent parent = loader.load();
//		pnlRaiz.setCenter(parent);
//		stage.setTitle("Projeto Final - AlteraOcorrencia");
//	}

	
	
	
	
	@FXML
	void sairOnAction(ActionEvent event) {
		stage.close();
	}
}
