package noidea;

import pokerhands.PokerHand;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kot on 28.06.16.
 */
public class ComboOfHands {

    PokerHand handInRow1;
    PokerHand handInRow2;
    PokerHand handInRow3;
    List<PokerCard> deadCards = new LinkedList<>();

    public ComboOfHands(PokerHand pokerHand, List<PokerCard> deadCards,List<PokerCard> producedDeadCards){
        this.deadCards.addAll(deadCards);
        this.deadCards.addAll(producedDeadCards);
        handInRow3 = pokerHand;
    }

    public ComboOfHands(PokerHand pokerHand, PokerHand pokerHand2, List<PokerCard> deadCards, List<PokerCard> producedDeadCards){
        this.deadCards.addAll(deadCards);
        this.deadCards.addAll(producedDeadCards);
        handInRow2 = pokerHand2;
        handInRow3 = pokerHand;
    }

    public ComboOfHands(PokerHand pokerHand, PokerHand pokerHand2, PokerHand pokerHand3){
        handInRow3 = pokerHand;
        handInRow2 = pokerHand2;
        handInRow1 = pokerHand3;
    }

    public List<PokerCard> getDeadCards() {
        return deadCards;
    }

    public void addCardsToDeadCards(List<PokerCard> cards){
        deadCards.addAll(cards);
    }

    public PokerHand getHandInRow1() {
        return handInRow1;
    }

    public void setHandInRow1(PokerHand handInRow1) {
        this.handInRow1 = handInRow1;
    }

    public PokerHand getHandInRow2() {
        return handInRow2;
    }

    public void setHandInRow2(PokerHand handInRow2) {
        this.handInRow2 = handInRow2;
    }

    public PokerHand getHandInRow3() {
        return handInRow3;
    }

    public void setHandInRow3(PokerHand handInRow3) {
        this.handInRow3 = handInRow3;
    }

    public void printCombo(){
        if(handInRow1 != null)System.out.println("Hand in row 1: " + handInRow1.getName());
        if(handInRow2 != null)System.out.println("Hand in row 2: " + handInRow2.getName());
        if(handInRow3 != null)System.out.println("Hand in row 3 " + handInRow3.getName());
        System.out.println();


    }
}
