import java.util.ArrayList;

public abstract class Card {

    public abstract void play(Player currentPlayer, ArrayList<Player> allPlayers);

    protected int getPointValue() {
    }
}
