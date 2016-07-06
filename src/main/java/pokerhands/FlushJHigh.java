package pokerhands;

import noidea.PokerCard;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by kot on 20.06.16.
 */
public class FlushJHigh implements PokerHand {

    List<PokerCard> producedDeadCards = new LinkedList<>();
    List<PokerCard> comboTemporaryDeadCards = new LinkedList<>();
    Character color;

    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {

        Map<Character,Integer> map = new HashMap<>();
        map.clear();
        producedDeadCards.clear();
        map.put('C',0);
        map.put('D',0);
        map.put('H',0);
        map.put('S',0);
        cards.stream().forEach(e -> map.replace(e.color, map.get(e.color) + 1));
        if(map.entrySet().stream().filter(e -> e.getValue() == 0).count() > 2){
            if(cards.size() != 0) color = map.entrySet().stream().filter(e -> e.getValue() != 0).findFirst().get().getKey();
            else color = 'C';
            if(cards.stream().allMatch(e -> e.value == '2' || e.value == '3' || e.value == '4' || e.value == '5' || e.value == '6' || e.value == '7' || e.value == '8' || e.value == '9' || e.value == 'T' || e.value == 'J') && deadCards.stream().filter(e -> e.color == color && (e.value == '2'  || e.value == '3' || e.value == '4' || e.value == '5' || e.value == '6' || e.value == '7' || e.value == '8' || e.value == '9' || e.value == 'T' || e.value == 'J')).count() < 6) {
                for(int i=0;i<5-cards.size();i++)  producedDeadCards.add(new PokerCard(new StringBuilder().append('x').append(color).toString()));
                return true;
            }
            else return false;
        }else return false;
    }

    @Override
    public int getValue(int row) {
        if(row == 2) return 8;
        else return 4;
    }

    @Override
    public String getName() {
        return "Flush J high";
    }

    @Override
    public int getPriority() {
        return 49;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        comboTemporaryDeadCards.clear();
        int cardsNeeded = 5 - (int)cards.stream().filter(e -> e.value == '2' || e.value == '3' || e.value == '4' || e.value == '5' || e.value == '6' || e.value == '7' || e.value == '8' || e.value == '9' || e.value == 'T' || e.value == 'J').count();
        int cardsAvailable = 10 - (int)deadCards.stream().filter(e -> e.value == '2' || e.value == '3' || e.value == '4' || e.value == '5' || e.value == '6' || e.value == '7' || e.value == '8' || e.value == '9' || e.value == 'T' || e.value == 'J').count();
        long combos;
        if(cardsAvailable >= cardsNeeded) combos = CombinatoricsUtils.binomialCoefficient(cardsAvailable,cardsNeeded);
        else return  0;
        for(int i=0;i<cardsNeeded;i++)comboTemporaryDeadCards.add(new PokerCard(new StringBuilder().append('x').append(color).toString()));
        return combos;
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;
    }
}
