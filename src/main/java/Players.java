public class Players {
    private String playerName;
    private String lastActivity;
    private String officialRating;
    private String wld;
    private String[] commanderWR;

    public Players(String playerName, String lastActivity, String officialRating, String wld, String[] commanderWR) {
        this.playerName = playerName;
        this.lastActivity = lastActivity;
        this.officialRating = officialRating;
        this.wld = wld;
        this.commanderWR = commanderWR;
    }
}
