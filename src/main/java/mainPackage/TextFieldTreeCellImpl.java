package mainPackage;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseButton;

/**
 * Created by jacobmenke on 4/15/17.
 */
public class TextFieldTreeCellImpl extends TreeCell<String> {

    private TextField textField;

    public TextFieldTreeCellImpl(TreeView fileBrowserTreeTable, TableView mainTableView, ObservableList<FileInfo> files, MainController mainController) {

        this.setOnMouseClicked(e->{
            if (e.getButton() == MouseButton.SECONDARY){
                FilePathTreeItem fileInfo = (FilePathTreeItem)fileBrowserTreeTable.getSelectionModel().getSelectedItem();
                ContextMenu cm = Utilities.createContextMenu(new FileInfo(fileInfo.getPathString()), mainTableView, files, mainController);

                cm.show(this, e.getScreenX(), e.getScreenY());
            }
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item.toString());
            setGraphic(getTreeItem().getGraphic());
        }
    }
}