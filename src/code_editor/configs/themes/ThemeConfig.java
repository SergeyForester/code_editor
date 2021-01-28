package configs.themes;

import com.kodedu.terminalfx.TerminalBuilder;
import com.kodedu.terminalfx.TerminalTab;
import com.kodedu.terminalfx.config.TerminalConfig;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.List;

public class ThemeConfig {
	public String theme;

	public String primaryColor;
	public String primaryLightColor;
	public String fontColor;
	public String codeEditorTheme;

	public Color terminalBackgroundColor;
	public Color terminalForegroundColor;
	public Color terminalCursorColor;

	public TabPane openedTerminalsPane;

	public List<Object> elements = new ArrayList<>();

	public void init() throws Exception {
		for (Object element: elements) {
			colorElement(element);
		}
	}

	private void colorElement(Object element) throws Exception {
		System.out.println(element.getClass().getName());
		if (element instanceof VBox) {
			((VBox) element).setStyle("-fx-background-color:" + primaryColor + ";");
		} else if (element instanceof TreeView) {
			((TreeView) element).setStyle(
					".fileExplorerTree * { -fx-background-color: #0a0a0a ; -fx-text-fill: #ffffff; }"
			);
		} else if (element instanceof Tab) {
			((Tab) element).setStyle("-fx-background-color:" + primaryLightColor + "; " +
					"-fx-text-base-color: " + fontColor + ";");
		} else if (element instanceof Button) {
			((Button) element).setStyle("-fx-background-color:" + primaryLightColor + "; " +
					"-fx-text-base-color: " + fontColor + ";");
		} else if (element instanceof WebView) {
			WebEngine webEngine = ((WebView) element).getEngine();
			webEngine.executeScript("editor.setTheme('ace/theme/" + codeEditorTheme + "')");
		}
	}

	public void buildTerminal() {
		// terminal tab
		try {
			// build new terminal
			TerminalConfig terminalConfig = new TerminalConfig();
			terminalConfig.setBackgroundColor(terminalBackgroundColor);
			terminalConfig.setForegroundColor(terminalForegroundColor);
			terminalConfig.setCursorColor(terminalCursorColor);

			TerminalBuilder terminalBuilder = new TerminalBuilder(terminalConfig);
			TerminalTab terminal = terminalBuilder.newTerminal();

			openedTerminalsPane.getTabs().remove(terminal);

			openedTerminalsPane.getTabs().add(terminal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// getters and setters

	public TabPane getOpenedTerminalsPane() {
		return openedTerminalsPane;
	}

	public void setOpenedTerminalsPane(TabPane openedTerminalsPane) {
		this.openedTerminalsPane = openedTerminalsPane;
	}

	public List<Object> getElements() {
		return elements;
	}

	public void addElement(Object el) throws Exception {
		colorElement(el);
		elements.add(el);
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getPrimaryColor() {
		return primaryColor;
	}

	public void setPrimaryColor(String primaryColor) {
		this.primaryColor = primaryColor;
	}

	public String getPrimaryLightColor() {
		return primaryLightColor;
	}

	public void setPrimaryLightColor(String primaryLightColor) {
		this.primaryLightColor = primaryLightColor;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getCodeEditorTheme() {
		return codeEditorTheme;
	}

	public void setCodeEditorTheme(String codeEditorTheme) {
		this.codeEditorTheme = codeEditorTheme;
	}

	public Color getTerminalBackgroundColor() {
		return terminalBackgroundColor;
	}

	public void setTerminalBackgroundColor(Color terminalBackgroundColor) {
		this.terminalBackgroundColor = terminalBackgroundColor;
	}

	public Color getTerminalForegroundColor() {
		return terminalForegroundColor;
	}

	public void setTerminalForegroundColor(Color terminalForegroundColor) {
		this.terminalForegroundColor = terminalForegroundColor;
	}

	public Color getTerminalCursorColor() {
		return terminalCursorColor;
	}

	public void setTerminalCursorColor(Color terminalCursorColor) {
		this.terminalCursorColor = terminalCursorColor;
	}

	public void setElements(List<Object> elements) {
		this.elements = elements;
	}


}
