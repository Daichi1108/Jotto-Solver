import java.util.ArrayList;

/** CLASS
 * spell checker that returns a string that is most similar to a misspelled word
 */
public class SpellChecker {


    /**
     * returns the closest real words to word
     * @param word misspelled string
     * @param d dictionary of all legal words
     * @param count number of words expected to be returned
     * @return string array of closest words to word
     */
    public ArrayList<String> bestMatches(String word, Dictionary d, int count) {
        ArrayList<String> lowest = new ArrayList<String>();
        for (String check : d.getWords()) {
            lowest = addLowest(lowest, word, check, count);
        }
        lowest.subList(0, count);
        return lowest;
    }

    /**
     * sorts check into arr based on lowest edit distance
     * @param arr the current array of words with lowest edit distances
     * @param word misspelled word
     * @param check the word being sorted
     * @return a new array that sorts check into the array
     */
    private ArrayList<String> addLowest(ArrayList<String> arr, String word, String check, int length) {
        for (int i = 0; i < arr.size(); i++) {

            if (operationMatrix(word, check) < operationMatrix(word, arr.get(i))) {
                arr.add(i, check);
                arr.remove(arr.size()-1);
                return arr;
            }
        }
        if (arr.size() < length)
            arr.add(check);
        return arr;
    }

    /**
     * uses the Wagner-Fischer algorithm to find and return the Levenshtein distance between word and check
     * @param word word 1
     * @param check word 2
     * @return levenshtein distance between word and check
     */
    private int operationMatrix(String word, String check) {
        int maxLength = Math.max(word.length(), check.length());
        while (word.length() < maxLength) {
            word += " ";
        }
        while (check.length() < maxLength) {
            check += " ";
        }
        word =  " " + word;
        check = " " + check;

        int[][] matrix = new int[check.length()][word.length()];

        for (int i = 1; i < maxLength+1; i++) {
            matrix[0][i] = i;
            matrix[i][0] = i;
        }

        for (int i = 1; i < word.length(); i++) {
            for (int k = 1; k < check.length(); k++) {
                int lowestAdjacent = matrix[i][k-1];
                if (matrix[i-1][k-1] < lowestAdjacent)
                    lowestAdjacent = matrix[i-1][k-1];
                if (matrix[i-1][k] < lowestAdjacent)
                    lowestAdjacent = matrix[i-1][k];

                matrix[i][k] = lowestAdjacent;
                if (word.charAt(i) != check.charAt(k)) {
                    matrix[i][k] = lowestAdjacent + 1;
                }
            }
        }
        return matrix[matrix.length-1][matrix.length-1];
    }
}
