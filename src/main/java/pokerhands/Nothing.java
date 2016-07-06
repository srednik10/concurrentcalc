package pokerhands;

import noidea.PokerCard;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kot on 02.07.16.
 */
public class Nothing implements PokerHand {

    List<PokerCard> producedDeadCards = new LinkedList<>();
    List<PokerCard> comboTemporaryDeadCards = new LinkedList<>();

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
        return "Nothing";
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        comboTemporaryDeadCards.clear();
        int cardsNeeded = 5 - cards.size();
        int cardsAvailable = 52 - deadCards.size();
        for(int i=0 ; i < cardsNeeded ; i++ ) comboTemporaryDeadCards.add(new PokerCard("xx"));
        return CombinatoricsUtils.binomialCoefficient(cardsAvailable,cardsNeeded);
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;
    }
}
