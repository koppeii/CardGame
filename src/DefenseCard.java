import java.util.ArrayList;

public class DefenseCard extends Card implements ApplyStatus{
    private int tick = 2;
    private int value = 2;



    @Override
    public void play(Player currentPlayer, ArrayList<Player> allPlayers) {

        System.out.println(currentPlayer.getName() + " played " + this);
        System.out.println(currentPlayer.getName() + " now has " + currentPlayer.getHealth() + " points.");



        applyStatus(currentPlayer, currentPlayer, "Defense", allPlayers.size()*tick, value);

    }
    @Override
    public void applyStatus(Player effector, Player affected, String statusName, int ticks, int value) {
        affected.addStatus(statusName, ticks, value);

        System.out.println(effector.getName() + " froze " + affected.getName() + " for " + ticks + " tick" + Helper.pluralSuffix(ticks) + "!");
    }










    DefenseCard() {super(20);}
}
