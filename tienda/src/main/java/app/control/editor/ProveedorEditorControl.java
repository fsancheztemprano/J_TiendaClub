package app.control.editor;

import app.misc.StaticHelpers;
import app.misc.TabTraversalEventHandler;
import app.model.Proveedor;
import com.google.common.base.Strings;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ProveedorEditorControl extends GridControl<Proveedor> {

    @FXML
    public TextField fxNif;
    @FXML
    public TextField fxEmail;
    @FXML
    public TextArea fxDescripcion;
    @FXML
    private TextField fxId;
    @FXML
    private TextField fxNombre;
    @FXML
    private TextField fxTelefono;
    @FXML
    private TextArea fxDireccion;
    @FXML
    private CheckBox fxCheckActivo;


    @FXML
    public void initialize() {
        fxDescripcion.addEventFilter(KeyEvent.KEY_PRESSED, new TabTraversalEventHandler());
        fxDireccion.addEventFilter(KeyEvent.KEY_PRESSED, new TabTraversalEventHandler());

    }


    @Override
    public void updateEditee(Proveedor editee) {
        editee.setNif(StaticHelpers.textInputEmptyToNull(fxNif));
        editee.setNombre(StaticHelpers.textInputEmptyToNull(fxNombre));
        editee.setTelefono(StaticHelpers.textInputEmptyToNull(fxTelefono));
        editee.setEmail(StaticHelpers.textInputEmptyToNull(fxEmail));
        editee.setDireccion(StaticHelpers.textInputEmptyToNull(fxDireccion));
        editee.setDescripcion(StaticHelpers.textInputEmptyToNull(fxDescripcion));
        editee.setActive(fxCheckActivo.isSelected());
    }

    @Override
    public Proveedor buildNew() {
        Proveedor editee = new Proveedor(fxNif.getText().trim());
        updateEditee(editee);
        return editee;
    }

    @Override
    public void setFields(Proveedor editee) {
        if (editee.getId() > 0)
            fxId.setText((Integer.toString(editee.getId())));
        fxNif.setText(Strings.nullToEmpty(editee.getNif()));
        fxNombre.setText(Strings.nullToEmpty(editee.getNombre()));
        fxTelefono.setText(Strings.nullToEmpty(editee.getTelefono()));
        fxEmail.setText(Strings.nullToEmpty(editee.getEmail()));
        fxDireccion.setText(Strings.nullToEmpty(editee.getDireccion()));
        fxDescripcion.setText(Strings.nullToEmpty(editee.getDescripcion()));
        fxCheckActivo.setSelected(editee.isActive());
    }

    @Override
    public boolean validFields() {
        return fxNombre.getText().trim().length() > 0 && fxNif.getText().trim().length() > 0;
    }
}
