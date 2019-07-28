package app.model.models.abstracts;

public abstract class AbstractAcceso {
    protected byte id;
    protected String nivel;

    public AbstractAcceso(byte id, String nivel) {
        this.id = id;
        this.nivel = nivel;
    }

    public AbstractAcceso(String nivel) {
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractAcceso that = (AbstractAcceso) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "AccesoAbstract{" +
                "id=" + id +
                ", nivel='" + nivel + '\'' +
                '}';
    }
}