package br.com.cursojava.projetofinal.controller;

import br.com.cursojava.projetofinal.repository.NumeroExtensoRepository;
import br.com.cursojava.projetofinal.repository.RepositoryException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class NumeroExtensoController {

    @FXML
    private TextArea bpaneValorExtenco;

    @FXML
    private TextField txtNumero;

   
    
    public TextField getIdNumero() {
		return txtNumero;
	}

	public void setIdNumero(TextField idNumero) {
		this.txtNumero = txtNumero;
	}


	private Alert alerta = new Alert(AlertType.ERROR);

	// metodos para exibir notificacoes\avisos
	private void exibirErro(String mensagem) {

		alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Aviso");
		alerta.setHeaderText("Erro na geração de numero por extenço");
		alerta.setContentText(mensagem);
		alerta.show();

	}
	
	
	
	 @FXML
	    void numeroOnKeyReleased(javafx.event.Event event) {
		 try {
			if (txtNumero.getText().length() == 0) {
					exibirErro("Digite um valor valido para gerar extenso. ");
					txtNumero.requestFocus();
					return;
				}
			 else {
				 
				 NumeroExtensoRepository numero = new NumeroExtensoRepository();
				String result = numero.numeroExtenso(Integer.parseInt(txtNumero.getText()));
				bpaneValorExtenco.setText(result);
					// System.out.println( txtNumero.getText());
					txtNumero.requestFocus();
			 }
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			
			System.out.println("erro na digitacao");
			
			e.printStackTrace();
		} catch (RepositoryException e) {
			System.out.println("erro na digitacao");
			e.printStackTrace();
		}
	    }

	
	
    
    
    
}
