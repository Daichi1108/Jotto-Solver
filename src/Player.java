import java.util.ArrayList;
import java.util.Scanner;

/** CLASS
 * Player that inputs guesses
 */
public class Player {

    /**Jotto solver to return best guess for board*/
    private Solver solver;
    /**spell check for incorrect words*/
    private SpellChecker spellChecker;
    /**if true, use the AI guesser, if false, take input from the player*/
    private boolean useAI;

    /** CONSTRUCTOR
     * sets solver and spellChecker with instances of their class
     */
    public Player() {
        solver = new Solver();
        spellChecker = new SpellChecker();
        useAI = false;
    }

    /**
     * gets a guess from the player and checks if it is valid
     * @param board the current game board
     * @param d dictionary of all 5 letter possible words
     * @return string of the guess
     */
    public String getValidGuess (ArrayList<Turn> board, Dictionary d) {
        if (useAI) {
            return solver.getBestWord(board, d);
        }
        while (true) {
            Scanner playerInput = new Scanner(System.in);
            System.out.print("(Type \"ai\" to use AI guesser) Enter Guess: ");
            String input = playerInput.nextLine().toLowerCase();
            if (input.equals("ai")) {
                useAI = true;
                return solver.getBestWord(board, d);
            }
            else if (d.isWord(input)) {
                if (JottoGame.isValidGuess(input, board)) {
                    return input;
                }
                System.out.println("\u001B[31mNot a Valid Guess" + JottoGame.ANSI_RESET);
            }
            else {
                System.out.println("\u001B[31mIncorrect Spelling" + JottoGame.ANSI_RESET + "\nPerhaps you meant:");
                for (String match : spellChecker.bestMatches(input, d, 6)) {
                    System.out.println("\t" + match);
                }
            }
        }
    }
}
