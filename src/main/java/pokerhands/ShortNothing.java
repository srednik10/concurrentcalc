package pokerhands;

import noidea.PokerCard;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kot on 02.07.16.
 */
public class ShortNothing implements PokerHand {

    List<PokerCard> producedDeadCards = new LinkedList<>();
    List<PokerCard> comboTemporaryDeadCards =  new LinkedList<>();

    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {
        return true;
    }

    @Override
    public int getValue(int row) {
        return 0;
    }

    @Override
    public String getName() {
        return "Short nothing";
    }

    @Override
    public int getPriority() {
        return -1;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        int cardsNeeded = 3 - cards.size();
        int cardsAvailable = 52 - deadCards.size() - cardsNeeded;
        return CombinatoricsUtils.binomialCoefficient(cardsAvailable,cardsNeeded);
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;
    }
}
