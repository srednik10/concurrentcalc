package pokerhands;

import noidea.PokerCard;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kot on 18.06.16.
 */
public class ShortPairOfKs implements PokerHand {

    List<PokerCard> comboTemporaryDeadCards =  new LinkedList<>();
    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {

        long countInCards = cards.stream().filter(e -> e.value == 'K').count();
        long countInDeadCards = deadCards.stream().filter(e -> e.value == 'K').count();

        if(countInCards == 2) return true;
        else if(countInCards == 1 && countInDeadCards < 3 && cards.size() < 3) return true;
        else if(countInCards == 0 && countInDeadCards < 3 && cards.size() < 2) return true;
        else return false;
    }

    @Override
    public int getValue(int row) {
        return 8;
    }

    @Override
    public String getName() {
        return "Pair of Ks";
    }

    @Override
    public int getPriority() {
        return 8;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return null;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        long combos;
        int countDifferent = (int) cards.stream().filter(e -> e.value != 'K').count();
        int cardsNeeded = 2 - (int) cards.stream().filter(e -> e.value == 'K').count();
        int cardsAvailable = 4 - (int) deadCards.stream().filter(e -> e.value == 'K').count();
        if(cardsAvailable >= cardsNeeded) combos = CombinatoricsUtils.binomialCoefficient(cardsAvailable, cardsNeeded);
        else combos = 0 ;
        if(countDifferent == 0) return combos*12;
        else return combos;
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;
    }
}
