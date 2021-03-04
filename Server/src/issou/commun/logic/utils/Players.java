package issou.commun.logic.utils;

public class Players {

    private final Player[] players;

    public Players(Player player1, Player player2){
        this.players = new Player[2];
        this.players[0] = player1;
        this.players[1] = player2;
    }

    public Player playerOne(){
        return players[0];
    }
    public Player playerTwo(){
        return players[1];
    }
}
