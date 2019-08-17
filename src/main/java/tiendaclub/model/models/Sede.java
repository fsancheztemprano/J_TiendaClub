package tiendaclub.model.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import tiendaclub.model.models.abstracts.AbstractSede;

public class Sede extends AbstractSede {

    public static final String TABLE_NAME = "sedes";
    private static final ArrayList<String> COL_NAMES = new ArrayList<>(
            Arrays.asList("nombre", "telefono", "direccion", "activo"));

    private HashMap<Integer, Caja> cajas = new HashMap<>();
    private HashMap<Integer, Compra> compras = new HashMap<>();
    private HashMap<Integer, Transferencia> transferIn = new HashMap<>();
    private HashMap<Integer, Transferencia> transferOut = new HashMap<>();

    public Sede(int id, String nombre, String telefono, String direccion) {
        super(id, nombre, telefono, direccion);
    }

    public Sede(String nombre, String telefono, String direccion) {
        super(nombre, telefono, direccion);
    }

    public Sede(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        setActivo(rs.getBoolean(5));
    }

    @Override
    public void buildStatement(PreparedStatement pst) throws SQLException {
        pst.setString(1, getNombre());
        pst.setString(2, getTelefono());
        pst.setString(3, getDireccion());
        pst.setBoolean(4, isActivo());
    }

    @Override
    public void updateObject(ResultSet rs) throws SQLException {
        //setId(rs.getInt(1));
        setNombre(rs.getString(2));
        setTelefono(rs.getString(3));
        setDireccion(rs.getString(4));
        setActivo(rs.getBoolean(5));
    }

    public HashMap<Integer, Caja> getCajas() {
        return cajas;
    }

    public HashMap<Integer, Compra> getCompras() {
        return compras;
    }

    public HashMap<Integer, Transferencia> getTransferIn() {
        return transferIn;
    }

    public HashMap<Integer, Transferencia> getTransferOut() {
        return transferOut;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public ArrayList<String> getColumnNames() {
        return COL_NAMES;
    }

    @Override
    public String toString() {
        return getId() + " " + getNombre();
    }
}
