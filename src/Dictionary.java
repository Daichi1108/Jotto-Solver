import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Dictionary {

    //Dictionary of all words with duplicates removed
    private ArrayList<String> dictionary;

    /** CONSTRUCTOR
     * sets dictionary with all words
     */
    public Dictionary() throws FileNotFoundException {
        dictionary = new ArrayList<String>();
        File dictText = new File("Dictionary.txt");
        Scanner fileScanner = new Scanner(dictText);
        while (fileScanner.hasNextLine()) {
            String word = fileScanner.nextLine();
            if (noDuplicates(word)) {
                dictionary.add(word);
            }
        }
        fileScanner.close();
    }

    /** ACCESSOR
     * getter method for dictionary
     * @return ArrayList<String> dictionary of all words
     */

    public ArrayList<String> getDict() {
        return dictionary;
    }

    /** HELPER
     * checks if there are any duplicate letters
     * @param str word being checked
     * @return boolean true if no duplicates, false if duplicates
     */
    private boolean noDuplicates(String str) {
        for (int i = 0; i < str.length(); i++) {
            if ((str.substring(0, i) + str.substring(i + 1)).contains(str.substring(i, i + 1))) {
                return false;
            }
        }
        return true;
    }
}
