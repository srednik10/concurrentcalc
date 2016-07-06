package pokerhands;

import noidea.PokerCard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by kot on 19.06.16.
 */
public class Straight6High implements PokerHand {

    List<PokerCard> producedDeadCards = new LinkedList<>();
    List<PokerCard> comboTemporaryDeadCards = new LinkedList<>();

    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {

        if(cards.stream().allMatch(e -> e.value == '6' || e.value == '2' || e.value == '3' || e.value == '4' || e.value == '5')){
            Map<Character,Integer> map = new HashMap<>();
            map.clear();
            producedDeadCards.clear();
            map.put('6',0);
            map.put('2',0);
            map.put('3',0);
            map.put('4',0);
            map.put('5',0);
            cards.stream().forEach(e -> map.replace(e.value,map.get(e.value)+1));
            if(map.entrySet().stream().allMatch(entry -> (entry.getValue() ==  0  && deadCards.stream().filter(e -> e.value == entry.getKey()).count() < 4) || entry.getValue() == 1)){
                if(cards.stream().noneMatch(e -> e.value == '6')) producedDeadCards.add(new PokerCard(new StringBuilder().append('6').append('x').toString()));
                if(cards.stream().noneMatch(e -> e.value == '2')) producedDeadCards.add(new PokerCard(new StringBuilder().append('2').append('x').toString()));
                if(cards.stream().noneMatch(e -> e.value == '3')) producedDeadCards.add(new PokerCard(new StringBuilder().append('3').append('x').toString()));
                if(cards.stream().noneMatch(e -> e.value == '4')) producedDeadCards.add(new PokerCard(new StringBuilder().append('4').append('x').toString()));
                if(cards.stream().noneMatch(e -> e.value == '5')) producedDeadCards.add(new PokerCard(new StringBuilder().append('5').append('x').toString()));
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
        return "Straight 6 high";
    }

    @Override
    public int getPriority() {
        return 36;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        comboTemporaryDeadCards.clear();
        long combos = 1;
        if(cards.stream().filter(e -> e.value == '6').count() == 0) {combos = combos * (4 - deadCards.stream().filter(e -> e.value == '6').count()); comboTemporaryDeadCards.add(new PokerCard("6x"));}
        if(cards.stream().filter(e -> e.value == '5').count() == 0) {combos = combos * (4 - deadCards.stream().filter(e -> e.value == '5').count()); comboTemporaryDeadCards.add(new PokerCard("5x"));}
        if(cards.stream().filter(e -> e.value == '4').count() == 0) {combos = combos * (4 - deadCards.stream().filter(e -> e.value == '4').count()); comboTemporaryDeadCards.add(new PokerCard("4x"));}
        if(cards.stream().filter(e -> e.value == '3').count() == 0) {combos = combos * (4 - deadCards.stream().filter(e -> e.value == '3').count()); comboTemporaryDeadCards.add(new PokerCard("3x"));}
        if(cards.stream().filter(e -> e.value == '2').count() == 0) {combos = combos * (4 - deadCards.stream().filter(e -> e.value == '2').count()); comboTemporaryDeadCards.add(new PokerCard("2x"));}

        return combos;
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;
    }
}
