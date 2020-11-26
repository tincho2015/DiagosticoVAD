package android.martin.expertoeasyrules;

public class DatosListView {


    private int id;
    private String titulo;
    private int imagen;
    private boolean bloqueado;
    private boolean realizado;

    public DatosListView(int id, String titulo, int imagen,boolean bloqueado,boolean realizado) {
        this.id = id;
        this.titulo = titulo;
        this.imagen = imagen;
        this.bloqueado = bloqueado;
        this.realizado = realizado;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
