import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
/**
 * Esta clase almacena los datos del mapa
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Map {
    /**
     * Contiene el nombre del mapa
     */
    private String name;
    /**
     * Contiene el creador del mapa
     */
    private String creator;
    /**
     * Contiene los jugadores máximos que permite el mapa
     */
    private String maxPlayers;
    /**
     * Contiene el tamaño en cuadrículas del mapa
     */
    private String size;

    public Map(String name, String creator, String maxPlayers, String size) {
        this.name = name;
        this.creator = creator;
        this.maxPlayers = maxPlayers;
        this.size = size;
    }

    public Map() {}


    @Override
    public String toString() {
        return "Map{" +
                "name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", maxPlayers='" + maxPlayers + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
    }

    public String getMaxPlayers() {
        return maxPlayers;
    }

    public String getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return Objects.equals(name, map.name);
    }

}
