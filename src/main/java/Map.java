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

    @Override
    public String toString() {
        return "Map{" +
                "name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", maxPlayers='" + maxPlayers + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
