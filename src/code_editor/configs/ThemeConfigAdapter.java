package configs;

import configs.themes.DefaultTheme;
import configs.themes.DraculaTheme;
import configs.themes.ThemeConfig;
import controllers.MainController;

import java.util.List;

public class ThemeConfigAdapter {
	private ThemeConfig themeConfig;

	public void setTheme(String theme, List<Object> elements) throws Exception {
		if (theme.equals("Dracula")) {
			DraculaTheme draculaTheme = new DraculaTheme(elements);
			draculaTheme.setOpenedTerminalsPane(MainController.themeConfig.getOpenedTerminalsPane());
			draculaTheme.buildTerminal();
			draculaTheme.init();

			themeConfig = draculaTheme;
			MainController.themeConfig = draculaTheme;
		} else if (theme.equals("Default")) {
			DefaultTheme defaultTheme = new DefaultTheme(elements);
			defaultTheme.setOpenedTerminalsPane(MainController.themeConfig.getOpenedTerminalsPane());
			defaultTheme.buildTerminal();
			defaultTheme.init();

			themeConfig = defaultTheme;
			MainController.themeConfig = defaultTheme;
		}
	}

	public ThemeConfig getThemeConfig() {
		return themeConfig;
	}

	public void setThemeConfig(ThemeConfig themeConfig) {
		this.themeConfig = themeConfig;
	}
}
