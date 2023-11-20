import java.util.ArrayList;

public class Game {
    private String gameName;
    private Map map;
    private ArrayList<Player> players;

    public Game(String gameName, Map map, ArrayList<Player> players) {
        this.gameName = gameName;
        this.map = map;
        this.players = players;
    }
}
