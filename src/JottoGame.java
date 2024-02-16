import java.util.ArrayList;

/** CLASS
 * runs the entire game of Jotto
 *
 * bells and whistles:
 * spellchecker displays words with the closest edit distance when a misspelled word is inputted
 * changed the board array to an arraylist
 * used colors when displaying text to the console
 *
 */
public class JottoGame {
    /** The mastermind that runs the game and sets secret word*/
    private MasterMind masterMind;
    /** inputs guesses into the game*/
    private Player player;
    /** list of turns that contains the guess and the response*/
    private ArrayList<Turn> board;
    /** dictionary of all 5 letter english words in the game*/
    private Dictionary dictionary;
    /**the time of when the game starts*/
    private long startTime;

    /**resets text color to default color*/
    public static final String ANSI_RESET = "\u001B[0m";
    /**contains colors of the rainbow*/
    public static final String[] ANSI_RAINBOW = {"\u001B[31m", "\u001B[33m", "\u001B[32m", "\u001B[34m", "\u001B[36m", "\u001B[35m"};

    /** CONSTRUCTOR
     * sets all the instance variables with new objects
     * sets startTime to 0
     */
    public JottoGame () {
        masterMind = new MasterMind();
        player = new Player();
        board = new ArrayList<Turn>();
        dictionary = new Dictionary();
        startTime = 0;
    }

    /**
     * starts the game, picks a secret word, and takes inputs until the player wins
     */
    public void playGame () {
        startTime = System.currentTimeMillis();
        masterMind.setSecretWord(dictionary);
        board = new ArrayList<Turn>();
        while (!isGameOver ()) {
            String guess = player.getValidGuess(board, dictionary);
            int response = masterMind.getResponse(guess);
            board.add(new Turn (guess, response));
            display();
        }
        String congratMsg = "Congratulations! You guessed correctly: " + board.get(board.size()-1).guess + "\nTime to answer: " + getTime();
        for (int i = 0; i < congratMsg.length(); i++) {
            System.out.print(ANSI_RAINBOW[i % 6] + congratMsg.charAt(i) + ANSI_RESET);
        }
    }

    /**
     * gets a string representation of the current minutes and seconds since start
     * @return minutes and seconds since start formatted as a string
     */
    private String getTime() {
        double timeElapsed =  (System.currentTimeMillis() - startTime) / 1000.0;
        int seconds = (int)(timeElapsed % 60);
        timeElapsed = (int)timeElapsed / 60;
        int minutes = (int)(timeElapsed % 60);
        return minutes + ":" + seconds;
    }

    /**
     * checks if the player has won or not
     * @return true if the word has been guessed, false if the word has not been guessed
     */
    private boolean isGameOver() {
        if (board.size() > 0)
            return masterMind.getResponse(board.get(board.size()-1).guess) == 5;
        return false;
    }

    /**
     * displays the current board to the console
     */
    private void display() {
        System.out.println();
        for (Turn turn : board) {
            if (turn.response == 5) {
                System.out.println("\u001B[32m" + turn + ANSI_RESET);
            }
            else {
                System.out.println(turn);
            }
        }
    }

    /**
     Returns the # of letters in common between a & b
     @param a The 1st String
     @param b The 2nd String
     @returns The number of letters in common between a & b
     */
    public static int lettersInCommon (String a, String b) {
        int out = 0;
        for (int i = 0; i < a.length(); i++) {
            if (b.contains(a.substring(i,i+1))) {
                out++;
            }
        }
        return out;
    }

    /**
     * returns if the guess being inputted is valid and matches the other words on the board
     * @param guess the string being guessed
     * @param board the list of turns already played
     * @return true if valid guess, false if invalid guess
     */
    public static boolean isValidGuess (String guess, ArrayList<Turn> board) {
        for (Turn turn : board) {
            if (lettersInCommon(turn.guess, guess) != turn.response) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks if a string has any duplicates
     * @param str string being checked for duplicates
     * @return true if no duplicates, false if duplicates
     */
    public static boolean noDuplicates(String str) {
        for (int i = 0; i < str.length(); i++) {
            if ((str.substring(0, i) + str.substring(i + 1)).contains(str.substring(i, i + 1))) {
                return false;
            }
        }
        return true;
    }
}