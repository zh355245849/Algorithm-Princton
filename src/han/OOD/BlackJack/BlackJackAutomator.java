package han.OOD.BlackJack;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh355245849 on 2017/6/3.
 */
public class BlackJackAutomator {
    private Deck deck;
    private BlackjackHand[] hands;
    private static final int HIT_UNTIL = 16;

    public BlackJackAutomator(int numOfPlayers) {
        hands = new BlackjackHand[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            hands[i] = new BlackjackHand();
        }
    }

    void initialDeck() {
        deck = new Deck();
        deck.shuffle();
    }

    boolean dealInitial() {
        for (BlackjackHand hand : hands) {
            Card[] cards = deck.dealHand(2);
            if (cards == null) {
                return false;
            }
            hand.addCards(cards);
        }
        return true;
    }

    List<Integer> getBlackJack() {
        List<Integer> winners = new ArrayList<>();
        for (int i = 0; i < hands.length; i++) {
            if (hands[i].isBlackJack()) {
                winners.add(i);
            }
        }
        return winners;
    }

    boolean playHand(BlackjackHand hand) {
        while (hand.score() < HIT_UNTIL) {
            Card card = deck.dealCard();
            if (card == null) {
                return false;
            }
            hand.addCards(new Card[] {card});
        }
        return true;
    }

    boolean playAllHands() {
        for (BlackjackHand hand : hands) {
            if (!playHand(hand)) {
                return false;
            }
        }
        return true;
    }

    List<Integer> getWinner() {
        List<Integer> winners = new ArrayList<>();
        int maxScore = 0;
        for (int i = 0; i < hands.length; i++) {
            BlackjackHand h = hands[0];
            if (!h.isBull()) {
                if (h.score() > maxScore) {
                    maxScore = h.score();
                    winners.clear();
                    winners.add(i);
                } else if (h.score() == maxScore) {
                    winners.add(i);
                }
            }
        }
        return winners;
    }

    void printHandsAndScore() {
        for (int i = 0; i < hands.length; i++) {
            System.out.println("Hand " + i + " (" + hands[i].score() + ")");
            System.out.println();
        }
    }

    public void simulate() {
        initialDeck();
        boolean success = dealInitial();
        if (!success) {
            System.out.println("No enough cards!!");
        } else {
            List<Integer> blackjacks = getBlackJack();
            if (blackjacks.size() > 0) {
                System.out.print("Blackjack at : ");
                for (int i : blackjacks) {
                    System.out.print(i + " ");
                }
                System.out.println("done!!");
            } else {
                List<Integer> winners = getWinner();
                if (winners.size() > 0) {
                    System.out.print("winners at : ");
                    for (int i : winners) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                } else {
                    System.out.println("All bust!!!!");
                }
            }
        }
    }

    public static void main(String[] args) {
        BlackJackAutomator automator = new BlackJackAutomator(4);
        automator.simulate();
    }
}
