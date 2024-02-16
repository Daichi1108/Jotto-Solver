import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/** CLASS
 * handles the dictionary of all valid 5 letter words
 */
public class Dictionary {

    /** list of all 5 letter english words*/
    private ArrayList<String> words;
    /**list of all 5 letter good secret words*/
    private ArrayList<String> goodSecretWords;

    /** CONSTRUCTOR
     * sets words with all 5 letter english words
     * sets goodSecretWords with all good secret words
     */
    public Dictionary () {
        try {
            words = new ArrayList<String>();
            Scanner fileScanner = new Scanner(new File("Dictionary.txt"));
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine();
                if (JottoGame.noDuplicates(word)) {
                    words.add(word);
                }
            }
            fileScanner.close();

            goodSecretWords = new ArrayList<String>();
            fileScanner = new Scanner(new File("GoodSecretWords.csv"));
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine();
                goodSecretWords.add(word);
            }
            fileScanner.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** ACCESSOR
     * accessor method to get words arraylist
     * @return words
     */
    public ArrayList<String> getWords() {
        return words;
    }

    /**
     * check if a word is a word in the dictionary
     * @param word the string being checked
     * @return true if word in words, false if word is not in words
     */
    public boolean isWord (String word) {
        for (String s : words) {
            if (s.equals(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns a random five letter good secret word
     * @return five letter english word
     */
    public String getRandomGoodWord () {
        return goodSecretWords.get((int)(Math.random() * goodSecretWords.size()));
    }
}
