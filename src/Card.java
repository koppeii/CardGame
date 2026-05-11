import java.util.ArrayList;

public abstract class Card {


    public abstract void play(Player currentPlayer, ArrayList<Player> allPlayers);

    public int pointValue;

    public int getPointValue() {
        return pointValue;
    }

    public Card(int pointValue) { this.pointValue = pointValue; }

}
