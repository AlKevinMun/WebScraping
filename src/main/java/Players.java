public class Players {
    private String playerName;
    private String lastActivity;
    private int officialRating;
    private String wld;
    private String[] commanderWR;

    public Players(String playerName, String lastActivity, int officialRating, String wld, String[] commanderWR) {
        this.playerName = playerName;
        this.lastActivity = lastActivity;
        this.officialRating = officialRating;
        this.wld = wld;
        this.commanderWR = commanderWR;
    }
}
