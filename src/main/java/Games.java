import java.util.ArrayList;

public class Games {
    private String gameName;
    private Map map;
    private ArrayList<Players> players;

    public Games(String gameName, Map map, ArrayList<Players> players) {
        this.gameName = gameName;
        this.map = map;
        this.players = players;
    }
}
