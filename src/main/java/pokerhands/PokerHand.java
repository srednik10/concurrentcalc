package pokerhands;

import noidea.PokerCard;

import java.util.List;

/**
 * Created by kot on 20.05.16.
 */
public interface PokerHand {

    boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards);

    int getValue(int row);

    String getName();

    int getPriority();

    List<PokerCard> getProducedDeadCard();

    long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards);

    List<PokerCard> getComboTemporaryDeadCards();
}
