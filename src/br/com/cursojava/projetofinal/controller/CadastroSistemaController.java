package br.com.cursojava.projetofinal.controller;

import br.com.cursojava.projetofinal.model.Sistema;
import br.com.cursojava.projetofinal.repository.SistemaRepository;
import br.com.cursojava.projetofinal.repository.RepositoryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class CadastroSistemaController {
	// nome completo da classe que deve ser informado no scene.
	// br.com.cursojava.projetofinal.controller.CadastroCargoController
	// control shift + o para import de itens que sejam necessarios ao projeto.

	@FXML
	private TextField txtSistema;

	private Alert alerta;

	@FXML
	void salvarOnAction(ActionEvent event) {
		txtSistema.setText(txtSistema.getText().trim());
		// valida total de caracteres digitados <=20
		if (txtSistema.getText().length() > 20) {
			exibirErro("O Sistema deve possuir no maximo 100 caracteres. ");
			txtSistema.requestFocus();
			return;
		}

		// valida total de caracteres digitados <=20
		if (txtSistema.getText().length() == 0) {
			exibirErro("Digite um nome valido para o Sistema. ");
			txtSistema.requestFocus();
			return;
		}

		// valida total de caracteres digitados <> 0
		if (txtSistema.getText().length() == 0) {
			exibirErro("Digite um nome valido para o Sistema. ");
			txtSistema.requestFocus();
			return;
		}

		// captura cargo digitado para carregar uma instancia de cargo com o valor digitado
		Sistema sist = new Sistema();
		sist.setNome(txtSistema.getText());
       
		
		
		
		try {
			// grava dados na base/tabela
			SistemaRepository repo = new SistemaRepository();
			repo.save(sist);

			exibirSucesso();
			limparOnAction(event);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			exibirErro(e.getMessage());
			e.printStackTrace();
		}

	}

	@FXML
	void limparOnAction(ActionEvent event) {

		txtSistema.setText("");
		txtSistema.requestFocus();

	}

	// metodos para exibir notificacoes\avisos

	private void exibirSucesso() {
		// retorno de log do console
		System.out.println(" Sistema, cadastrado - com sucesso !! ");

		alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle("Aviso");
		alerta.setHeaderText("Cadastro de Sistema");
		alerta.setContentText("Cadastro realizado com sucesso");
		alerta.show();

	}

	private void exibirErro(String mensagem) {

		alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Aviso");
		alerta.setHeaderText("Cadastro de Sistema");
		alerta.setContentText(mensagem);
		alerta.show();

	}

}
