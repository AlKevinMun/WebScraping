import java.util.List;

public class Clase {
    private String hitPoints;
    private List<String> competencias;
    private List<String> equipamiento;
    private int id = 0;

    public Clase(String hitPoints, List<String> competencias, List<String> equipamiento) {
        this.hitPoints = hitPoints;
        this.competencias = competencias;
        this.equipamiento = equipamiento;
        this.id = id++;
    }

    public String getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(String hitPoints) {
        this.hitPoints = hitPoints;
    }

    public List<String> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<String> competencias) {
        this.competencias = competencias;
    }

    public List<String> getEquipamiento() {
        return equipamiento;
    }

    public void setEquipamiento(List<String> equipamiento) {
        this.equipamiento = equipamiento;
    }

    public int getId() {
        return id;
    }
}
