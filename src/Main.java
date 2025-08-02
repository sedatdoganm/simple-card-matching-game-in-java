import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Card[][] cards = new Card[4][4];
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        randomCardsCreate();

        System.out.println("GAME STARTED!");
        while (!isGameOver()) {
            printBoard();
            makeGuess();
        }
        System.out.println("Congratulations! You won!");
    }

    // kartlari rastgele olusturan ve matris'e yerlestiren metod
    public static void randomCardsCreate() {
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        List<Character> cardList = new ArrayList<>();

        // Her harften 2 tane eklenir
        for (char ch : letters) {
            cardList.add(ch);
            cardList.add(ch);
        }

        // karistirma islemi
        Collections.shuffle(cardList);

        // kartlar matrise yerlestirilir
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cards[i][j] = new Card(cardList.get(index));
                index++;
            }
        }
    }

    // kullanici tahmin yapar ve kontrol edilir
    public static void makeGuess() {
        System.out.println("First guess: enter i and j separated by space");
        int i1 = input.nextInt();
        int j1 = input.nextInt();

        while (cards[i1][j1].isGuessed()) {
            System.out.println("This card is already guessed, choose another");
            i1 = input.nextInt();
            j1 = input.nextInt();
        }
        cards[i1][j1].setGuessed(true);
        printBoard();

        System.out.println("Second guess: enter i and j separated by space");
        int i2 = input.nextInt();
        int j2 = input.nextInt();


        while (cards[i2][j2].isGuessed()) {
            System.out.println("This card is already guessed, choose another");
            i2 = input.nextInt();
            j2 = input.nextInt();
        }

        // kartlar eslesirse ikincisini de acar
        if (cards[i1][j1].getValue() == cards[i2][j2].getValue()) {
            cards[i2][j2].setGuessed(true);
            System.out.println("Correct match!");
        } else {
            System.out.println("Wrong match!");
            cards[i1][j1].setGuessed(false);
        }
    }

    // oyunun bitti mi kontrol edilir
    public static boolean isGameOver() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!cards[i][j].isGuessed()) {
                    return false; // Acilmamis kart var, oyun devam
                }
            }
        }
        return true; // tum kartlar acildi, oyun bitti
    }

    // tahtayi ekrana yazdirir
    public static void printBoard() {
        System.out.println("___________________");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cards[i][j].isGuessed()) {
                    System.out.print(" |" + cards[i][j].getValue() + "| ");
                } else {
                    System.out.print(" | | ");
                }
            }
            System.out.println();
        }
        System.out.println("___________________");
    }
}
