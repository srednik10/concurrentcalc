package pokerhands;

import noidea.PokerCard;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kot on 19.06.16.
 */
public class ThreeOfKind2s implements PokerHand{

    List<PokerCard> producedDeadCards = new LinkedList<>();
    List<PokerCard> comboTemporaryDeadCards = new LinkedList<>();

    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {

        producedDeadCards.clear();
        long countInCards = cards.stream().filter(e -> e.value == '2').count();
        long countInDeadCards = deadCards.stream().filter(e -> e.value == '2').count();


        List<PokerCard> listOfDifferent =  new LinkedList<>();
        listOfDifferent.clear();
        cards.forEach(e -> {
            if (e.value != '2') listOfDifferent.add(e);
        });
        if(countInCards == 3 && listOfDifferent.size() == 2){
            if(listOfDifferent.get(0).value == listOfDifferent.get(1).value) return false;
            else return true;
        } else if(countInCards == 3) return true;
        else if(countInCards == 2 && countInDeadCards < 2 && cards.size() < 5) {for(int i=0;i<1;i++) producedDeadCards.add(new PokerCard("2x")); return true;}
        else if(countInCards == 1 && countInDeadCards < 2 && cards.size() < 4) {for(int i=0;i<2;i++) producedDeadCards.add(new PokerCard("2x")); return true;}
        else if(countInCards == 0 && countInDeadCards < 2 && cards.size() < 3) {for(int i=0;i<3;i++) producedDeadCards.add(new PokerCard("2x")); return true;}
        else return false;
    }

    @Override
    public int getValue(int row) {
        if(row == 2) return 2;
        else return 0;
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
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        comboTemporaryDeadCards.clear();
        int countDifferent = (int) cards.stream().filter(e -> e.value != '2').count();
        int cardsNeeded = 3 - (int)cards.stream().filter(e -> e.value == '2').count();
        int cardsAvailable = 4 - (int) deadCards.stream().filter(e -> e.value == '2').count();

        long combos;
        if(cardsAvailable >= cardsNeeded) combos = CombinatoricsUtils.binomialCoefficient(cardsAvailable,cardsNeeded);
        else return  0;

        for (int i=0 ; i < cardsNeeded ; i++) comboTemporaryDeadCards.add(new PokerCard("2x"));
        int leftNeeded = 2 - countDifferent;
        long leftCombos = CombinatoricsUtils.binomialCoefficient(52- deadCards.size()-cardsNeeded,leftNeeded);

        return combos*leftCombos;
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;
    }
}
