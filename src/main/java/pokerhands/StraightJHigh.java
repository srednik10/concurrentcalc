package pokerhands;

import noidea.PokerCard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by kot on 19.06.16.
 */
public class StraightJHigh implements PokerHand {

    List<PokerCard> producedDeadCards = new LinkedList<>();
    List<PokerCard> comboTemporaryDeadCards = new LinkedList<>();

    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {

        if(cards.stream().allMatch(e -> e.value == 'J' || e.value == '7' || e.value == '8' || e.value == '9' || e.value == 'T')){
            Map<Character,Integer> map = new HashMap<>();
            map.clear();
            producedDeadCards.clear();
            map.put('J',0);
            map.put('7',0);
            map.put('8',0);
            map.put('9',0);
            map.put('T',0);
            cards.stream().forEach(e -> map.replace(e.value,map.get(e.value)+1));
            if(map.entrySet().stream().allMatch(entry -> (entry.getValue() ==  0  && deadCards.stream().filter(e -> e.value == entry.getKey()).count() < 4) || entry.getValue() == 1)){
                if(cards.stream().noneMatch(e -> e.value == 'T')) producedDeadCards.add(new PokerCard(new StringBuilder().append('T').append('x').toString()));
                if(cards.stream().noneMatch(e -> e.value == 'J')) producedDeadCards.add(new PokerCard(new StringBuilder().append('J').append('x').toString()));
                if(cards.stream().noneMatch(e -> e.value == '8')) producedDeadCards.add(new PokerCard(new StringBuilder().append('8').append('x').toString()));
                if(cards.stream().noneMatch(e -> e.value == '9')) producedDeadCards.add(new PokerCard(new StringBuilder().append('9').append('x').toString()));
                if(cards.stream().noneMatch(e -> e.value == '7')) producedDeadCards.add(new PokerCard(new StringBuilder().append('7').append('x').toString()));
                return true;
            }
            else return false;
        }else return false;


    }

    @Override
    public int getValue(int row) {
        if(row == 2) return 4;
        else return 2;
    }

    @Override
    public String getName() {
        return "Straight J high";
    }

    @Override
    public int getPriority() {
        return 41;
    }


    @Override
    public List<PokerCard> getProducedDeadCard() {
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        comboTemporaryDeadCards.clear();
        long combos = 1;
        if(cards.stream().filter(e -> e.value == 'J').count() == 0) {combos = combos * (4 - deadCards.stream().filter(e -> e.value == 'J').count()); comboTemporaryDeadCards.add(new PokerCard("Jx"));}
        if(cards.stream().filter(e -> e.value == '7').count() == 0) {combos = combos * (4 - deadCards.stream().filter(e -> e.value == '7').count()); comboTemporaryDeadCards.add(new PokerCard("7x"));}
        if(cards.stream().filter(e -> e.value == '8').count() == 0) {combos = combos * (4 - deadCards.stream().filter(e -> e.value == '8').count()); comboTemporaryDeadCards.add(new PokerCard("8x"));}
        if(cards.stream().filter(e -> e.value == '9').count() == 0) {combos = combos * (4 - deadCards.stream().filter(e -> e.value == '9').count()); comboTemporaryDeadCards.add(new PokerCard("9x"));}
        if(cards.stream().filter(e -> e.value == 'T').count() == 0) {combos = combos * (4 - deadCards.stream().filter(e -> e.value == 'T').count()); comboTemporaryDeadCards.add(new PokerCard("Tx"));}

        return combos;
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;
    }
}
