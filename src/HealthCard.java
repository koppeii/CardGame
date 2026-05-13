import java.util.ArrayList;

public class HealthCard extends Card {

    public HealthCard() {

        super(Rand.randomInt(10, 40));
    }

    @Override
    public void play(Player currentPlayer,
                     ArrayList<Player> allPlayers) {

        currentPlayer.heal(super.getPointValue());

        System.out.println(
                currentPlayer.getName()
                        + " played "
                        + this
        );

        System.out.println(
                currentPlayer.getName()
                        + " now has "
                        + currentPlayer.getHealth()
                        + " health."
        );
    }

    @Override
    public String toString() {

        return "Health Card { heal: "
                + super.getPointValue()
                + " }";
    }
}
