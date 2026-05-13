import java.util.ArrayList;

public class ShieldCard extends Card {

    public ShieldCard() {

        super(Rand.randomInt(10, 40));
    }

    @Override
    public void play(Player currentPlayer,
                     ArrayList<Player> allPlayers) {

        currentPlayer.addShield(super.getPointValue());

        System.out.println(
                currentPlayer.getName()
                        + " played "
                        + this
        );

        System.out.println(
                currentPlayer.getName()
                        + " now has "
                        + currentPlayer.getShield()
                        + " shield."
        );
    }

    @Override
    public String toString() {

        return "Shield Card { shield: "
                + super.getPointValue()
                + " }";
    }
}