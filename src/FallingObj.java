public class FallingObj {
    private String dir;
    private int Cantidad;
    private int Velocidad;

    public FallingObj(String dir, int cantidad, int velocidad) {
        this.dir = dir;
        this.Cantidad = cantidad;
        this.Velocidad = velocidad;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        this.Cantidad = cantidad;
    }

    public int getVelocidad() {
        return Velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.Velocidad = velocidad;
    }
}
