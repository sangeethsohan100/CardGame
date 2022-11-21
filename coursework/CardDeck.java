import java.util.ArrayList;

public class CardDeck {

    private ArrayList<Card> deck = new ArrayList<Card>();
    public int deckNumber;
    public ArrayList<Player> players = new ArrayList<>();

    public CardDeck(int id) {

        this.deckNumber = id;
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public Card[] getDeck() {
        Card[] d = new Card[this.deck.size()];
        for (Integer n = 0; n < d.length; n++) {
            d[n] = this.deck.get(n);
        }
        return d;
    }

    public Card getTopCard() {
        Card top = deck.get(0);
        deck.remove(top);
        return top;
    }

    // private Card getBottomCard() {
    // Card bottom = deck.get(deck.size() - 1);
    // deck.remove(bottom);
    // return bottom;
    // }

    public int getDeckSize() {
        return deck.size();
    }

    public void setDeckNumber(int deckNo) {
        this.deckNumber = deckNo;
    }

    public int getDeckNumber() {
        return this.deckNumber;

    }

}
