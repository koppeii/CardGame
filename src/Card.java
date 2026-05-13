import java.util.ArrayList;

public abstract class Card {

    private int pointValue;


    public Card(int pointValue) {
        this.pointValue = pointValue;
    }

    public abstract void play(Player currentPlayer, ArrayList<Player> allPlayers);

    // Getter method to provide  access to the pointValue
    public int getPointValue() {
        return pointValue;
    }

    @Override
    public abstract String toString();
}
