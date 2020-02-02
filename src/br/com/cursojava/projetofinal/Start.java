package br.com.cursojava.projetofinal;

import br.com.cursojava.projetofinal.controller.JanelaPrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Start extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JanelaPrincipal.fxml"));
		Parent parent = loader.load();
		JanelaPrincipalController controller = loader.getController();
		controller.setStage(stage);

		stage.setScene(new Scene(parent));
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/view/image/funcionario-icon.png")));
		stage.setTitle("Projeto-ICS");
		stage.setWidth(600);
		stage.setHeight(460);
		stage.show();
	}
}
