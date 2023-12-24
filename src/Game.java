public class Game {

    private String answer;

    public Game(String answer) {
        this.answer = answer;
    }

    public int guess(String guess) {
        int total = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (answer.contains(guess.substring(i,i+1))) {
                total++;
            }
        }
        return total;
    }
}
