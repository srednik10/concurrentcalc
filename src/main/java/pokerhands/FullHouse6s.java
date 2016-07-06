package pokerhands;

import noidea.PokerCard;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by kot on 22.06.16.
 */
public class FullHouse6s implements PokerHand {

    Character valueOfPair;
    List<PokerCard> producedDeadCards = new LinkedList<>();
    List<PokerCard> comboTemporaryDeadCards = new LinkedList<>();

    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {

        long countInDeadCards = deadCards.stream().filter(e -> e.value == '6').count();

        Map<Character,Integer> map = new HashMap<>();
        map.clear();
        producedDeadCards.clear();
        map.put('2',0);
        map.put('3',0);
        map.put('4',0);
        map.put('5',0);
        map.put('6',0);
        map.put('7',0);
        map.put('8',0);
        map.put('9',0);
        map.put('T',0);
        map.put('J',0);
        map.put('Q',0);
        map.put('K',0);
        map.put('A',0);
        cards.stream().forEach(e -> map.replace(e.value, map.get(e.value) + 1));

        if (map.entrySet().stream().filter(e -> e.getValue() != 0).count() == 2 && map.entrySet().stream().anyMatch(e -> e.getValue() != 0 && e.getKey() == '6')) {
            Character valueOfPair = map.entrySet().stream().filter(e -> e.getValue() != 0 && e.getKey() != '6').findFirst().get().getKey();

            long countInCards = cards.stream().filter(e -> e.value == '6').count();
            long countValueOfPairInCards = cards.stream().filter(e -> e.value == valueOfPair).count();
            long countValueOfPairInDeadCards = deadCards.stream().filter(e -> e.value == valueOfPair).count();

            if (countInDeadCards < 2 && countValueOfPairInDeadCards < 3 && countValueOfPairInCards < 3) {
                for(int i=0;i<3-countInCards;i++) producedDeadCards.add(new PokerCard("6x"));
                for(int i=0;i<2-countValueOfPairInCards;i++) producedDeadCards.add(new PokerCard(new StringBuilder().append(valueOfPair).append('x').toString()));
                return true;
            }
            else return false;

        }else {
            if((map.entrySet().stream().filter(e -> e.getValue() != 0).count() == 1 && (cards.size() < 3 || cards.size() == 3 && map.entrySet().stream().findFirst().filter(e -> e.getValue() != 0).get().getKey() == '6') ) || cards.size() == 0 )  return true;
            else return false;
        }
    }

    @Override
    public int getValue(int row) {
        if(row == 2) return 12;
        else return 6;
    }

    @Override
    public String getName() {
        return "Full house - 6s";
    }

    @Override
    public int getPriority() {
        return 57;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        comboTemporaryDeadCards.clear();
        long combos;
        int countInCards = (int)cards.stream().filter(e -> e.value == '6').count();
        int cardsNeeded = 3 - (int)cards.stream().filter(e -> e.value == '6').count();
        int cardsAvaiable = 4 - (int)deadCards.stream().filter(e -> e.value == '6').count();
        if(cardsAvaiable >= cardsNeeded) combos = CombinatoricsUtils.binomialCoefficient(cardsAvaiable,cardsNeeded);
        else return  0;
        for(int i=0 ; i<cardsNeeded;i++) comboTemporaryDeadCards.add(new PokerCard(new StringBuilder().append('6').append('x').toString()));
        if(cards.size()-countInCards == 1){
            Character valueOfPair = cards.stream().filter(e -> e.value != '6').findFirst().get().value;
            comboTemporaryDeadCards.add(new PokerCard(new StringBuilder().append(valueOfPair).append('x').toString()));
            int valueOfPairAvailable = 4 - (int)deadCards.stream().filter(e -> e.value == valueOfPair).count();
            combos = combos * valueOfPairAvailable;
        }else {
            combos = combos * 11;
            comboTemporaryDeadCards.add(new PokerCard(new StringBuilder().append('x').append('x').toString()));
            comboTemporaryDeadCards.add(new PokerCard(new StringBuilder().append('x').append('x').toString()));
        }

        return combos;
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;
    }

}
