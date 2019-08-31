package app.model;

import app.data.DataStore;
import app.data.casteldao.daomodel.Activable;
import app.data.casteldao.daomodel.IPersistible;
import app.data.casteldao.daomodel.IndexIdActiveDao;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.NonNull;

public class Sede extends Activable {

    public static final String TABLE_NAME = "sedes";
    private static final ArrayList<String> COLUMN_NAMES = new ArrayList<>(Arrays.asList("nombre", "telefono", "direccion", "activo"));

    protected String nombre;
    protected String telefono;
    protected String direccion;

    public Sede(int id, String nombre) {
        super(id);
        setNombre(nombre);
    }

    public Sede(String nombre) {
        this(0, nombre);
    }

    public Sede(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getString(2));
        setDireccion(rs.getString(3));
        setTelefono(rs.getString(4));
        setActivo(rs.getBoolean(5));
    }

    @Override
    public void buildStatement(@NonNull PreparedStatement pst) throws SQLException {
        pst.setString(1, getNombre());
        pst.setString(2, getTelefono());
        pst.setString(3, getDireccion());
        pst.setBoolean(4, isActivo());
    }

    @Override
    public <V extends IPersistible> boolean restoreFrom(@NonNull V objectV) {
        if (getId() == objectV.getId() && !this.equals(objectV)) {
            Sede newValues = (Sede) objectV;
            setNombre(newValues.getNombre());
            setTelefono(newValues.getTelefono());
            setDireccion(newValues.getDireccion());
            setActivo(newValues.isActivo());
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    @SuppressWarnings("unchecked")
    public IndexIdActiveDao<Sede> getDataStore() {
        return DataStore.getSedes();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<Caja> getCajas() {
        return DataStore.getCajas().getIndexSede().getCacheKeyValues(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Sede sede = (Sede) o;
        return getId() == sede.getId() && isActivo() == sede.isActivo() && Objects.equal(getNombre(), sede.getNombre())
               && Objects.equal(getTelefono(), sede.getTelefono())
               && Objects.equal(getDireccion(), sede.getDireccion());
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("id", id)
                          .add("nombre", nombre)
                          .add("telefono", telefono)
                          .add("direccion", direccion)
                          .add("activo", isActivo())
                          .toString();
    }

    @Override
    public String toStringFormatted() {
        return getId() + " " + getNombre();
    }
}