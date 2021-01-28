package components;

import javafx.scene.control.TreeItem;

public class FileTreeItem extends TreeItem<String> {
	private String fullPath;
	private String title;


	public FileTreeItem(String s, String fullPath, String title) {
		super(s);
		this.fullPath = fullPath;
		this.title = title;
	}

	public FileTreeItem() {
	}


	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
