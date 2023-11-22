import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Map {
    private String name;
    private String creator;
    private String maxPlayers;
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
