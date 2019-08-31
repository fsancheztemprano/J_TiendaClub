package app.model;

import app.data.DataStore;
import app.data.casteldao.daomodel.IPersistible;
import app.data.casteldao.daomodel.IndexIdDao;
import app.data.casteldao.daomodel.Persistible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import org.checkerframework.checker.nullness.qual.NonNull;

public class Acceso extends Persistible {

    public static final String TABLE_NAME = "accesos";
    private static final ArrayList<String> COLUMN_NAMES = new ArrayList<>(Collections.singletonList("nivel"));

    private String nivel;

    public Acceso(int id, String nivel) {
        super(id);
        this.nivel = nivel;
    }

    public Acceso(String nivel) {
        this(0, nivel);
    }

    public Acceso(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getString(2));
    }

    @Override
    @SuppressWarnings("unchecked")
    public IndexIdDao<Acceso> getDataStore() {
        return DataStore.getAccesos();
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public void buildStatement(@NonNull PreparedStatement pst) throws SQLException {
        pst.setString(1, getNivel());
    }

    @Override
    public <V extends IPersistible> boolean restoreFrom(@NonNull V objectV) {
        if (getId() == objectV.getId() && !this.equals(objectV)) {
            Acceso newValues = (Acceso) objectV;
            setNivel(newValues.getNivel());
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Acceso acceso = (Acceso) o;
        return id == acceso.id && Objects.equal(nivel, acceso.nivel);
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("id", id).add("nivel", nivel).toString();
    }

    @Override
    public String toStringFormatted() {
        return getId() + " " + getNivel();
    }
}