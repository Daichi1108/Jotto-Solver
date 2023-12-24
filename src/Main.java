import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Dictionary dict = new Dictionary();
        int total = 0;
        for (int i = 0; i < 1000; i++) {
            Game game = new Game(dict.getDict().get((int)(Math.random() * dict.getDict().size())));
            Guesser solver = new Guesser(new Dictionary().getDict());
            int moves = 1;
            while (game.guess(solver.guess()) < 5) {
                solver.addRule(solver.guess(), game.guess(solver.guess()));
                moves++;
            }
            total += moves;
        }
        System.out.println(total / 1000.0);
    }
}