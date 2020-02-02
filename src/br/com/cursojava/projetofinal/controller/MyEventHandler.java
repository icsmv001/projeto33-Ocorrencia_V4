package br.com.cursojava.projetofinal.controller;

/**
* Usando metodos parseXXX, sendo XXXX o tipo numérico para o qual sua String será convertida, 
* ou seja, Int (String->int), [b]Double/b, Float (String->float), [b]Long/b…
* Exemplos:
*
* String a = "1";
* String b = "20.9";
* String c = "1000000";
*
* int x = Integer.parseInt(a);
* double y = Double.parseDouble(b);
* float z = Float.parseFloat(b);
* long w = Long.parseLong(c);
*/
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

class MyEventHandler<AlteraOcorrenciaController> implements EventHandler<MouseEvent> {
// ************** CODIGO QUE CAPTURA O VALOR A DA LINHA SELECIONADO NO TABLEVIEW COM DOUBLE_CLICK DO MOUSE
	@Override

	public void handle(MouseEvent MouseEvent) {
		if (MouseEvent.getButton().equals(MouseButton.PRIMARY)) {

			if (MouseEvent.getClickCount() == 2) {
				System.out.println("Double clicked");
				TableView c = (TableView) MouseEvent.getSource();

				// recupera linha total selecionada no tableview
				// System.out.println("Teste1-Recupera codigo do id_ocorrencia.: " +
				// c.getSelectionModel().getSelectedItem()+"fim_teste");

				// recuperar indice da coluna, que foi selecionada na linha do tableview
				// System.out.println("Teste2-Recupera codigo do id_ocorrencia.: "+
				// c.getSelectionModel().getSelectedIndex());

				/// ***** INICIO DE CHAMADA PARA NOVA JANELA

				TablePosition pos = (TablePosition) c.getSelectionModel().getSelectedCells().get(0);
				int index = pos.getRow();

				String id_OcorrenciaPesquisa = c.getItems().get(index).toString();
				id_OcorrenciaPesquisa = id_OcorrenciaPesquisa.substring(0, id_OcorrenciaPesquisa.indexOf("-"));
				// recupera o conteudo\valor da coluna colOcorrencia, de indexa 0, da linha
				// selecionada - ics 18/01/2020

				System.out.println("Teste4-Recupera codigo do id_OcorrenciaPesquisa.: " + id_OcorrenciaPesquisa);

				PegarDados.setDado((id_OcorrenciaPesquisa).trim());

				// ***** INICIO DE CHAMADA PARA NOVA JANELA
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/view/AlteraOcorrencia.fxml"));
					Stage stage = new Stage();
					Scene scene = new Scene(root, 400, 240);
					stage.setScene(scene);

					stage.setTitle("Altera Cadastro Ocorrencia");
					stage.setWidth(600);
					stage.setHeight(460);
					stage.show();

				} catch (IOException e) {
					System.out.println("saido por erro ao carregar nova janela, por que ???");
					e.printStackTrace();
				}

			}

		}
	}

};
