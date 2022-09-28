import java.util.Scanner;

public class Main {
    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel", "cat", "clam", "cobra",
            "cougar", "coyote", "crow", "deer", "dog", "donkey", "duck", "eagle", "ferret", "fox",
            "frog", "goat", "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
            "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", "python", "rabbit",
            "ram", "rat", "raven", "rhino", "salmon", "seal", "shark", "sheep", "skunk", "sloth", "snake",
            "spider", "stork", "swan", "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale",
            "wolf", "wombat", "zebra"};
    public static final String GALLOW_LOSE =    " +---+\n" +
                                                " |   |\n" +
                                                " O   |\n" +
                                                "/|\\  |\n" +   //if the user missed six guesses.
                                                "/ \\  |\n" +
                                                "     |\n" +
                                                " =========\n";
    public static String[] gallows = {
            "+---+\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +   //if the user didn't miss any guesses.
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +   //if the user missed one guess.
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +    //if the user missed two guesses.
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +   //if the user missed three guesses.
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +   //if the user missed four guesses.
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +  //if the user missed five guesses.
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            GALLOW_LOSE};

    public static void main(String[] args) {
        start();
        String word = words[getRandom()];
        getLetters(word);
        int error = 0;
        int goodGuess = 0;
        char letter;
        char[] guess = new char[word.length()];
        String badGuess = "";
        getPlaceholders(guess);
        Scanner scan = new Scanner(System.in);
        while (true) {
            if (checkLose(gallows, error)) {
                System.out.println("You lose");
                break;
            }
            printGallow(error);
            System.out.println("Please type a letter\n");
            System.out.print("Word: ");
            printPlaceholders(guess);
            System.out.print("\nMisses: " + badGuess);
            System.out.print("\nGuess: ");
            letter = scan.next().toLowerCase().charAt(0);
            for (int i = 0; i < guess.length; i++) {
                if (letter == word.charAt(i)) {
                    guess[i] = getLetters(word)[i];
                    goodGuess++;
                }
            }
            if (goodGuess == 0) {
                badGuess += letter;
                error++;
            }
            goodGuess = 0;
            if (checkWin(guess, word)) {
                System.out.println("You win");
                scan.close();
                break;
            }
            if (checkLose(gallows, error)) {
                printGallow(error);
                System.out.println("You lose");
                scan.close();
                break;
            }

        }
    }

    public static int getRandom() {
        return (int) (Math.random() * words.length);
    }

    public static char[] getLetters(String word) {
        char[] letters = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            letters[i] = word.charAt(i);
        }
        return letters;
    }

    public static void getPlaceholders(char[] guess) {
        for (int i = 0; i < guess.length; i++) {
            guess[i] = '_';
        }
    }

    public static void printPlaceholders(char[] guess) {
        for (char c : guess) {
            System.out.print(c);
        }
    }

    public static void printGallow(int i) {
        System.out.println(gallows[i]);
    }

    public static void start() {
        System.out.println("Welcome in the game HANGMAN!");
        System.out.println("You have to guess correct word.");
    }

    public static boolean checkWin(char[] guess, String word) {
        int win = 0;
        for (int i = 0; i < word.length(); i++) {
            if (guess[i] == word.charAt(i)) {
                win++;
            }
        }
        return win == word.length();
    }

    public static boolean checkLose(String[] gallows, int error) {
        return gallows[error].equals(GALLOW_LOSE);
    }
}
