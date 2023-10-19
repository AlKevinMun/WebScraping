public class Raza {
    private int id = 0;
    private String estadisticasExtra;
    private int edad;
    private String tamaño;
    private String velocidad;
    private String lenguaje;

    public Raza(int id, String estadisticasExtra, String tamaño, String velocidad, String lenguaje) {
        this.id = id++;
        this.estadisticasExtra = estadisticasExtra;
        this.edad = edad;
        this.tamaño = tamaño;
        this.velocidad = velocidad;
        this.lenguaje = lenguaje;
    }

    public int getId() {
        return id;
    }

    public String getEstadisticasExtra() {
        return estadisticasExtra;
    }

    public void setEstadisticasExtra(String estadisticasExtra) {
        this.estadisticasExtra = estadisticasExtra;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }
}
