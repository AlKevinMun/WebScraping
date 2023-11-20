import java.util.ArrayList;
import java.util.List;

public class Game {
    private String gameName;
    private Map map;
    private List<Player> players;

    public Game(String gameName, Map map, List<Player> players) {
        this.gameName = gameName;
        this.map = map;
        this.players = players;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameName='" + gameName + '\'' +
                ", map=" + map +
                ", players=" + players +
                '}';
    }
}
