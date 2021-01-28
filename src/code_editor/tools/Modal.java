package tools;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Modal {

	public void show(String fxml) {
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);

		try {
			Parent settingsDialog = FXMLLoader.load(getClass().getResource(fxml));
			dialogStage.setScene(new Scene(settingsDialog));
			dialogStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
