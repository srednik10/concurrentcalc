package pokerhands;

import noidea.PokerCard;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kot on 18.06.16.
 */
public class ShortThreeOfKind2s implements PokerHand{

    List<PokerCard> comboTemporaryDeadCards =  new LinkedList<>();
    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {

        long countInCards = cards.stream().filter(e -> e.value == '2').count();
        long countInDeadCards = deadCards.stream().filter(e -> e.value == '2').count();

        if(countInCards == 3) return true;
        else if(countInCards == 2 && countInDeadCards < 2 && cards.size() == 2) return true;
        else if(countInCards == 1 && countInDeadCards < 2 && cards.size() == 1) return true;
        else if(countInCards == 0 && countInDeadCards < 2 && cards.size() == 0) return true;
        else return false;
    }

    @Override
    public int getValue(int row) {
        return 10;
    }

    @Override
    public String getName() {
        return "Three of kind - 2s";
    }

    @Override
    public int getPriority() {
        return 22;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return null;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        int cardsNeeded = 3 - (int) cards.stream().filter(e -> e.value == '2').count();
        int cardsAvailable = 4 - (int) deadCards.stream().filter(e-> e.value == '2').count();

        long combos;
        if(cardsAvailable >= cardsNeeded) combos = CombinatoricsUtils.binomialCoefficient(cardsAvailable,cardsNeeded);
        else return  0;

        return combos;
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;
    }
}
