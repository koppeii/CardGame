import java.util.ArrayList;

public class AttackUpCard extends Card implements ApplyStatus{
    private int tickDuration = 2;
    private int value = 5;


    AttackUpCard() {super(67);}

    @Override
    public void play(Player currentPlayer, ArrayList<Player> allPlayers) {
        System.out.println(currentPlayer.getName() + " played " + this);
        System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getHealth() + " points.");

        // choose a target player (and not the current player)
//        if (allPlayers.size() < 2) {
//            System.out.println("Error: No other players for the FreezeCard to freeze or damage.");
//            return;
//        }

        boolean selectedAnotherPlayer = false;
        Player otherPlayer = null;

        while (!selectedAnotherPlayer) {
            int randomPlayerIndex = Rand.randomInt(0, allPlayers.size());
            otherPlayer = allPlayers.get(randomPlayerIndex);
            if (otherPlayer != currentPlayer) {
                selectedAnotherPlayer = true;
            }
        }

        applyStatus(currentPlayer, otherPlayer, "Frozen", tickDuration, 0);
    };

    @Override
    public void applyStatus(Player effector, Player affected, String statusName, int ticks, int value) {

    }





}
