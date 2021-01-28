package controllers;

import com.kodedu.terminalfx.TerminalBuilder;
import com.kodedu.terminalfx.TerminalTab;
import components.FileTreeItem;
import configs.ThemeConfigAdapter;
import configs.themes.DraculaTheme;
import configs.themes.ThemeConfig;
import tools.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainController {
//	public WebView mainCodeWebView;

	public TreeView<String> projectTreeView;
	public MenuItem menuOpenFile;
	public StackPane rootNode;
	private static Logger log = Logger.getLogger(MainController.class.getName());
	public MenuItem menuSaveFile;

	public Tab initialTab;

	public Button openProjectOnInitialTab;
	public TabPane openedFilesTabPane;

	public MenuItem menuSettings;
	public VBox initialTabContainer;
	public static ThemeConfig themeConfig;
	public static ThemeConfigAdapter themeConfigAdapter;
	public StackPane terminalWindow;
	public MenuItem menuOpenDirectory;
	public MenuItem menuNewFile;
	public TabPane openedTerminalsPane;

	@FXML
	private void initialize() throws Exception {
		// create adapter
		themeConfigAdapter = new ThemeConfigAdapter();

		// init theme
		List<Object> elements = new ArrayList<>();
		elements.add(initialTab);
		elements.add(openProjectOnInitialTab);

		themeConfig = new DraculaTheme(elements);
		themeConfig.setOpenedTerminalsPane(openedTerminalsPane);
		themeConfig.buildTerminal();
		themeConfig.init();

		// open empty file
		menuNewFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {

			}
		});

		// open project from menu
		menuOpenDirectory.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				projectOpen();
			}
		});

		// opening project
		openProjectOnInitialTab.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				projectOpen();
				openedFilesTabPane.getTabs().remove(initialTab);
			}
		});


		// open settings
		menuSettings.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				new Modal().show("../static/settings.fxml");
			}
		});

		// working with tab
		projectTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observable, Object oldValue,
								Object newValue) {

				FileTreeItem selectedItem = (FileTreeItem) newValue;

				File f = new File(selectedItem.getFullPath());
				if (f.exists() && f.isDirectory()) {
					return;
				}

				log.log(Level.INFO, "Selected File: " + selectedItem.getFullPath());

				// creating new tab
				Tab newTab = new Tab(selectedItem.getValue());
				newTab.setStyle("-fx-background-color:" + themeConfig.getPrimaryLightColor() + "; " +
						"-fx-text-base-color: " + themeConfig.getFontColor() + ";");

				WebView codeEditor = new WebView();
				codeEditor.getEngine().load(this.getClass().getResource("/static/main.html").toString());

				String content = FileTools.read(selectedItem.getFullPath());

				content = content
						.replace("'", "\\'")
						.replace(System.getProperty("line.separator"), "\\n")
						.replace("\n", "\\n")
						.replace("\r", "\\n");


				String finalContent = content;
				codeEditor.getEngine().getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
					if (newState == Worker.State.SUCCEEDED) {
						CodeEditorTools.setMode(codeEditor.getEngine(), selectedItem.getFullPath());

						codeEditor.getEngine().executeScript("editor.setValue('" + finalContent + "')");

						Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								try {
									String code = (String) codeEditor.getEngine().executeScript("editor.getSession().getValue()");
									FileTools.write(selectedItem.getFullPath(), code);
								} catch (IOException e) {
									e.printStackTrace();
								}
								log.log(Level.INFO, "Saved file: " + selectedItem.getFullPath());
							}
						}));

						fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
						fiveSecondsWonder.play();

						newTab.setContent(codeEditor);
						openedFilesTabPane.getTabs().add(newTab);

						try {
							themeConfig.addElement(newTab);
							themeConfig.addElement(codeEditor);
						} catch (Exception e) {
							e.printStackTrace();
						}

						// stop saving file on tab close
						newTab.setOnCloseRequest(e -> {
							fiveSecondsWonder.stop();
							log.log(Level.INFO, "File closed: " + selectedItem.getFullPath());
						});
					}

				});

			}
		});

	}
	public void projectOpen() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(rootNode.getScene().getWindow());

		projectTreeView.setRoot(new TreeItem<String>(selectedDirectory.getName()));

		FilesTreeView.createTree(selectedDirectory, projectTreeView.getRoot());
		projectTreeView.getRoot().setExpanded(true);
	}
}
