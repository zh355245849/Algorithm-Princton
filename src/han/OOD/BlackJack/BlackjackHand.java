package han.OOD.BlackJack;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh355245849 on 2017/5/28.
 */
public class BlackjackHand extends Hand {
    @Override
    public int score() {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        List<Integer> possibleScore = possibleScore();
        for (Integer i : possibleScore) {
            if (i > 21 && i < min) {
                min = i;
            } else if (i <= 21 && i > max) {
                max = i;
            }
        }
        return max == Integer.MIN_VALUE ? min : max;
    }

    private List<Integer> possibleScore() {
        List<Integer> scores = new ArrayList<>();
        for (Card c : cards) {
            updateScore(c, scores);
        }
        return scores;
    }

    private void updateScore(Card card, List<Integer> score) {
        int[] cardValue = getScore(card);
        if (score.isEmpty()) {
            for (int i = 0; i < cardValue.length; i++) {
                score.add(cardValue[i]);
            }
        } else {
            for (int i = 0; i < score.size(); i++) {
                int sc = score.get(i);
                score.set(i, sc + cardValue[0]);
                for (int j = 1; j < cardValue.length; j++) {
                    score.add(sc + cardValue[j]);
                }
            }
        }
    }

    private int[] getScore(Card card) {
        if (card.getValue() != 1) {
            return new int[] {Math.min(10, card.getValue())};
        } else {
            return new int[] {1, 11};
        }
    }

    public boolean isBlackJack() {
        if (cards.size() != 2) {
            return false;
        }
        Card card1 = cards.get(0);
        Card card2 = cards.get(1);
        return (isAce(card1) && isFaceValue(card2)) ||
                (isAce(card2) && isFaceValue(card1));
    }

    public boolean isBull() {
        return score() > 21;
    }

    private boolean isAce(Card card) {
        return card.getValue() == 1;
    }

    private boolean isFaceValue(Card card) {
        return card.getValue() >= 11 && card.getValue() <= 13;
    }
}
