package han.OOD.BlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zh355245849 on 2017/5/28.
 */
public class Deck {

    private List<Card> cards;
    private int dealNum;

    public Deck() {
        this.dealNum = 0;
        cards = new ArrayList<>();
        for (int i = 0; i <= 13; i++) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(suit, i));
            }
        }
    }

    public void shuffle() {
        for (int i = 0; i < cards.size(); i++) {
            int random = new Random().nextInt(cards.size() - i) + i;
            Card card1 = cards.get(random);
            Card card2 = cards.get(i);
            cards.set(i, card1);
            cards.set(random, card2);
        }
    }

    public Card[] dealHand(int number) {
        if (remainCard() < number) {
            return null;
        }
        Card[] hcards = new Card[number];
        for (int i = 0; i < number; i++) {
            hcards[i] = dealCard();
        }
        return hcards;
    }

    public Card dealCard() {
        return remainCard() == 0 ? null : cards.get(dealNum++);
    }

    public int remainCard() {
        return cards.size() - dealNum;
    }
}
