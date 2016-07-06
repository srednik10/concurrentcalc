package pokerhands;

import noidea.PokerCard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by kot on 23.06.16.
 */
public class Poker7High implements PokerHand {

    List<PokerCard> producedDeadCards = new LinkedList<>();
    List<PokerCard> comboTemporaryDeadCards = new LinkedList<>();
    Character color;

    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {
        boolean canMakeOnEmptyBoard = false;
        Map<Character,Integer> map = new HashMap<>();
        map.clear();
        producedDeadCards.clear();
        map.put('C',0);
        map.put('D',0);
        map.put('H',0);
        map.put('S',0);
        for(PokerCard card : deadCards){
            if(card.value == '6' || card.value == '7' || card.value == '3' || card.value == '4' || card.value == '5')
                if(card.color != 'x')  map.replace(card.color,map.get(card.color) + 1);
        }
        if(map.entrySet().stream().filter(e -> e.getValue() == 0).count() > 0) canMakeOnEmptyBoard = true;
        if(cards.size() != 0){
            color = cards.get(0).color;
            if (cards.stream().allMatch(e -> e.color == color && (e.value == '6' || e.value == '7' || e.value == '3' || e.value == '4' || e.value == '5')) && deadCards.stream().noneMatch(e -> e.color == color && (e.value == '6' || e.value == '7' || e.value == '3' || e.value == '4' || e.value == '5')))  {
                if(cards.stream().noneMatch(e -> e.value == '3')) producedDeadCards.add(new PokerCard(new StringBuilder().append('3').append(color).toString()));
                if(cards.stream().noneMatch(e -> e.value == '7')) producedDeadCards.add(new PokerCard(new StringBuilder().append('7').append(color).toString()));
                if(cards.stream().noneMatch(e -> e.value == '6')) producedDeadCards.add(new PokerCard(new StringBuilder().append('6').append(color).toString()));
                if(cards.stream().noneMatch(e -> e.value == '5')) producedDeadCards.add(new PokerCard(new StringBuilder().append('5').append(color).toString()));
                if(cards.stream().noneMatch(e -> e.value == '4')) producedDeadCards.add(new PokerCard(new StringBuilder().append('4').append(color).toString()));
                return true;
            }
            else return false;
        }else if(canMakeOnEmptyBoard) {
            producedDeadCards.add(new PokerCard(new StringBuilder().append('5').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('4').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('3').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('7').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('6').append('x').toString()));
            return true;
        }
        else return false;
    }

    @Override
    public int getValue(int row) {
        if(row == 2) return 30;
        else return 15;
    }

    @Override
    public String getName() {
        return "Poker 7 high";
    }

    @Override
    public int getPriority() {
        return 81;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        comboTemporaryDeadCards.clear();

        if (cards.stream().noneMatch(e -> e.value == '6')) comboTemporaryDeadCards.add(new PokerCard("6x"));
        if (cards.stream().noneMatch(e -> e.value == '7')) comboTemporaryDeadCards.add(new PokerCard("7x"));
        if (cards.stream().noneMatch(e -> e.value == '3')) comboTemporaryDeadCards.add(new PokerCard("3x"));
        if (cards.stream().noneMatch(e -> e.value == '4')) comboTemporaryDeadCards.add(new PokerCard("4x"));
        if (cards.stream().noneMatch(e -> e.value == '5')) comboTemporaryDeadCards.add(new PokerCard("5x"));

        return 1;
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;

    }
}
