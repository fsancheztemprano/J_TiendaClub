package app.model.models;

import app.model.IPersistible;
import app.model.models.abstracts.AbstractTransferencia;
import app.model.utils.DateUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Transferencia extends AbstractTransferencia implements IPersistible {
    public static final String TABLE_NAME = "transferencias";
    private static final ArrayList<String> COL_NAMES = new ArrayList<>(Arrays.asList("idUsuario", "idSedeOrigen", "idSedeDestino", "idProducto", "Cantidad", "fechahora"));

    private Usuario usuario;
    private Sede sedeOrigen;
    private Sede sedeDestino;
    private Producto producto;

    public Transferencia(int id, int idUsuario, int idSedeOrigen, int idSedeDestino, int idProducto, int cantidad, LocalDateTime fechahora) {
        super(id, idUsuario, idSedeOrigen, idSedeDestino, idProducto, cantidad, fechahora);
        updateUsuario();
        updateSedeOrigen();
        updateSedeDestino();
        updateProducto();
    }

    public Transferencia(int idUsuario, int idSedeOrigen, int idSedeDestino, int idProducto, int cantidad) {
        super(idUsuario, idSedeOrigen, idSedeDestino, idProducto, cantidad);
        updateUsuario();
        updateSedeOrigen();
        updateSedeDestino();
        updateProducto();
    }

    public Transferencia(ResultSet rs) throws SQLException {
        this(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), DateUtils.toLocalDateTime(rs.getDate(7)));
    }

    @Override
    public void buildStatement(PreparedStatement preparedStatement) throws SQLException {

    }

    @Override
    public void updateObject(ResultSet rs) throws SQLException {

    }

    @Override
    public void setIdUsuario(int idUsuario) {
        super.setIdUsuario(idUsuario);
        updateUsuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void updateUsuario() {
        if (usuario != null)
            usuario.getTransferencias().remove(id);
        //TODO DAO
        //usuario = DAO usuario . get ( idUsuario );
        usuario.getTransferencias().put(id, this);
    }

    @Override
    public void setIdSedeOrigen(int idSedeOrigen) {
        super.setIdSedeOrigen(idSedeOrigen);
        updateSedeOrigen();
    }

    public Sede getSedeOrigen() {
        return sedeOrigen;
    }

    public void setSedeOrigen(Sede sedeOrigen) {
        this.sedeOrigen = sedeOrigen;
    }

    private void updateSedeOrigen() {
        if (sedeOrigen != null)
            sedeOrigen.getTransferIn().remove(id);
        //TODO DAO
        //sedeOrigen = DAO sede . get ( idSedeOrigen );
        sedeOrigen.getTransferIn().put(id, this);
    }


    @Override
    public void setIdSedeDestino(int idSedeDestino) {
        super.setIdSedeDestino(idSedeDestino);
        updateSedeDestino();
    }

    public Sede getSedeDestino() {
        return sedeDestino;
    }

    public void setSedeDestino(Sede sedeDestino) {
        this.sedeDestino = sedeDestino;
    }

    private void updateSedeDestino() {
        if (sedeDestino != null)
            sedeDestino.getTransferIn().remove(id);
        //TODO DAO
        //sedeDestinp = DAO sede . get ( idSedeDestino );
        sedeDestino.getTransferIn().put(id, this);
    }


    @Override
    public void setIdProducto(int idProducto) {
        super.setIdProducto(idProducto);
        updateProducto();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    private void updateProducto() {
        //if(producto!=null)
        //  producto.getComprados().remove(id); //No Use

        //TODO DAO
        //compra = DAO compra . get( idCompra );

        //producto.getComprados().put(id, this); //No Use
    }

    @Override
    public int updateOnDb() {
        return 0;
    }

    @Override
    public int refreshFromDb() {
        return 0;
    }

    @Override
    public String insertString() {
        return IPersistible.buildInsertString(TABLE_NAME, COL_NAMES);
    }

    @Override
    public String updateString() {
        return IPersistible.buildUpdateString(TABLE_NAME, ID_COL_NAME, COL_NAMES, getId());
    }
}