package app.control.editor;

import app.misc.Flogger;
import app.misc.FxDialogs;
import casteldao.datasource.DataSourceIdImpl;
import casteldao.model.EntityInt;
import com.google.common.flogger.StackSize;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditorControl<T extends EntityInt> extends BorderPane {

    @FXML
    public BorderPane fxGenericEditorBorderPane;
    @FXML
    public MenuButton fxButtonMenu;
    protected T editee;
    protected boolean creating = true;
    protected GridControl<T> gridControl;
    protected DataSourceIdImpl<T> dataOrigin;

    {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/editor/GenericEditorPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (final IOException e) {
            Flogger.atSevere().withCause(e).log();
        }
    }

    public EditorControl(T editee, DataSourceIdImpl<T> dataOrigin, GridControl<T> gridControl, Pane gridpane) {
        this.dataOrigin  = dataOrigin;
        this.gridControl = gridControl;
        gridControl.setFxButtonMenu(fxButtonMenu);
        this.editee = editee;
        if (editee != null) {
            creating    = false;
            gridControl.setFields(this.editee);
        }
        setCenter(gridpane);
    }

    @FXML
    protected void fxBtnDiscardAction(ActionEvent actionEvent) {
        ((Stage) this.getScene().getWindow()).close();
    }
    @FXML
    protected void fxBtnSaveAction(ActionEvent event) {
        if (gridControl.validFields()) {
            int rows = 0;
            if (creating) {
                editee = gridControl.buildNew();
                rows   = editee.insertIntoDB();
            } else {
                try {
                    editee.setBackup();
                    gridControl.updateEditee(editee);
                    rows = editee.updateOnDb();
                } catch (CloneNotSupportedException e) {
                    Flogger.atSevere().withStackTrace(StackSize.FULL).log("Failed creating backup");
                }
            }
            if (rows > 0) {
                FxDialogs.showInfo("Success",
                                   editee.getClass().getSimpleName() + " " + (creating ? "creado" : "modificado"));
                ((Node) event.getSource()).getScene().getWindow().hide();
            } else {
                FxDialogs.showError("Fail!",
                                    editee.getClass().getSimpleName() + " no " + (creating ? "creado" : "modificado"));
                gridControl.setFields(editee);
            }
        } else {
            Flogger.atWarning().log("Invalid Fields");
            FxDialogs.showError("Fail!", "Invalid Fields");
        }
    }
}