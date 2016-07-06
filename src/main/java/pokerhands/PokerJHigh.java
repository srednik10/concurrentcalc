package pokerhands;

import noidea.PokerCard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by kot on 23.06.16.
 */
public class PokerJHigh implements PokerHand {

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
            if(card.value == 'J' || card.value == '7' || card.value == '8' || card.value == '9' || card.value == 'T')
                if(card.color != 'x')  map.replace(card.color,map.get(card.color) + 1);
        }
        if(map.entrySet().stream().filter(e -> e.getValue() == 0).count() > 0) canMakeOnEmptyBoard = true;
        if(cards.size() != 0){
            color = cards.get(0).color;
            if (cards.stream().allMatch(e -> e.color == color && (e.value == 'J' || e.value == '7' || e.value == '8' || e.value == '9' || e.value == 'T')) && deadCards.stream().noneMatch(e -> e.color == color && (e.value == 'J' || e.value == '7' || e.value == '8' || e.value == '9' || e.value == 'T'))) {
                if(cards.stream().noneMatch(e -> e.value == '8')) producedDeadCards.add(new PokerCard(new StringBuilder().append('8').append(color).toString()));
                if(cards.stream().noneMatch(e -> e.value == '9')) producedDeadCards.add(new PokerCard(new StringBuilder().append('9').append(color).toString()));
                if(cards.stream().noneMatch(e -> e.value == '7')) producedDeadCards.add(new PokerCard(new StringBuilder().append('7').append(color).toString()));
                if(cards.stream().noneMatch(e -> e.value == 'J')) producedDeadCards.add(new PokerCard(new StringBuilder().append('J').append(color).toString()));
                if(cards.stream().noneMatch(e -> e.value == 'T')) producedDeadCards.add(new PokerCard(new StringBuilder().append('T').append(color).toString()));
                return true;
            }
            else return false;
        }else if(canMakeOnEmptyBoard) {
            producedDeadCards.add(new PokerCard(new StringBuilder().append('T').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('9').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('8').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('7').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('J').append('x').toString()));
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
        return "Poker J high";
    }

    @Override
    public int getPriority() {
        return 85;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        comboTemporaryDeadCards.clear();

        if (cards.stream().noneMatch(e -> e.value == '7')) comboTemporaryDeadCards.add(new PokerCard("7x"));
        if (cards.stream().noneMatch(e -> e.value == '8')) comboTemporaryDeadCards.add(new PokerCard("8x"));
        if (cards.stream().noneMatch(e -> e.value == '9')) comboTemporaryDeadCards.add(new PokerCard("9x"));
        if (cards.stream().noneMatch(e -> e.value == 'T')) comboTemporaryDeadCards.add(new PokerCard("Tx"));
        if (cards.stream().noneMatch(e -> e.value == 'J')) comboTemporaryDeadCards.add(new PokerCard("Jx"));

        return 1;
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;

    }
}
