package configs.themes;

import javafx.scene.paint.Color;

import java.util.List;

public class DefaultTheme extends ThemeConfig {
	public DefaultTheme(List<Object> els) {
		theme = "Default";

		codeEditorTheme = "textmate";
		primaryColor = "#383838";
		primaryLightColor = "#525252";
		fontColor = "#ffffff";

		terminalBackgroundColor = Color.rgb(255, 255, 255);
		terminalForegroundColor = Color.rgb(0, 0, 0);
		terminalCursorColor = Color.rgb(255, 0, 0, 0.5);

		elements = els;
	}
}
