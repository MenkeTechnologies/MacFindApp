package mainPackage;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

import java.io.File;
import java.nio.file.Paths;

import static mainPackage.CustomTableRow.changeToolTipTime;

/**
 * Created by jacobmenke on 4/15/17.
 */
public class CustomTreeCell extends TreeCell<FilePathTreeItem> {
    public CustomTreeCell(TreeView fileBrowserTreeTable, TableView mainTableView, ObservableList<FileInfo> files, MainController mainController) {

        this.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {

                FilePathTreeItem filePathTreeItem = (FilePathTreeItem) this.getTreeItem();

                ContextMenu cm = Utilities.createContextMenu(new FileInfo(filePathTreeItem.getPathString()), mainTableView, files, mainController, "treeView");

                cm.show(this, e.getScreenX(), e.getScreenY());
            }
        });
    }

    private Tooltip tooltip = new Tooltip();

    @Override
    protected void updateItem(FilePathTreeItem item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item.getFileName());

            ImageView imageView = (ImageView) getTreeItem().getGraphic();

            setGraphic(item.getGraphic());

            CommonUtilities.formatTooltip(tooltip);

            changeToolTipTime(tooltip, 1);

            FileInfo fileInfo = new FileInfo(item.getPathString());

            tooltip.setText(fileInfo.toString());

            if (!item.isDirectory()) {
                tooltip.setGraphic(new ImageView(fileInfo.getFileImage()));
            } else {
                if (item.specialDirs.contains(item.getPathString())) {
                    if (item.getPathString().equals(FilePathTreeItem.home)) {
                        tooltip.setGraphic(new ImageView(FilePathTreeItem.homeImage));
                    } else if (item.getPathString().equals(FilePathTreeItem.downloads)) {
                        tooltip.setGraphic(new ImageView(FilePathTreeItem.dlImage));
                    } else if (item.getPathString().equals(FilePathTreeItem.desktop)) {
                        tooltip.setGraphic(new ImageView(FilePathTreeItem.desktopImage));
                    }
                } else {
                    tooltip.setGraphic(new ImageView(FilePathTreeItem.folderCollapseImage));
                }
            }

            setTooltip(tooltip);
        }
    }
}
