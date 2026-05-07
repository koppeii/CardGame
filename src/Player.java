import java.util.*;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int numPoints;
    private boolean isFrozen;
    private Map<String, List<Integer>> statusEffects =  new LinkedHashMap<>();


    public Player(String name) {
        this.name = name;
        hand = new ArrayList<Card>();
        numPoints = 5;
        isFrozen = false;
    }

    public void playRandomCardFromHand(ArrayList<Player> players) {
        // select a random card from our hand to play
        int randomCardIndex = Rand.randomInt(0, hand.size());
        Card randomCard = hand.remove(randomCardIndex);
        randomCard.play(this, players);

        // pick a random player (but not oneself) to apply any additional actions to
        boolean selectedAnotherPlayer = false;
        Player otherPlayer = null;

        while (!selectedAnotherPlayer) {
            int randomPlayerIndex = Rand.randomInt(0, players.size());
            otherPlayer = players.get(randomPlayerIndex);
            if (otherPlayer != this) {
                selectedAnotherPlayer = true;
            }
        }

        // do possible additional damage action
        if (randomCard instanceof DealsDamage) {
            DealsDamage damageCard = (DealsDamage)randomCard;
            damageCard.doDamage(this, otherPlayer);
        }

//        // do possible additional freeze action
//        if (randomCard instanceof FreezeCard) {
//            FreezeCard freezeCard = (FreezeCard)randomCard;
//            freezeCard.applyStatus(this, otherPlayer);
//        }
    }

    public void addStatus(String statusName, int tickDuration, int value) {
        statusEffects.put(statusName, Arrays.asList(tickDuration, value));
    }

    public void advanceTick() {
        for (Map.Entry<String, List<Integer>> entry : statusEffects.entrySet()) { // new syntax
            int i = entry.getValue().get(0).intValue();

            if (i == 1)
                statusEffects.remove(entry.getKey());
            else
                addStatus(entry.getKey(), i - 1, entry.getValue().get(1).intValue());
        }
    }

    public boolean hasCardsInHand() {
        return hand.size() > 0;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public Card removeRandomCard() {
        if (hand.size() == 0) {
            return null; // returning null indicates there are no cards to remove
        }

        int randomCardIndex = Rand.randomInt(0, hand.size());
        return hand.remove(randomCardIndex); // ArrayList.remove both removes AND returns a reference to the object
    }

    public String getName() {
        return name;
    }

    public void addPoints(int pointsToAdd) {
        numPoints += pointsToAdd;
        if (numPoints < 0) {
            numPoints = 0;
        }
    }

    public void removePoints(int pointsToRemove) {
        addPoints(-pointsToRemove);
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void displayStatus() {
        System.out.println(" | ----- " + name + " ----- ");
        System.out.println(" | Points: " + numPoints);
        if (isFrozen) {
            System.out.println(" | *FROZEN*");
        }

        System.out.println(" | Cards in hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.print(" | " + (i+1) + ": ");
            System.out.println(hand.get(i));
        }
        System.out.println(" | ----- ----- ----- ");
    }
}
