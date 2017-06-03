package han.OOD.BlackJack;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by zh355245849 on 2017/5/28.
 */
public class Hand {

    protected final List<Card> cards = new ArrayList<>();

    public int score() {
        return 0;
    }

    public void addCards(Card[] c) {
        Collections.addAll(cards, c);
    }

    public int getSize() {
        return cards.size();
    }
}
