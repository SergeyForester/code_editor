package tools;

import javafx.scene.web.WebEngine;
import org.apache.commons.io.FilenameUtils;

public class CodeEditorTools {

	public static void setMode(WebEngine webEngine, String filename) {
		String fileExtension = FilenameUtils.getExtension(filename);

		String mode = "";

		switch (fileExtension) {
			case "py":
				mode = "python";
				break;
			case "js":
				mode = "js";
				break;
			case "html":
				mode = "html";
				break;
			case "css":
				mode = "css";
				break;
			case "java":
				mode = "java";
				break;
			case "cpp":
				mode = "c_cpp";
				break;
			case "h":
				mode = "c_cpp";
				break;
			case "xml":
				mode = "xml";
				break;
		}

		webEngine.executeScript("editor.session.setMode('ace/mode/"+ mode +"');");
		System.out.println("File mode: " + mode);
	}

	public static void changeTheme(String theme) {

	}
}
