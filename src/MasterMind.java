import java.util.Scanner;
/** CLASS
 * knows the secret word and is responsible for returning the response to guesses
 */
public class MasterMind {

    /** the secret word the player must guess*/
    private String secretWord;
    /** suggests words to user if an incorrectly spelled word is inputted*/
    private SpellChecker spellChecker;

    /** CONSTRUCTOR
     * sets secret word and spellChecker
     */
    public MasterMind () {
        secretWord = "jotto";
        spellChecker = new SpellChecker();
    }

    /**
     * sets the secret word with player input or with a random good word
     * @param d dictionary that has access to all possible secret words
     */
    public void setSecretWord (Dictionary d) {
        while (true) {
            Scanner masterInput = new Scanner(System.in);
            System.out.print("Type \"random\" for a random word\nEnter Secret Word: ");
            String input = masterInput.nextLine().toLowerCase();
            if (input.equals("random")) {
                secretWord = d.getRandomGoodWord();
                return;
            }
            if (d.isWord(input)) {
                secretWord = input;
                for (int i = 0; i < 40; i++) {
                    System.out.println("Hiding Secret Word");
                }
                return;
            }
            else {
                System.out.println("\u001B[31mIncorrect Spelling" + JottoGame.ANSI_RESET + "\nPerhaps you meant:");

                for (String match : spellChecker.bestMatches(input, d, 6)) {
                    System.out.println("\t" + match);
                }
            }
        }
    }

    /**
     * compares the guess against the secret word for letters in common
     * @param guess the string being guessed
     * @return number of letters in common between guess and secret word
     */
    public int getResponse (String guess) {
        return JottoGame.lettersInCommon(guess, secretWord);
    }
}
