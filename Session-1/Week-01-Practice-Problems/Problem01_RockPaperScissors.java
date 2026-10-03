import java.util.Random;

public class Problem01_RockPaperScissors {

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"}; // Demo player moves
        int totalRounds = playerMoves.length;

        String[][] summaryTable = new String[totalRounds][4]; // Round, Player, Computer, Result
        Random random = new Random();

        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < totalRounds; i++) {
            String playerMove = playerMoves[i];
            String computerMove = moves[random.nextInt(3)];
            String result = playRound(playerMove, computerMove);

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }

            summaryTable[i][0] = String.valueOf(i + 1);
            summaryTable[i][1] = playerMove;
            summaryTable[i][2] = computerMove;
            summaryTable[i][3] = result;
        }

        // Display Summary Table
        System.out.println("=================================================");
        System.out.printf("%-8s | %-12s | %-13s | %-12s%n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("=================================================");
        for (int i = 0; i < totalRounds; i++) {
            System.out.printf("%-8s | %-12s | %-13s | %-12s%n", 
                    summaryTable[i][0], summaryTable[i][1], summaryTable[i][2], summaryTable[i][3]);
        }
        System.out.println("=================================================");

        // Display Final Statistics
        double winPercentage = ((double) wins / totalRounds) * 100;
        System.out.printf("Final Summary (after %d rounds)%n", totalRounds);
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", wins, losses, draws, winPercentage);
    }
}