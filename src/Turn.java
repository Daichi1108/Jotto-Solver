/** CLASS
 * container for a guess and its corresponding response
 */
public class Turn {

    /** The string of the guess for that turn*/
    public final String guess;
    /** The response of letters in common between guess and the secret word in a game of Jotto*/
    public final int response;

    /** CONSTRUCTOR
     @param guess A valid Jotto guess
     @param response The response from the MasterMind */
    public Turn (String guess, int response) {
        this.guess = guess;
        this.response = response;
    }

    /**
     * returns a string that is formatted for displaying in Jotto
     * @return string with guess and response
     */
    public String toString() {
        return guess + " - " + response;
    }
}
