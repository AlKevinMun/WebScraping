import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private String playerName;
    private String lastActivity;
    private String officialRating;
    private String wld;
    private ArrayList<String> commanderWR;

    public Player(String playerName, String lastActivity, String officialRating, String wld, ArrayList<String> commanderWR) {
        this.playerName = playerName;
        this.lastActivity = lastActivity;
        this.officialRating = officialRating;
        this.wld = wld;
        this.commanderWR = commanderWR;
    }

    public String getPlayerName() {
        return playerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", lastActivity='" + lastActivity + '\'' +
                ", officialRating='" + officialRating + '\'' +
                ", wld='" + wld + '\'' +
                ", commanderWR=" + commanderWR +
                '}';
    }
}
