package br.com.cursojava.projetofinal.controller;

import java.awt.event.MouseMotionListener;
import java.util.Date;
import java.util.List;

import br.com.cursojava.projetofinal.model.Ocorrencia;
import br.com.cursojava.projetofinal.repository.OcorrenciaRepository;
import br.com.cursojava.projetofinal.repository.RepositoryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PesquisaOcorrenciaController implements
//MouseListener, 
		MouseMotionListener {

	@FXML
	private TextField txtDataOcorrencia;

	@FXML
	private TableView<Ocorrencia> tabOcorrencia;

	@FXML
	private TableColumn<Ocorrencia, Boolean> selectCol;

	@FXML
	private TableColumn<Ocorrencia, Integer> colOcorrencia;

	@FXML
	private TableColumn<Ocorrencia, String> ColChamado;

	@FXML
	private TableColumn<Ocorrencia, String> ColSistema;

	@FXML
	private TableColumn<Ocorrencia, Date> colDataCadastro;

	@FXML
	private TableColumn<Ocorrencia, String> colSintoma;
	@FXML
	private TableColumn<Ocorrencia, String> colDataInicio;
	@FXML
	private TableColumn<Ocorrencia, String> colHrInicio;
	@FXML
	private TableColumn<Ocorrencia, String> colDataFim;
	@FXML
	private TableColumn<Ocorrencia, String> colHrFim;
	@FXML
	private TableColumn<Ocorrencia, String> colImpacto;
	@FXML
	private TableColumn<Ocorrencia, String> colCausa;
	@FXML
	private TableColumn<Ocorrencia, String> colAcao;
	@FXML
	private TableColumn<Ocorrencia, String> colObservacao;
	@FXML
	private TableColumn<Ocorrencia, String> colStatus;

	@FXML
	private Button btAlterarOcorrencia;

	private Alert alerta = new Alert(AlertType.ERROR);

	// metodos para exibir notificacoes\avisos
	private void exibirErro(String mensagem) {

		alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Aviso");
		alerta.setHeaderText("Pesquisa de Funcionario");
		alerta.setContentText(mensagem);
		alerta.show();

	}

	@FXML
	void initialize() {

		tabOcorrencia.addEventFilter(MouseEvent.MOUSE_CLICKED, new MyEventHandler());

//		  Callback<TableColumn, TableCell> stringCellFactory =
//	                new Callback<TableColumn, TableCell>() {
//	            @Override
//	            public TableCell call(TableColumn p) {
//	              //  MyStringTableCell cell = new MyStringTableCell();
//	                tabOcorrencia.addEventFilter(MouseEvent.MOUSE_CLICKED, null);
//	                return null;
//	                
//	                
//	            }
//	        };

		// mapeando as colunas da tabela a ser utiliza e carregendo celulas com retorno
		// da consulta SQL.
		// ITENS RECUPERADOS - OK

		selectCol.setCellValueFactory(new PropertyValueFactory<>("selected"));

		colOcorrencia.setCellValueFactory(new PropertyValueFactory<>("idOcorrencia"));

		ColChamado.setCellValueFactory(new PropertyValueFactory<>("Chamado"));
		colDataCadastro.setCellValueFactory(new PropertyValueFactory<>("DataCadastroFormatada"));
		ColSistema.setCellValueFactory(new PropertyValueFactory<>("Sistema"));
		colSintoma.setCellValueFactory(new PropertyValueFactory<>("sintoma"));

		colDataInicio.setCellValueFactory(new PropertyValueFactory<>("DataInicioFormatada"));
		colHrInicio.setCellValueFactory(new PropertyValueFactory<>("HrInicio"));

		colDataFim.setCellValueFactory(new PropertyValueFactory<>("DataFimFormatada"));
		colHrFim.setCellValueFactory(new PropertyValueFactory<>("HrFim"));

		colImpacto.setCellValueFactory(new PropertyValueFactory<>("impacto"));
		colCausa.setCellValueFactory(new PropertyValueFactory<>("causa"));
		colAcao.setCellValueFactory(new PropertyValueFactory<>("acao"));
		colObservacao.setCellValueFactory(new PropertyValueFactory<>("observacao"));
		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

	}

//	@FXML
//	void alterarDadosOnAction(ActionEvent event) {
//		System.out.println("Teste botao, alterar em grid com sucesso - com sucesso !! ");
//	}
//	

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
		if (txtDataOcorrencia.getText().length() == 0) {
			exibirErro("Digite um nome valido para pesquisa de Ocorrencia. ");
			txtDataOcorrencia.requestFocus();
			return;
		}

		try {

			// Limpa Pesquisa Anterior
			tabOcorrencia.getItems().clear();

			// criar instancia do repository
			OcorrenciaRepository repo = new OcorrenciaRepository();
			List<Ocorrencia> lista = repo.findByname(txtDataOcorrencia.getText());

			tabOcorrencia.getItems().addAll(lista);

			// System.out.println("carrega lista Default Banco..: " + lista);

			// ativa mouseclick
			/////////////////////////////////////////////////////
			// private TableView<Ocorrencia> tabOcorrencia;

			// coloca campos em mode de edicao

//			selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));
//
//			// if ( (selectCol.cellValueFactoryProperty().isBound()) == true) {
//
//			//ColChamado.setCellFactory(TextFieldTableCell.forTableColumn());
//			ColSistema.setCellFactory(TextFieldTableCell.forTableColumn());
//			colSintoma.setCellFactory(TextFieldTableCell.forTableColumn());
//
//			colDataInicio.setCellFactory(TextFieldTableCell.forTableColumn());
//
//			colHrInicio.setCellFactory(TextFieldTableCell.forTableColumn());
//
//			colDataFim.setCellFactory(TextFieldTableCell.forTableColumn());
//
//			colHrFim.setCellFactory(TextFieldTableCell.forTableColumn());
//			colImpacto.setCellFactory(TextFieldTableCell.forTableColumn());
//			colCausa.setCellFactory(TextFieldTableCell.forTableColumn());
//			colAcao.setCellFactory(TextFieldTableCell.forTableColumn());
//			colObservacao.setCellFactory(TextFieldTableCell.forTableColumn());
//			colStatus.setCellFactory(TextFieldTableCell.forTableColumn());
//
//			System.out.println("captura conteudo modo de edicao teste..: "); // + lista);

		} catch (RepositoryException e) {
			exibirErro("erro ao pesquisar Ocorrencia");
			e.printStackTrace();
		}

	}

	@FXML
	void alterarDadosOnAction(ActionEvent event) {

		// @FXML
		// void alterarDadosOnAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AlteraOcorrenciaController.fxml"));
		// Parent parent = loader.load();
		// pnlRaiz.setCenter(parent);
		// stage.setTitle("Projeto Final - teste ");
		// }

		// ColChamado.setCellFactory(TextFieldTableCell.forTableColumn());
		// tabOcorrencia.getItems().

		selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));

		// if ( (selectCol.cellValueFactoryProperty().isBound()) == true) {

		// ColChamado.setCellFactory(TextFieldTableCell.forTableColumn());
		ColSistema.setCellFactory(TextFieldTableCell.forTableColumn());
		colSintoma.setCellFactory(TextFieldTableCell.forTableColumn());

		colDataInicio.setCellFactory(TextFieldTableCell.forTableColumn());

		colHrInicio.setCellFactory(TextFieldTableCell.forTableColumn());

		colDataFim.setCellFactory(TextFieldTableCell.forTableColumn());

		colHrFim.setCellFactory(TextFieldTableCell.forTableColumn());
		colImpacto.setCellFactory(TextFieldTableCell.forTableColumn());
		colCausa.setCellFactory(TextFieldTableCell.forTableColumn());
		colAcao.setCellFactory(TextFieldTableCell.forTableColumn());
		colObservacao.setCellFactory(TextFieldTableCell.forTableColumn());
		colStatus.setCellFactory(TextFieldTableCell.forTableColumn());

	}

	@Override
	public void mouseDragged(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setStage(Stage stage) {
		// TODO Auto-generated method stub

	}

}
