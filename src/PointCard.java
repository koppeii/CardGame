import java.util.ArrayList;

public class PointCard extends Card {

    public PointCard() {
        // Point card settings
        int minPoints = 6;
        int maxPoints = 10;

        int pointValue = Rand.randomInt(minPoints, maxPoints + 1);

        super(pointValue);
    }

    @Override
    public void play(Player currentPlayer, ArrayList<Player> allPlayers) {
        currentPlayer.addHealth(super.getPointValue());

        System.out.println(currentPlayer.getName() + " played " + this);
        System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getHealth() + " health.");
    }

    @Override
    public String toString() {
        return "Point Card { point value: " + super.getPointValue() + "}";
    }
}
