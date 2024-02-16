import java.util.ArrayList;

/** CLASS
 * solver for jotto that gives the strongest guess
 */
public class Solver {

    /**
     * returns the best guess for the current board of jotto
     * @param board board of the current game
     * @param d dictionary of all valid words
     * @return best guess
     */

    //Average moves after playing 1000 random words
    //Random O(n): 7.176
    //check for even distribution(checked with lowest highest index in distribution chart) O(n^2): 6.739
    public String getBestWord(ArrayList<Turn> board, Dictionary d) {
        ArrayList<String> validWords = new ArrayList<String>();
        for (String word : d.getWords()) {
            if (JottoGame.isValidGuess(word, board)) {
                validWords.add(word);
            }
        }
        //return validWords[Math.random() * validWords.length];
        int lowest = d.getWords().size()+1;
        String lowestString = "";
        for (String word : validWords) {
            int[] distribution = new int[6];
            for (String guess : validWords) {
                distribution[JottoGame.lettersInCommon(word, guess)]++;
            }
            if (highestInArray(distribution) < lowest) {
                lowest = highestInArray(distribution);
                lowestString = word;
            }
        }
        return lowestString;
    }

    /**
     * finds the index with the highest element in the array
     * @param arr int array being checked
     * @return index of arr with highest integer
     */
    private int highestInArray(int[] arr) {
        int highest = 0;
        for (int num : arr) {
            if (num > highest) {
                highest = num;
            }
        }
        return highest;
    }

    /**
     * returns a string array with a new array that adds word to words
     * @param words the array being added to
     * @param word string being added to array
     * @return string array that is words with word added to end
     */
}
