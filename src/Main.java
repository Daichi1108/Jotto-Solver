import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Guesser solver = new Guesser(new Dictionary().getDict());
        System.out.println(solver.guess());
    }
}