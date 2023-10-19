import java.util.PrimitiveIterator;

public class Hechizo {
    private int id;
    private String nombre;
    private int nivel;
    private int castingTime;
    private String duracion;
    private int rango;
    private String salvacion;
    private String tipoDaño;

    public Hechizo(String nombre, int nivel, int castingTime, String duracion, int rango, String salvacion, String tipoDaño) {
        this.id = id++;
        this.nombre = nombre;
        this.nivel = nivel;
        this.castingTime = castingTime;
        this.duracion = duracion;
        this.rango = rango;
        this.salvacion = salvacion;
        this.tipoDaño = tipoDaño;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(int castingTime) {
        this.castingTime = castingTime;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public String getSalvacion() {
        return salvacion;
    }

    public void setSalvacion(String salvacion) {
        this.salvacion = salvacion;
    }

    public String getTipoDaño() {
        return tipoDaño;
    }

    public void setTipoDaño(String tipoDaño) {
        this.tipoDaño = tipoDaño;
    }
}
