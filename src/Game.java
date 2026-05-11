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
            System.out.println(currentPlayer.getName()
                    + "'s TURN");
            System.out.println("======================");

            currentPlayer.displayStatus();

            Input.waitForUserToPressEnter(
                    "\nPress ENTER to continue."
            );

            // frozen
            if (currentPlayer.isFrozen()) {

                System.out.println(currentPlayer.getName()
                        + " is frozen!");

                currentPlayer.unfreeze();

                continue;
            }

            // play card
            currentPlayer.playRandomCardFromHand(players);

            // draw card
            currentPlayer.drawCard();

            Input.waitForUserToPressEnter(
                    "\nPress ENTER to end turn."
            );
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

            Player p = players.get(i);
            System.out.println(p.getName() + ": " + p.getNumPoints());

            // update highest score tracker
            if (p.getNumPoints() >= highestScore) {
                highestScore = p.getNumPoints();
                playerWithHighestScore = p;
            }
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