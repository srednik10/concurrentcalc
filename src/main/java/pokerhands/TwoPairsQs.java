package pokerhands;

import noidea.PokerCard;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kot on 18.06.16.
 */
public class TwoPairsQs implements PokerHand {

    List<PokerCard> producedDeadCards = new LinkedList<>();
    List<PokerCard> comboTemporaryDeadCards = new LinkedList<>();

    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {

        producedDeadCards.clear();
        long countInCards = cards.stream().filter(e -> e.value == 'Q').count();
        long countInDeadCards = deadCards.stream().filter(e -> e.value == 'Q').count();

        if(countInCards == 2 && cards.size() <5) return true;
        else if(countInCards == 1 && countInDeadCards < 3 && cards.size() < 3) {producedDeadCards.add(new PokerCard("Qx"));return true;}
        else if(countInCards == 0 && countInDeadCards < 3 && cards.size() < 3) {producedDeadCards.add(new PokerCard("Qx"));producedDeadCards.add(new PokerCard("Qx"));return true;}
        else if(countInCards == 2 && cards.size() == 5){
            List<PokerCard> listOfDifferent =  new LinkedList<>();
            listOfDifferent.clear();
            cards.forEach(e -> {
                if(e.value != 'Q') listOfDifferent.add(e);
            });
            if(listOfDifferent.get(0).value == listOfDifferent.get(1).value ||
                    listOfDifferent.get(0).value == listOfDifferent.get(2).value ||
                    listOfDifferent.get(2).value == listOfDifferent.get(1).value ) return true;
            else return false;
        }else return false;
    }

    @Override
    public int getValue(int row) {
        return 0;
    }

    @Override
    public String getName() {
        return "Two pairs Qs";
    }

    @Override
    public int getPriority() {
        return 19;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        comboTemporaryDeadCards.clear();
        List<PokerCard> listOfDifferent =  new LinkedList<>();
        listOfDifferent.clear();
        cards.forEach(e -> {
            if(e.value != 'Q') listOfDifferent.add(e);
        });
        int countDifferent = (int) cards.stream().filter(e -> e.value != 'Q').count();
        int cardsNeeded = 2 - (int) cards.stream().filter(e -> e.value == 'Q').count();
        int cardsAvailable = 4 - (int) deadCards.stream().filter(e -> e.value == 'Q').count();
        for(int i=0; i< cardsNeeded ;i++) comboTemporaryDeadCards.add(new PokerCard("Qx"));
        long combos;
        if(cardsAvailable >= cardsNeeded) combos = CombinatoricsUtils.binomialCoefficient(cardsAvailable,cardsNeeded);
        else return  0;
        if(countDifferent == 1) {
            combos = combos*12;
            for(int i=0 ; i < 2 ; i++) comboTemporaryDeadCards.add(new PokerCard("xx"));
        }else if(countDifferent == 2){
            if(listOfDifferent.get(0).value == listOfDifferent.get(1).value){
                combos = combos*11;
            }else {
                int differentLeft = 6 - (int)deadCards.stream().filter(e -> e.value == listOfDifferent.get(0).value || e.value == listOfDifferent.get(1).value).count();
                combos = combos*differentLeft;
            }
        }
        return combos;
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;
    }
}
