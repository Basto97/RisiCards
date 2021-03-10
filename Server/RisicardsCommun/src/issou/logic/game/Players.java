package issou.logic.game;


public class Players {

    private final Player[] players = new Player[2];
    private final Game game;

    public Players(Game game){
        this.game = game;
    }

    public Player getCurrent(){
        throw new IllegalArgumentException();
    }

    public Player get(int i){
        if(i != 1 && i != 0) throw new IllegalArgumentException();
        return players[i];
    }

    public void set(int i, Player p){
        if(i != 1 && i != 0) throw new IllegalArgumentException();
        players[i] = p;
    }
}
