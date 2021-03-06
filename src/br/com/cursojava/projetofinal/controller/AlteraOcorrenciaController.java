package br.com.cursojava.projetofinal.controller;

import java.util.List;

import br.com.cursojava.projetofinal.model.Ocorrencia;
import br.com.cursojava.projetofinal.model.Sistema;
import br.com.cursojava.projetofinal.repository.OcorrenciaRepository;
import br.com.cursojava.projetofinal.repository.RepositoryException;
import br.com.cursojava.projetofinal.repository.SistemaRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlteraOcorrenciaController {
	// constante de formata��o de mascara
	// a palavra final torna um constante, ou seja onde for usado sera sempre da
	// mesma forma
	// private static final DecimalFormat MASCARA_SALARIO = new
	// DecimalFormat("#,##0.00");

	@FXML
	private TextField txtOcorrencia; // txtocorrencia recebe descricao ocorrencia / sintoma

	@FXML
	private TextField txtChamado;
	@FXML
	private ComboBox<Sistema> cmbSistema;
	@FXML
	private TextField txtImpacto;
	@FXML
	private TextField txtAcao;
	@FXML
	private DatePicker datDataInicio;
	@FXML
	private TextField txtObservacao;
	@FXML
	private DatePicker datDataFim;
	@FXML
	private TextField txtCausa;

	@FXML
	private ComboBox<String> cmbStatus;

	@FXML
	private ComboBox<String> cmbHrInicio;

	@FXML
	private ComboBox<String> cmbHrFim;

	@FXML
	private ComboBox<String> cmbCausaExterna;

	private Alert alerta;

	private int FecharTela;

	@FXML
	void initialize() {
		try {
			// criar instancia do repository
			OcorrenciaRepository repo = new OcorrenciaRepository();
			List<Ocorrencia> listaItem = repo.findByint(Integer.parseInt(PegarDados.getDado()));
			for (Ocorrencia o : listaItem) {
				// preenche campos com dados da consulta, retorno do filtro ***
				txtOcorrencia.setText(o.getSintoma());
				txtChamado.setText(o.getChamado());

				cmbSistema.setPromptText(o.getSistema());
				String Teste = o.getSistema();
			
				txtImpacto.setText(o.getImpacto());
				txtAcao.setText(o.getAcao());
				txtCausa.setText(o.getCausa());
				txtObservacao.setText(o.getObservacao());

				// retorna status -ok
				if (o.getStatus() != null) {
					// cmbStatus.setPromptText(o.getStatus());
					cmbStatus.getSelectionModel().select(o.getStatus());
				}

				else {
					cmbStatus.getSelectionModel().select("PENDENTE");
				}

				// retorna causaExterna ok
				if (o.getCausaExterna_SN() != null) {
					cmbCausaExterna.setPromptText(o.getCausaExterna_SN());
				} else {
					cmbCausaExterna.getSelectionModel().select("N");
				}

				// ----------------------------------------------- //

				datDataInicio.setPromptText((o.getDataInicioFormatada()));

				System.out.println("datDataInicio " + o.getDataInicioFormatada());
				System.out.println("datDataInicio " + o.getDataInicioFormatada());

				// ----------------------------------------------------//

				// --------------------------------------------//

				cmbHrInicio.setPromptText(o.getHrInicio());
				datDataFim.setPromptText(o.getDataFimFormatada());
				cmbHrFim.setPromptText(o.getHrFim());

			}

		} catch (RepositoryException e) {
			exibirErro("erro ao pesquisar Sistema");
			e.printStackTrace();
		}

		cmbStatus.getItems().removeAll(cmbStatus.getItems());
		cmbStatus.getItems().addAll("PENDENTE", "EM ANALISE", "NORMALIZADO");
		// cmbStatus.getSelectionModel().select("PENDENTE");

		cmbCausaExterna.getItems().removeAll(cmbCausaExterna.getItems());
		cmbCausaExterna.getItems().addAll("N", "S");
		// cmbCausaExterna.getSelectionModel().select("N");

		// cmbHrInicio -- horario/minuto //
		for (int i = 0; i < 24; i++) {
			// System.out.println("Indice " +String.format("%02d", i));

			// converte int para string - ok
			// cmbStatus.getItems().addAll(String.valueOf(i));

			for (int j = 0; j < 60; j++) {
				cmbHrInicio.getItems().addAll(String.format("%02d", i) + ":" + String.format("%02d", j) + ":00");
				// cmbHrInicio.getSelectionModel().select("00:00:00");
				// format com 2 zeros a esqueda - ok
				// System.out.println(String.format("%02d", i));
			}

		}

		// cmbHrInicio -- horario/minuto //
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < 60; j++) {
				cmbHrFim.getItems().addAll(String.format("%02d", i) + ":" + String.format("%02d", j) + ":00");
				// cmbHrFim.getSelectionModel().select("00:00:00");
				// format com 2 zeros a esqueda - ok
				// System.out.println(String.format("%02d", i));
			}

		}

		try {
			/* obtem do BD a lista de sistema */
			SistemaRepository repo = new SistemaRepository();
			List<Sistema> listaItem = repo.findAll();
			/* colocar lista no combo */
			cmbSistema.getItems().addAll(listaItem);

		} catch (Exception e) {
			exibirErro("Falha ao carregar lista de Sistemas");
			e.printStackTrace();
		}

		try {
			/* obtem do BD a lista de sistema */
			OcorrenciaRepository repo = new OcorrenciaRepository();
			List<Ocorrencia> listaItem = repo.findByint(Integer.parseInt(PegarDados.getDado()));
		} catch (Exception e) {
			exibirErro("Falha ao carregar lista de Sistemas");
			e.printStackTrace();
		}

	};

	@FXML
	void alterarOcorrenciaOnAction(ActionEvent event) {
		// alterarOcorrenciaOnAction

		Ocorrencia ocorrencia = new Ocorrencia();

		// captura dados informados para gravar no banco de dados..
		// valida total de caracteres digitados no OCORRENCIA <> 0
		// SINTOMA -- Descricao da ocorrencia que gerou indisponibilidade

		if (txtOcorrencia.getText().length() == 0) {
			exibirErro("Descricao de Ocorrencia � obrigatorio. ");
			txtOcorrencia.requestFocus();
			return;
		} else if (txtOcorrencia.getText().length() > 150) {
			exibirErro("Descricao de Ocorrencia dever ser menor que 50 caracteres. ");
			txtOcorrencia.requestFocus();
			return;
		} else {
			ocorrencia.setSintoma(txtOcorrencia.getText());
			System.out.println("Ocorrencia informada foi " + txtOcorrencia.getText());
		}

		// Chamado - numero chamado aberto para registro da ocorrencia no JIRA
		if (txtChamado.getText().length() == 0) {
			exibirErro("informar Chamado � obrigatorio. ");
			txtChamado.requestFocus();
			return;
		} else if (txtChamado.getText().length() > 150) {
			exibirErro("Chamado dever ser menor que 50 caracteres. ");
			txtChamado.requestFocus();
			return;
		} else {
			ocorrencia.setChamado(txtChamado.getText());
			System.out.println("Chamado informado foi " + txtChamado.getText());
		}

		// cmbSistema
		/* Captura Cargo Selecionado e valida se recebido */

		// Sistema sistemaSelecionado = cmbSistema.itemsProperty().id

		Sistema sistemaSelecionado = cmbSistema.getSelectionModel().getSelectedItem();

		if (sistemaSelecionado == null) {
			/* mostra item */
			exibirErro("Selecione um Sistema valido");
			cmbSistema.requestFocus();
			return;
		} else {
			ocorrencia.setCodigoSistema(sistemaSelecionado.getId());
			System.out.println("Sistema, codigo e nome selecionados foram " + sistemaSelecionado.getId() + " - "
					+ sistemaSelecionado.getNome());
		}

		// Impacto, cadastro do impacto provocado pela ocorrencia
		if (txtImpacto.getText().length() == 0) {
			exibirErro("informar Impacto � obrigatorio. ");
			txtImpacto.requestFocus();
			return;
		} else if (txtImpacto.getText().length() > 150) {
			exibirErro("Chamado dever ser menor que 50 caracteres. ");
			txtImpacto.requestFocus();
			return;
		} else {
			ocorrencia.setImpacto(txtImpacto.getText());
			System.out.println("Impacto informado foi " + txtImpacto.getText());
		}

		// txtAcao -- Cadastrar acao realizada para correcao de ocorrencia e minimizar
		// impactos
		if (txtAcao.getText().length() == 0) {
			exibirErro("informar Acao � obrigatorio. ");
			txtAcao.requestFocus();
			return;
		} else if (txtAcao.getText().length() > 150) {
			exibirErro("Acao dever ser menor que 50 caracteres. ");
			txtAcao.requestFocus();
			return;
		} else {
			ocorrencia.setAcao(txtAcao.getText());
			System.out.println("Acao informada foi " + txtAcao.getText());
		}

		// txtCausa -- Cadastrar Causa de ocorrencia
		if (txtCausa.getText().length() == 0) {
			exibirErro("informar Acao � obrigatorio. ");
			txtCausa.requestFocus();
			return;
		} else if (txtCausa.getText().length() > 150) {
			exibirErro("Causa dever ser menor que 50 caracteres. ");
			txtCausa.requestFocus();
			return;
		} else {
			ocorrencia.setCausa(txtCausa.getText());
			System.out.println("Acao informada foi " + txtCausa.getText());
		}

		// txtObservacao
		if (txtObservacao.getText().length() == 0) {
			exibirErro("informar Observacao � obrigatorio. ");
			txtObservacao.requestFocus();
			return;
		} else if (txtObservacao.getText().length() > 150) {
			exibirErro("Observacao dever ser menor que 50 caracteres. ");
			txtObservacao.requestFocus();
			return;
		} else {
			ocorrencia.setObservacao(txtObservacao.getText());
			System.out.println("Observacao informada foi " + txtObservacao.getText());
		}

		// cmbStatus
		/* Captura Cargo Selecionado e valida se recebido */
		if (cmbStatus.getSelectionModel().getSelectedItem() == null) {
			/* mostra item */
			exibirErro("Selecione um Status valido");
			cmbStatus.requestFocus();
			return;
		} else {
			ocorrencia.setStatus(cmbStatus.getValue());
			System.out.println("Status, selecionados foi " + cmbStatus.getValue());
		}

		// datDataInicio data e hora de inicio da ocorrencia
		// preenche a data
		if (datDataInicio.getValue() == null) {
			exibirErro("Selecione um DAta Inicial Valida !!");
			datDataInicio.requestFocus();
			return;
		} else {
			ocorrencia.setDataInicio(datDataInicio.getValue());
			System.out.println("Data inicio  informada foi " + datDataInicio.getValue());
		}

		// cmbHrInicio
		/* Captura Cargo Selecionado e valida se recebido */
		if (cmbHrInicio.getSelectionModel().getSelectedItem() == null) {
			/* mostra item */
			exibirErro("Selecione uma Hora inicial valida");
			cmbHrInicio.requestFocus();
			return;
		} else {
			ocorrencia.setHrInicio(cmbHrInicio.getValue());

			System.out.println("Horario Inicial ocorrencia, selecionado foi " + cmbHrInicio.getValue());
		}

		// datDataFim
		// preenche a data
		if (datDataFim.getValue() == null) {
			exibirErro("Selecione um Data Final de Ocorrencia Valida !!");
			datDataFim.requestFocus();
			return;
		} else {
			ocorrencia.setDataFim(datDataFim.getValue());
			System.out.println("Data FIM  informada foi " + datDataFim.getValue());

		}

		// cmbHrFim
		if (cmbHrFim.getSelectionModel().getSelectedItem() == null) {
			/* mostra item */
			exibirErro("Selecione um Horario Final  valido");
			cmbHrFim.requestFocus();
			return;
		} else {
			ocorrencia.setHrFim(cmbHrFim.getValue());
			System.out.println("Horario Final de  ocorrencia, selecionado foi " + cmbHrFim.getValue());
		}


		// cmbCausaExterna
		if (cmbCausaExterna.getSelectionModel().getSelectedItem() == null) {
			/* mostra item */
			exibirErro("Selecione Causa Externa (S/N)... valida");
			cmbCausaExterna.requestFocus();
			return;
		} else {
			ocorrencia.setCausaExterna_SN(cmbCausaExterna.getValue());
			System.out.println("Selecione Causa Externa, selecionado foi " + cmbCausaExterna.getValue());
		}

		// abaixo codigo para salvar dados apos validos....
		// descomentar apos ajuste de // captura de tela.
		try {
			// envia a ocorrencia para o repository, criando a instancia dela
			OcorrenciaRepository repo = new OcorrenciaRepository();
			repo.update(ocorrencia, Integer.parseInt(PegarDados.getDado()));

			limparOcorrenciaOnAction(event);
			exibirSucesso();

			// alterarOcorrenciaOnAction
			FecharTela = 0;

			if (FecharTela == 0) {
				// Parent root =
				// FXMLLoader.load(getClass().getResource("/view/AlteraOcorrencia.fxml"));

				Stage stage = new Stage();
				stage.close();
				// System.exit(FecharTela);

			}

		} catch (RepositoryException e) {
			exibirErro(e.getMessage());
			e.printStackTrace();
		}

	}

	@FXML
	void excluirOcorrenciaOnAction(ActionEvent event) {
		System.out.println("Teste botao, excluir - com sucesso !! ");
	}

	@FXML
	void limparOcorrenciaOnAction(ActionEvent event) {
		/* limpar campos da tela */
		txtOcorrencia.clear();
		txtChamado.clear();
		cmbSistema.getSelectionModel().select(null);
		txtImpacto.clear();
		txtAcao.clear();
		txtCausa.clear();
		datDataInicio.setValue(null);
		txtObservacao.clear();
		datDataFim.setValue(null);
		cmbStatus.getSelectionModel().select(null);
		cmbHrInicio.getSelectionModel().select(null);
		cmbHrFim.getSelectionModel().select(null);
		/* coloca foco caixa de txtOcorrencia */
		txtOcorrencia.requestFocus();

	}

	// metodos para exibir notificacoes\avisos

	private void exibirSucesso() {
		// retorno de log do console
		System.out.println(" Ocorrencia, Altera��o de cadastrado - Realizada com sucesso !! ");

		alerta = new Alert(AlertType.INFORMATION);
		alerta.setTitle("Aviso");
		alerta.setHeaderText("Cadastro de Ocorrencia");
		alerta.setContentText("Alteracao Cadastro realizado com sucesso");
		alerta.show();

	}

	private void exibirErro(String mensagem) {
		alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Aviso");
		alerta.setHeaderText("Cadastro de Ocorrencia");
		alerta.setContentText(mensagem);
		alerta.show();

		Stage stage = new Stage();
		stage.close();

	}

	public void start(Stage stage) {
		// TODO Auto-generated method stub

	}

	public void setStage(Stage stage) {
		// TODO Auto-generated method stub

	}

//	@FXML
//	private void fechar(){
//	    Stage stage = (Stage) btnFechar.getScene().getWindow();
//	    stage.close();
//	}

}
