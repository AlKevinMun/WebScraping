import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Objects;
/**
 * Clase que almacena los datos del jugador
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Player {
    /**
     * Contiene el nombre del jugador
     */
    private String playerName;
    /**
     * Contiene la última vez que entro el jugador
     */
    private String lastActivity;
    /**
     * Contiene el rank que tiene el jugador en la página.
     */
    private String officialRating;
    /**
     * Contiene el registro de victorias y derrotas de su perfil.
     */
    private String wld;
    /**
     * Este ArrayList almacena el nombre de cada comandante, junto a su winrate.
     */
    private ArrayList<String> commanderWR;

    public Player(String playerName, String lastActivity, String officialRating, String wld, ArrayList<String> commanderWR) {
        this.playerName = playerName;
        this.lastActivity = lastActivity;
        this.officialRating = officialRating;
        this.wld = wld;
        this.commanderWR = commanderWR;
    }

    public Player() {}

    public String getPlayerName() {
        return playerName;
    }

    public String getLastActivity() {
        return lastActivity;
    }

    public String getOfficialRating() {
        return officialRating;
    }

    public String getWld() {
        return wld;
    }

    public ArrayList<String> getCommanderWR() {
        return commanderWR;
    }

    /**
     * El siguiente método es para comparar si cada jugador en cuestión tiene el mismo nombre.
     * @param o es el objeto a comparar
     * @return Devuelve true o false dependiendo si el nombre del jugador es el mismo
     */
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
