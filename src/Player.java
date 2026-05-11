import java.util.ArrayList;

public class Player {

    private String name;

    private ArrayList<Card> hand;

    // NEW: personal deck
    private ArrayList<Card> deck;

    // Stats
    private int health;
    private int shield;

    private boolean isFrozen;

    public Player(String name) {

        this.name = name;
        hand = new ArrayList<Card>();
        deck = new ArrayList<Card>();
        // Starting values
        health = 100;
        shield = 100;
        isFrozen = false;
    }

    // -----------------------------
    // CARD PLAYING
    // -----------------------------

    public void playRandomCardFromHand(ArrayList<Player> players) {
        if (hand.size() == 0) {
            System.out.println(name + " has no cards!");
            return;
        }
        int randomCardIndex =
                Rand.randomInt(0, hand.size());
        Card randomCard =
                hand.remove(randomCardIndex);

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

        // damage effect
        if (randomCard instanceof DealsDamage) {
            DealsDamage damageCard =
                    (DealsDamage) randomCard;
            damageCard.doDamage(this, otherPlayer);
        }

        // freeze effect
        if (randomCard instanceof AppliesFreeze) {
            AppliesFreeze freezeCard =
                    (AppliesFreeze) randomCard;
            freezeCard.freeze(this, otherPlayer);
        }
    }

    // -----------------------------
    // HAND METHODS
    // -----------------------------

    public boolean hasCardsInHand() {

        return hand.size() > 0;
    }

    public void addCardToHand(Card card) {

        hand.add(card);
    }

    public Card removeRandomCard() {

        if (hand.size() == 0) {
            return null;
        }

        int randomCardIndex =
                Rand.randomInt(0, hand.size());

        return hand.remove(randomCardIndex);
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

        hand.add(drawnCard);

        System.out.println(name
                + " drew "
                + drawnCard);
    }

    public int getDeckSize() {

        return deck.size();
    }

    // -----------------------------
    // FREEZE METHODS
    // -----------------------------

    public boolean isFrozen() {

        return isFrozen;
    }

    public void freeze() {

        isFrozen = true;
    }

    public void unfreeze() {

        isFrozen = false;
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

    public void heal(int amount) {

        health += amount;
    }

    public void addShield(int amount) {

        shield += amount;
    }

    public void takeDamage(int damage) {

        // shield absorbs damage first
        if (shield >= damage) {

            shield -= damage;
        }

        else {

            damage -= shield;

            shield = 0;

            health -= damage;

            if (health < 0) {
                health = 0;
            }
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

        if (isFrozen) {

            System.out.println(" | *FROZEN*");
        }

        System.out.println(" | Cards in hand:");

        for (int i = 0; i < hand.size(); i++) {

            System.out.print(" | "
                    + (i + 1)
                    + ": ");

            System.out.println(hand.get(i));
        }

        System.out.println(" | ----- ----- ----- ");
    }
}