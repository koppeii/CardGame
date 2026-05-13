public class Main {
    public static void main(String[] args) {
        System.out.println("The Card Game Elemental Expansion");
        System.out.println("---------------------------------");

        // Gamemode selection
        System.out.println("Choose Game Mode:");
        System.out.println("1. Auto Mode (AI vs AI vs AI)");
        System.out.println("2. Player vs AI (Player vs 2 AI)");

        int mode = Input.getMenuChoice("Enter choice (1-2): ", 1, 2);

        Game game = new Game();

        if (mode == 1) {
            // Auto mode
            game.registerPlayer(new Player("Aang (AI)"));
            game.registerPlayer(new Player("SpongeBob (AI)"));
            game.registerPlayer(new Player("Michelangelo (AI)"));
        } else {
            // Human vs AI mode
            String userName = Input.getUserString("Enter your name: ");

            game.registerPlayer(new Player(userName + " (Player)"));
            game.registerPlayer(new Player("SpongeBob (AI)"));
            game.registerPlayer(new Player("Michelangelo (AI)"));
        }


        game.run();
    }
}
