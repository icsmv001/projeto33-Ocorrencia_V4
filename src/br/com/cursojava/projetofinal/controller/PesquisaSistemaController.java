package br.com.cursojava.projetofinal.controller;

import br.com.cursojava.projetofinal.model.Sistema;
import br.com.cursojava.projetofinal.repository.SistemaRepository;
import br.com.cursojava.projetofinal.repository.RepositoryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;

public class PesquisaSistemaController {

	@FXML
	private TextField txtNomeSistema;

	@FXML
	private TableView<Sistema> tabSistema;
	// TableView<?>, substituir o ? pelo objeto que tem os dados a serem
	// apresentados\trabalhados (ojbeto CARGO)
	// o objeto Cargo é uma classe que existe no pacote MODEL ( modelo de dados );
	// ou seja deve informar qual é a entidade\tabela que representa a informação
	// desejada

	@FXML
	private TableColumn<Sistema, Integer> colIdSistema;
	// TableColumn<tipo da informacao é um objeto CARGO, e o tipo de dado desta
	// coluna é INTEIRO)

	@FXML
	private TableColumn<Sistema, String> colNomeSistema;
	// TableColumn<tipo da informacao é um objeto CARGO, e o tipo de dado desta
	// coluna é String)

	private Alert alerta = new Alert(AlertType.ERROR);

	// metodos para exibir notificacoes\avisos
	private void exibirErro(String mensagem) {

		alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Aviso");
		alerta.setHeaderText("Pesquisa de Sistema");
		alerta.setContentText(mensagem);
		alerta.show();

	}

	@FXML
	void initialize() {
		// mapeando as colunas da tabela a ser utiliza
		colIdSistema.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNomeSistema.setCellValueFactory(new PropertyValueFactory<>("nome"));
	}

	@FXML
	void procurarOnAction(ActionEvent event) {
		/// exibirErro("teste - mensagem de erro");

		// teste de mesa, simulando dados Fake
		// Cargo cargoFake = new Cargo();
		// cargoFake.setId(111);
		// cargoFake.setNome("izael");
		// * adicionar o cargo na tabela */
		// tabCargo.getItems().add(cargoFake);

		// valida total de caracteres digitados <> 0
		if (txtNomeSistema.getText().length() == 0) {
			exibirErro("Digite um nome valido para pesquisa de Cargo. ");
			txtNomeSistema.requestFocus();
			return;
		}

		try {

			// Limpa Pesquisa Anterior
			tabSistema.getItems().clear();

			// criar instancia do repository
			SistemaRepository repo = new SistemaRepository();
			List<Sistema> lista = repo.findByname(txtNomeSistema.getText());

			tabSistema.getItems().addAll(lista);
			System.out.println(lista);
			
			
		} catch (RepositoryException e) {
			exibirErro("erro ao pesquisar Sistema");
			e.printStackTrace();
		}

		
	}

}
