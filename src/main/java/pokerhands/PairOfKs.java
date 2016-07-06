package pokerhands;

import noidea.PokerCard;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kot on 18.06.16.
 */
public class PairOfKs implements PokerHand {

    List<PokerCard> producedDeadCards = new LinkedList<>();
    List<PokerCard> comboTemporaryDeadCards = new LinkedList<>();

    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {

        producedDeadCards.clear();
        long countInCards = cards.stream().filter(e -> e.value == 'K').count();
        long countInDeadCards = deadCards.stream().filter(e -> e.value == 'K').count();


        List<PokerCard> listOfDifferent =  new LinkedList<>();
        listOfDifferent.clear();
        cards.forEach(e -> {
            if (e.value != 'K') listOfDifferent.add(e);
        });
        if(countInCards == 2 && listOfDifferent.size() == 3){
            if(listOfDifferent.get(0).value != listOfDifferent.get(1).value &&
                    listOfDifferent.get(1).value != listOfDifferent.get(2).value &&
                    listOfDifferent.get(0).value != listOfDifferent.get(2).value) return true;
            else return false;
        } else if(countInCards == 2 && listOfDifferent.size() == 2){
            if(listOfDifferent.get(0).value != listOfDifferent.get(1).value) return true;
            else return false;
        }else if(countInCards == 2) return true;
        else if(countInCards == 1 && countInDeadCards < 3 && cards.size() < 5) {producedDeadCards.add(new PokerCard("Kx"));return true;}
        else if(countInCards == 0 && countInDeadCards < 3 && cards.size() < 4) {producedDeadCards.add(new PokerCard("Kx"));producedDeadCards.add(new PokerCard("Kx"));return true;}
        else return false;
    }

    @Override
    public int getValue(int row) {
        return 0;
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
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        comboTemporaryDeadCards.clear();
        int countDifferent = (int) cards.stream().filter(e -> e.value != 'K').count();
        int cardsNeeded = 2 - (int) cards.stream().filter(e -> e.value == 'K').count();
        int cardsAvailable = 4 - (int) deadCards.stream().filter(e -> e.value == 'K').count();
        long combos;
        if(cardsAvailable >= cardsNeeded) combos = CombinatoricsUtils.binomialCoefficient(cardsAvailable,cardsNeeded);
        else return  0;
        for (int i=0 ; i < cardsNeeded ; i++) comboTemporaryDeadCards.add(new PokerCard("Kx"));
        int leftNeeded = 3 - countDifferent;
        long leftCombos = CombinatoricsUtils.binomialCoefficient(52- deadCards.size()-cardsNeeded,leftNeeded);
        return combos*leftCombos;
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;
    }
}