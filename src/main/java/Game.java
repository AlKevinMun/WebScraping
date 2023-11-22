import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Game {
    private String gameName;
    private Map map;
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
