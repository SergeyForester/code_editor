package configs.themes;

import com.kodedu.terminalfx.TerminalBuilder;
import com.kodedu.terminalfx.TerminalTab;
import com.kodedu.terminalfx.config.TerminalConfig;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DraculaTheme extends ThemeConfig {
	public DraculaTheme(List<Object> els) {
		theme = "Dracula";

		codeEditorTheme = "dracula";
		primaryColor = "#383838";
		primaryLightColor = "#525252";
		fontColor = "#ffffff";

		terminalBackgroundColor = Color.rgb(16, 16, 16);
		terminalForegroundColor = Color.rgb(240, 240, 240);
		terminalCursorColor = Color.rgb(255, 0, 0, 0.5);

		elements = els;
	}
}
