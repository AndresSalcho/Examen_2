public class Estacion {
    private String dir;
    FallingObj obj;

    public Estacion(String dir, FallingObj obj) {
        this.dir = dir;
        this.obj = obj;
    }

    public String getDir() {
        return dir;
    }

    public FallingObj getObj() {
        return obj;
    }

}
