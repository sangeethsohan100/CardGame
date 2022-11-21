import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Player implements Runnable {
    // Thread Safe

    public ArrayList<Card> hand = new ArrayList<Card>();
    // int count;
    public ArrayList<String> players = new ArrayList<>();
    private int playercard; // preffered card

    private int id;
    private CardDeck lDeck, rDeck; // left and right deck
    public ArrayList<Card> card = new ArrayList<>();

    public Player(CardDeck left, CardDeck right, int playernumber) {
        this.id = playernumber;
        this.playercard = playernumber;
        this.lDeck = left;
        this.rDeck = right;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    private void pickCard(Card lDeck) {
        hand.add(lDeck);
    }

    private void discardCard(Card card, CardDeck rdDeck) {
        rdDeck.addCard(card);
        hand.remove(card);
    }

    private Card chooseToDiscard() {
        ArrayList<Card> discard = new ArrayList<>();
        for (Card card : hand) {
            if (card.getValue() != id) {
                discard.add(card);
            }
        }

        Random rand = new Random();
        // Chooses to discard a random card from the discard list
        return discard.get(rand.nextInt(discard.size()));
    }

    }

    public void setPlayerNumber(int playernumber) {
        this.id = playernumber;

    }

    public int getPlayerNumber() {
        return this.id;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    /// Match hand
    public boolean matchHand(int value) {
        if (hand.size() == value) {
            return true;

        } else {
            return false;
        }

    }

    // add player hand set player hand

    // public void addPlayerHand(int value) {
    // return this.hand.add(value);
    // }

    // public void setPlayerHand(ArrayList<Card> playerHand) {
    // this.hand = playerHand;
    // }

    public int getPlayerCard() {
        return playercard;
    }

    private String getHandAsString() {
        String currentHand = "";
        for (Card c : hand) {
            currentHand = currentHand + " " + c.getValue();
        }
        return currentHand;
    }

    public void run() {

    }
}
