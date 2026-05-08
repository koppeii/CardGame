import java.util.*;

public class Player {
    private final String name;
    private ArrayList<Card> hand;
    private int numPoints;
    private Map<String, List<Integer>> statusEffects =  new LinkedHashMap<>();

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<Card>();
        numPoints = 5;
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
    }

    public void addStatus(String statusName, int tickDuration, int value) {
        statusEffects.put(statusName, Arrays.asList(tickDuration + 1, value));
    }

    public boolean hasStatus(String statusName) {
        if (statusEffects.containsKey(statusName))
            return true;
        return false;
    }

    public void advanceTicks() {
        for (Map.Entry<String, List<Integer>> entry : statusEffects.entrySet()) { // new syntax
            int i = entry.getValue().getFirst();

            if (i <= 1)
                statusEffects.remove(entry.getKey());
            else
                updateTick(entry.getKey(), i - 1);
        }
    }

    private void updateTick(String statusName, int tickDuration) {
        statusEffects.put(statusName, Arrays.asList(tickDuration, statusEffects.get(statusName).get(1)));
    }

    private int getRemainingTicks(String statusName) {
        if (statusEffects.containsKey(statusName))
            return statusEffects.get(statusName).getFirst();
        else
            return 0;
    }

    public boolean hasCardsInHand() {
        return !hand.isEmpty();
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public Card removeRandomCard() {
        if (hand.isEmpty())
            return null; // returning null indicates there are no cards to remove

        int randomCardIndex = Rand.randomInt(0, hand.size());
        return hand.remove(randomCardIndex); // ArrayList.remove both removes AND returns a reference to the object
    }

    public String getName() {
        return name;
    }

    public void addPoints(int pointsToAdd) {
        numPoints += pointsToAdd;

        if (numPoints < 0)
            numPoints = 0;
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

        if (hasStatus("Frozen"))
            System.out.println(" | *FROZEN FOR " + getRemainingTicks("Frozen") + " TICK" + Helper.pluralSuffix(getRemainingTicks("Frozen")).toUpperCase());

        System.out.println(" | Cards in hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.print(" | " + (i+1) + ": ");
            System.out.println(hand.get(i));
        }

        System.out.println(" | ----- ----- ----- ");
    }
}