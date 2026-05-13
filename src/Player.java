import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util. LinkedHashMap;
import java.util.Arrays;

public class Player {

    private String name;

    // NEW: personal deck
    private ArrayList<Card> deck;

    // Stats
    private int health;
    private int shield;

    private Map<String, List<Integer>> statusEffects =  new LinkedHashMap<>();

    public Player(String name) {
        this.name = name;
        deck = new ArrayList<>();

        // Starting values
        health = 100;
        shield = 100;
    }

    // -----------------------------
    // CARD PLAYING
    // -----------------------------

    public void playRandomCardFromHand(ArrayList<Player> players) {
        if (deck.isEmpty()) {
            System.out.println(name + " has no cards!");
            return;
        }
        int randomCardIndex =
                Rand.randomInt(0, deck.size());
        Card randomCard =
                deck.remove(randomCardIndex);

        randomCard.play(this, players);

        // choose another player
        boolean selectedAnotherPlayer = false;

        Player otherPlayer = null;

        while (!selectedAnotherPlayer) {

            int randomPlayerIndex =
                    Rand.randomInt(0, players.size());

            otherPlayer = players.get(randomPlayerIndex);

            if (otherPlayer != this) {
                selectedAnotherPlayer = true;
            }
        }
    }

    // -----------------------------
    // HAND METHODS
    // -----------------------------

    public boolean hasCardsInHand() {

        return deck.size() > 0;
    }

    public void addCardToHand(Card card) {

        deck.add(card);
    }

    public Card removeRandomCard() {

        if (deck.size() == 0) {
            return null;
        }

        int randomCardIndex =
                Rand.randomInt(0, deck.size());

        return deck.remove(randomCardIndex);
    }

    // -----------------------------
    // DECK METHODS
    // -----------------------------

    public void addCardToDeck(Card card) {

        deck.add(card);
    }

    public void drawCard() {

        if (deck.size() == 0) {

            System.out.println(name + "'s deck is empty!");

            return;
        }

        int randomCardIndex =
                Rand.randomInt(0, deck.size());

        Card drawnCard =
                deck.remove(randomCardIndex);

        deck.add(drawnCard);

        System.out.println(name
                + " drew "
                + drawnCard);
    }

    public int getDeckSize() {
        return deck.size();
    }

    // -----------------------------
    // STATUS METHODS
    // -----------------------------

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

    private int getValueOfStatus(String statusName) {
        if (!statusEffects.containsKey(statusName))
            return 0;
        else
            return statusEffects.get(statusName).get(1);
    }
    
    // -----------------------------
    // GETTERS
    // -----------------------------

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getShield() {
        return shield;
    }

    // -----------------------------
    // HEALTH / SHIELD
    // -----------------------------

    public void addHealth(int amount) {
        health += amount;
    }

    public void addShield(int amount) {
        shield += amount;
    }

    public void takeDamage(int damage) {

        // shield absorbs damage first
        if (shield >= damage)
            shield -= damage;

        else {
            damage -= shield;
            shield = 0;

            health -= damage;

            if (health < 0)
                health = 0;
        }
    }

    // -----------------------------
    // DISPLAY
    // -----------------------------

    public void displayStatus() {

        System.out.println(" | ----- "
                + name
                + " ----- ");

        System.out.println(" | Health: "
                + health);

        System.out.println(" | Shield: "
                + shield);

        System.out.println(" | Deck Size: "
                + deck.size());

        if (hasStatus("Frozen")) {

            System.out.println(" | *FROZEN*");
        }

        System.out.println(" | Cards in hand:");

        for (int i = 0; i < deck.size(); i++) {

            System.out.print(" | "
                    + (i + 1)
                    + ": ");

            System.out.println(deck.get(i));
        }

        System.out.println(" | ----- ----- ----- ");
    }
}