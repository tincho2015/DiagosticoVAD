package android.martin.expertoeasyrules.hechos;

public class diagnosticoFinal {

    private boolean presentaVAD;
    private int valoracion;
    private String descripcion;
    private boolean revisado;

    public diagnosticoFinal() {
        this.presentaVAD = false;
        this.valoracion = 0;
        this.descripcion = "";
        this.revisado = false;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isPresentaVAD() {
        return presentaVAD;
    }

    public void setPresentaVAD(boolean presentaVAD) {
        this.presentaVAD = presentaVAD;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public boolean isRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }
}
