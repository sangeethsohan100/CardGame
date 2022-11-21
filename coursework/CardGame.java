import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
    static Scanner scan = new Scanner(System.in);
    static int noOfPlayers;
    public ArrayList<Card> hand = new ArrayList<Card>();
    ArrayList<Player> player = new ArrayList<>();

    // public void CreateGame() {

    // }

    public static void main(String[] args) throws IOException {
        boolean input;

        System.out.println("Enter the number of players:");
        try {
            int noPlayer = scan.nextInt();
            input = true;

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Player Input not Valid");
            input = false;
        }

        System.out.println("Enter location of pack:");
        String packFile = scan.nextLine();
        ArrayList<Card> pack = new ArrayList<>();
        boolean validPack = false;
        while (!validPack) {
            try {
                Scanner packScan = new Scanner(new File(packFile));
                while (packScan.hasNextLine()) {

                    int value = Integer.parseUnsignedInt(packScan.nextLine());
                    pack.add(new Card(value));
                }
                if (pack.size() == noOfPlayers * 8) {
                    validPack = true;
                    packScan.close();
                    scan.close();
                } else {
                    System.out.println("Invalid pack size, please enter location of a valid pack to load:");
                    packFile = scan.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Invalid pack name, please enter location of a valid pack to load:");
                packFile = scan.nextLine();

            }

        }

        ArrayList<CardDeck> deck = createDecks(noOfPlayers);

        ArrayList<Player> player = createPlayers(noOfPlayers, deck);

        dealCards(pack, player, deck);

        startGame(player);

    }

    public static ArrayList<CardDeck> createDecks(int noOfPlayers) {

        ArrayList<CardDeck> deck = new ArrayList<>();

        for (int i = 0; i < noOfPlayers; i++) {
            deck.add(new CardDeck(i + 1));
        }
        return deck;

    }

    public static ArrayList<Player> createPlayers(int noPlayers, ArrayList<CardDeck> decks) {
        ArrayList<Player> player = new ArrayList<>();

        for (int i = 0; i < noPlayers; i++) {
            CardDeck left = decks.get(i);
            CardDeck right = decks.get((i + 1) % noPlayers);
            player.add(new Player(left, right, i + 1));
        }
        return player;

    }

    public static void dealCards(ArrayList<Card> pack, ArrayList<Player> player, ArrayList<CardDeck> deck) {

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < player.size(); j++) {
                player.get(j).addCard(pack.get(player.size() * i + j));
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < player.size(); j++) {
                deck.get(j).addCard(pack.get(i * player.size() + j + 4 * player.size()));
            }

        }

    }

    public static void startGame(ArrayList<Player> player) {
        for (Player play : player) {
            Thread thread = new Thread(play);
            thread.start();

        }
    }

    public boolean playerWin(ArrayList<Card> hand) {
        boolean win = true;

        for (int i = 0; i < 3; i++) {
            if (this.hand.get(i).getValue() != this.hand.get(i + 1).getValue()) {
                win = false;
            }
        }

        return win;
    }

}
