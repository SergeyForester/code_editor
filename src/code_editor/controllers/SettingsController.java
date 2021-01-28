package controllers;


import configs.themes.ThemeConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsController {
	public ComboBox<String> themeComboBox;

	private final Logger logger = Logger.getLogger(MainController.class.getName());
	public AnchorPane settingsContainer;

	@FXML
	public void initialize() {

	}

	public void selectThemeAction(ActionEvent actionEvent) throws Exception {
		logger.log(Level.INFO, "Changed theme: " + themeComboBox.getValue());

		TabPane terminalPane = MainController.themeConfig.getOpenedTerminalsPane();
		MainController.themeConfigAdapter.setTheme(themeComboBox.getValue(), MainController.themeConfig.getElements());
	}
}
