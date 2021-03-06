package pokerhands;

import noidea.PokerCard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by kot on 23.06.16.
 */
public class PokerAHigh implements PokerHand {

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
            if(card.value == 'J' || card.value == 'Q' || card.value == 'K' || card.value == 'A' || card.value == 'T')
                if(card.color != 'x')  map.replace(card.color,map.get(card.color) + 1);
        }
        if(map.entrySet().stream().filter(e -> e.getValue() == 0).count() > 0) canMakeOnEmptyBoard = true;
        if(cards.size() != 0){
            color = cards.get(0).color;
            if (cards.stream().allMatch(e -> e.color == color && (e.value == 'J' || e.value == 'Q' || e.value == 'K' || e.value == 'A' || e.value == 'T')) && deadCards.stream().noneMatch(e -> e.color == color && (e.value == 'J' || e.value == 'Q' || e.value == 'K' || e.value == 'A' || e.value == 'T')))  {
                if(cards.stream().noneMatch(e -> e.value == 'A')) producedDeadCards.add(new PokerCard(new StringBuilder().append('A').append(color).toString()));
                if(cards.stream().noneMatch(e -> e.value == 'K')) producedDeadCards.add(new PokerCard(new StringBuilder().append('K').append(color).toString()));
                if(cards.stream().noneMatch(e -> e.value == 'Q')) producedDeadCards.add(new PokerCard(new StringBuilder().append('Q').append(color).toString()));
                if(cards.stream().noneMatch(e -> e.value == 'J')) producedDeadCards.add(new PokerCard(new StringBuilder().append('J').append(color).toString()));
                if(cards.stream().noneMatch(e -> e.value == 'T')) producedDeadCards.add(new PokerCard(new StringBuilder().append('T').append(color).toString()));
                return true;
            }
            else return false;
        }else if(canMakeOnEmptyBoard) {
            producedDeadCards.add(new PokerCard(new StringBuilder().append('T').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('J').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('Q').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('K').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('A').append('x').toString()));
            return true;
        }
        else return false;
    }

    @Override
    public int getValue(int row) {
        if(row == 2) return 50;
        else return 25;
    }

    @Override
    public String getName() {
        return "Poker A high";
    }

    @Override
    public int getPriority() {
        return 88;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        comboTemporaryDeadCards.clear();

        if (cards.stream().noneMatch(e -> e.value == 'T')) comboTemporaryDeadCards.add(new PokerCard("Tx"));
        if (cards.stream().noneMatch(e -> e.value == 'J')) comboTemporaryDeadCards.add(new PokerCard("Jx"));
        if (cards.stream().noneMatch(e -> e.value == 'Q')) comboTemporaryDeadCards.add(new PokerCard("Qx"));
        if (cards.stream().noneMatch(e -> e.value == 'K')) comboTemporaryDeadCards.add(new PokerCard("Kx"));
        if (cards.stream().noneMatch(e -> e.value == 'A')) comboTemporaryDeadCards.add(new PokerCard("Ax"));

        return 1;
    }

    @Override
    public List<PokerCard> getComboTemporaryDeadCards() {
        return comboTemporaryDeadCards;

    }
}
