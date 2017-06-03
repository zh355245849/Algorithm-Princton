package han.OOD.BlackJack;

import han.OOD.parkingLot.Car;

/**
 * Created by zh355245849 on 2017/5/28.
 */
public class Card {
    private Suit suit;
    private int value;
    public Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }
    public Suit getSuit() {
        return suit;
    }
    public int getValue() {
        return value;
    }
}