package pokerhands;

import noidea.PokerCard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by kot on 23.06.16.
 */
public class Poker5High implements PokerHand {

    List<PokerCard> producedDeadCards = new LinkedList<>();
    List<PokerCard> comboTemporaryDeadCards = new LinkedList<>();
    Character color;

    @Override
    public boolean canMake(List<PokerCard> cards, List<PokerCard> deadCards) {
        boolean canMakeOnEmptyBoard = false;
        Map<Character, Integer> map = new HashMap<>();
        map.clear();
        producedDeadCards.clear();
        map.put('C', 0);
        map.put('D', 0);
        map.put('H', 0);
        map.put('S', 0);
        for (PokerCard card : deadCards) {
            if (card.value == 'A' || card.value == '2' || card.value == '3' || card.value == '4' || card.value == '5'){
                if(card.color != 'x') map.replace(card.color, map.get(card.color) + 1);
                //System.out.println("replaced");
            }
        }
        if (map.entrySet().stream().filter(e -> e.getValue() == 0).count() > 0) canMakeOnEmptyBoard = true;
        if (cards.size() != 0) {
            color = cards.get(0).color;
            if (cards.stream().allMatch(e -> e.color == color && (e.value == 'A' || e.value == '2' || e.value == '3' || e.value == '4' || e.value == '5')) && deadCards.stream().noneMatch(e -> e.color == color && (e.value == 'A' || e.value == '2' || e.value == '3' || e.value == '4' || e.value == '5'))) {
                if (cards.stream().noneMatch(e -> e.value == '3'))
                    producedDeadCards.add(new PokerCard(new StringBuilder().append('3').append(color).toString()));
                if (cards.stream().noneMatch(e -> e.value == '2'))
                    producedDeadCards.add(new PokerCard(new StringBuilder().append('2').append(color).toString()));
                if (cards.stream().noneMatch(e -> e.value == 'A'))
                    producedDeadCards.add(new PokerCard(new StringBuilder().append('A').append(color).toString()));
                if (cards.stream().noneMatch(e -> e.value == '5'))
                    producedDeadCards.add(new PokerCard(new StringBuilder().append('5').append(color).toString()));
                if (cards.stream().noneMatch(e -> e.value == '4'))
                    producedDeadCards.add(new PokerCard(new StringBuilder().append('4').append(color).toString()));
                return true;
            } else return false;
        } else if (canMakeOnEmptyBoard) {
            producedDeadCards.add(new PokerCard(new StringBuilder().append('5').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('4').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('3').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('2').append('x').toString()));
            producedDeadCards.add(new PokerCard(new StringBuilder().append('A').append('x').toString()));
            return true;
        } else return false;
    }

    @Override
    public int getValue(int row) {
        if (row == 2) return 30;
        else return 15;
    }

    @Override
    public String getName() {
        return "Poker 5 High";
    }

    @Override
    public int getPriority() {
        return 79;
    }

    @Override
    public List<PokerCard> getProducedDeadCard() {
        return producedDeadCards;
    }

    @Override
    public long getCountOfHandCombos(List<PokerCard> cards, List<PokerCard> deadCards) {
        comboTemporaryDeadCards.clear();

        if (cards.stream().noneMatch(e -> e.value == 'A')) comboTemporaryDeadCards.add(new PokerCard("Ax"));
        if (cards.stream().noneMatch(e -> e.value == '2')) comboTemporaryDeadCards.add(new PokerCard("2x"));
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
