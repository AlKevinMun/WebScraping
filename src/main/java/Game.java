import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
/**
 * La clase almacena los datos de cada partida
 */
public class Game {
    /**
     * Contiene el nombre de la partida
     */
    private String gameName;
    /**
     * Contiene el objeto mapa de esta partida
     */
    private Map map;
    /**
     * Contiene una lista con todos los jugadores que participan en esta partida
     */
    private List<Player> players;

    public Game(String gameName, Map map, List<Player> players) {
        this.gameName = gameName;
        this.map = map;
        this.players = players;
    }

    public Game() {}

    @Override
    public String toString() {
        return "Game{" +
                "gameName='" + gameName + '\'' +
                ", map=" + map +
                ", players=" + players +
                '}';
    }

    public String getGameName() {
        return gameName;
    }

    public Map getMap() {
        return map;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
