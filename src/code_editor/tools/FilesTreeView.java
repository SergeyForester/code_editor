package tools;

import components.FileTreeItem;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;

import java.io.File;

public class FilesTreeView {

    public static void createTree(File root_file, TreeItem parent) {
        if (root_file.isDirectory()) {
            FileTreeItem node = new FileTreeItem(root_file.getName(), root_file.getAbsolutePath(), root_file.getName());
            parent.getChildren().add(node);
            for (File f: root_file.listFiles()) {

                FileTreeItem placeholder = new FileTreeItem(); // Add TreeItem to make parent expandable even it has no child yet.
                node.getChildren().add(placeholder);

                // When parent is expanded continue the recursive
                node.addEventHandler(TreeItem.branchExpandedEvent(), new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        createTree(f, node); // Continue the recursive as usual
                        node.getChildren().remove(placeholder); // Remove placeholder
                        node.removeEventHandler(TreeItem.branchExpandedEvent(), this); // Remove event
                    }
                });

            }
        } else {
            parent.getChildren().add(new FileTreeItem(root_file.getName(), root_file.getAbsolutePath(), root_file.getName()));
        }
    }
}
