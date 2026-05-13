import java.util.ArrayList;

public class FortuneCard extends Card implements ApplyStatus{


    @Override
    public void play(Player currentPlayer, ArrayList<Player> allPlayers) {

        System.out.println(currentPlayer.getName() + " played " + this);
        System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getHealth() + " points.");

        int randomEffect = Rand.randomInt(0, 101);

        if (randomEffect<10 && randomEffect>1) {
            
        }
    }

    @Override
    public void applyStatus(Player effector, Player affected, String statusName, int ticks, int value) {
        affected.addStatus(statusName, ticks, value);

        System.out.println(effector.getName() + statusName + affected.getName() + " for " + ticks + " tick" + Helper.pluralSuffix(ticks) + "!");
    }
















    FortuneCard() {super(20);}
}
