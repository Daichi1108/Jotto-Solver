import java.util.ArrayList;

public class Guesser {

    private ArrayList<String> dictionary;

    public Guesser(ArrayList<String> dictionary) {
        this.dictionary = dictionary;
    }

    public void addRule(String word, int match) {
        int i = 0;
        while (i < dictionary.size()) {
            if (wordMatches(dictionary.get(i), word) > match) {
                dictionary.remove(i);
            }
            else {
                i++;
            }
        }
    }

    public String guess() {
        return dictionary.get(0);
    }

    private int wordMatches(String str1, String str2) {
        int total = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str2.contains(str1.substring(i,i+1))) {
                total++;
            }
        }
        return total;
    }
}
