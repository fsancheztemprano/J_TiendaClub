package tiendaclub.control.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import tiendaclub.control.editor.EditorControl;
import tiendaclub.control.editor.ProveedorEditorControl;
import tiendaclub.data.DataStore;
import tiendaclub.data.framework.dao.core.IndexIdActiveDao;
import tiendaclub.model.models.Proveedor;

public class ProveedoresTableControl extends ActiveTableControl<Proveedor> {


    @Override
    public void initialize() {
        super.initialize();
        TableColumn<Proveedor, String> fxColumnNif = new TableColumn<>("NIF");
        fxTable.getColumns().add(fxColumnNif);
        fxColumnNif.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("nif"));

        TableColumn<Proveedor, String> fxColumnNombre = new TableColumn<>("Nombre");
        fxTable.getColumns().add(fxColumnNombre);
        fxColumnNombre.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("nombre"));

        TableColumn<Proveedor, String> fxColumnTelefono = new TableColumn<>("Telefono");
        fxTable.getColumns().add(fxColumnTelefono);
        fxColumnTelefono.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("telefono"));

        TableColumn<Proveedor, String> fxColumnEmail = new TableColumn<>("Email");
        fxTable.getColumns().add(fxColumnEmail);
        fxColumnEmail.setCellValueFactory(new PropertyValueFactory<Proveedor, String>("email"));

        fxTable.setItems(listedObjects);
        addContent();
    }


    @Override
    protected IndexIdActiveDao<Proveedor> getDataOrigin() {
        return DataStore.getProveedores();
    }

    @Override
    protected EditorControl<Proveedor> getEditorControl() {
        return ProveedorEditorControl.getPane();
    }
}