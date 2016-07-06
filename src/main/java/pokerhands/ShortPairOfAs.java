package pokerhands;

import noidea.PokerCard;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kot on 18.06.16.
 */
public class ShortPairOfAs implements PokerHand {

    List<PokerCard> comboTemporaryDeadCards =  new LinkedList<>();
    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {

        long countInCards = cards.stream().filter(e -> e.value == 'A').count();
        long countInDeadCards = deadCards.stream().filter(e -> e.value == 'A').count();

        if(countInCards == 2) return true;
        else if(countInCards == 1 && countInDeadCards < 3 && cards.size() < 3) return true;
        else if(countInCards == 0 && countInDeadCards < 3 && cards.size() < 2) return true;
        else return false;
    }

    @Override
    public int getValue(int row) {
        return 9;
    }

    @Override
    public String getName() {
        return "Pair of As";
    }

    @Override
    public int getPriority() {
        return 9;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return null;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        long combos;
        int countDifferent = (int) cards.stream().filter(e -> e.value != 'A').count();
       // System.out.println(countDifferent);
        int cardsNeeded = 2 - (int) cards.stream().filter(e -> e.value == 'A').count();
        //System.out.println(cardsNeeded);
        int cardsAvailable = 4 - (int) deadCards.stream().filter(e -> e.value == 'A').count();
        //System.out.println(cardsAvailable);
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
