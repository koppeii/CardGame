import java.util.ArrayList;

public class Game {

    // settings
    private int startingHandSize;
    private int cardsPerDeck;

    // players
    private ArrayList<Player> players;

    public Game() {
        players = new ArrayList<Player>();

        setGameSettings();
    }

    public void registerPlayer(Player player) {
        players.add(player);
    }

    public void run() {

        // generate decks
        generatePlayerDecks();

        // starting hands
        dealStartingHands();

        int currentPlayerIndex = -1;

        // GAME LOOP
        while (!allDecksEmpty()) {

            currentPlayerIndex++;

            if (currentPlayerIndex >= players.size()) {
                currentPlayerIndex = 0;
            }

            Player currentPlayer =
                    players.get(currentPlayerIndex);

            System.out.println("\n======================");
            System.out.println(currentPlayer.getName() + "'s TURN");
            System.out.println("======================");

            currentPlayer.displayStatus();

            Input.waitForUserToPressEnter("\nPress ENTER to continue.");

            // frozen
            if (currentPlayer.hasStatus("Frozen")) {
                System.out.println(currentPlayer.getName() + " is frozen!");
                continue;
            }

            // play card
            currentPlayer.playRandomCardFromHand(players);

            // draw card
            currentPlayer.drawCard();

            Input.waitForUserToPressEnter(
                    "\nPress ENTER to end turn."
            );

            for (Player player : players) {
                player.advanceTicks();
            }
        }

        // overtime
        overtime();
    }

    // ---------------------------------
    // SETTINGS
    // ---------------------------------

    private void setGameSettings() {

        startingHandSize = 5;

        cardsPerDeck = 25;
    }

    // ---------------------------------
    // CREATE PLAYER DECKS
    // ---------------------------------

    private void generatePlayerDecks() {



        for (int i = 0; i < players.size(); i++) {
            Player currentPlayer = players.get(i);

            for (int j = 0; j < cardsPerDeck; j++) {

                float randomValue = Rand.random();

                // Point Card
                if (randomValue < 0.4f) {
                    currentPlayer.addCardToDeck(
                            new PointCard()
                    );
                }

                // Attack Card
                else if (randomValue < 0.7f) {

                    currentPlayer.addCardToDeck(
                            new AttackCard()
                    );
                }


                // Freeze Card
                else if (randomValue < 0.9f) {


                    currentPlayer.addCardToDeck(
                            new FreezeCard()
                    );

                }

                // Thief Card
                else {

                    currentPlayer.addCardToDeck(
                            new ThiefCard()
                    );
                }
            }
        }
    }

    // ---------------------------------
    // DEAL STARTING HANDS
    // ---------------------------------

    private void dealStartingHands() {

        for (int i = 0; i < startingHandSize; i++) {

            for (int j = 0; j < players.size(); j++) {

                players.get(j).drawCard();
            }
        }
    }


    // ---------------------------------
    // CHECK IF ALL DECKS EMPTY
    // ---------------------------------

    private boolean allDecksEmpty() {

        for (Player player : players) {
            if (player.getDeckSize() > 0)
                return false;
        }

        return true;
    }

    // ---------------------------------
    // OVERTIME WINNER
    // ---------------------------------

    private void overtime() {

        System.out.println("\n======================");
        System.out.println("OVERTIME!");
        System.out.println("======================");

        Player winner = null;

        int highestTotal = -1;

        for (int i = 0; i < players.size(); i++) {

            Player p = players.get(i);

            int total =
                    p.getHealth()
                            + p.getShield();

            System.out.println(
                    p.getName()
                            + " | Health: "
                            + p.getHealth()
                            + " | Shield: "
                            + p.getShield()
                            + " | Total: "
                            + total
            );

            if (total > highestTotal) {

                highestTotal = total;

                winner = p;
            }
        }

        System.out.println("\n"
                + winner.getName()
                + " wins the game!");
    }
}