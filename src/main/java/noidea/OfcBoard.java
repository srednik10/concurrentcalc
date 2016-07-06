package noidea;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kot on 06.05.16.
 */
public class OfcBoard {

    List<PokerCard> deadCards = new LinkedList<>(); //todo
    List<PokerCard> firstRow = new LinkedList<>();
    List<PokerCard> secondRow = new LinkedList<>();
    List<PokerCard> thirdRow = new LinkedList<>();

    public OfcBoard() {

    }

    public void addToDeadCards(List<PokerCard> cards){
        deadCards.addAll(cards);
    }

    public void addToDeadCards(PokerCard card){
        deadCards.add(card);
    }

    public List<PokerCard> getDeadCards() {
        return deadCards;
    }

    public OfcBoard(OfcBoard board) {
        this.deadCards  = board.deadCards;
        this.firstRow = new LinkedList<>(board.firstRow);
        this.secondRow = new LinkedList<>(board.secondRow);
        this.thirdRow = new LinkedList<>(board.thirdRow);;
    }


    public void addToRow(PokerCard card, int numberOfRow) {
        switch (numberOfRow) {
            case 1:

                //System.out.println("dodano do rzedu 1 " + card);
                firstRow.add(card);
                break;
            case 2:
                //System.out.println("dodano do rzedu 2 " + card);
                secondRow.add(card);
                break;
            case 3:
                //System.out.println("dodano do rzedu 3 " + card);
                thirdRow.add(card);
                break;
            default:
                System.out.println("cannot add card to the row number" + numberOfRow);
                break;
        }
    }

    public void printBoard() {
        System.out.print("First row :");
        System.out.print("   ");
        for (PokerCard card : firstRow) System.out.print(card.toString() + " ");
        System.out.println();
        System.out.print("Second row:");
        for (PokerCard card : secondRow) System.out.print(card.toString() + " ");
        System.out.println();
        System.out.print("Third row :");
        for (PokerCard card : thirdRow) System.out.print(card.toString() + " ");
        System.out.println();
        System.out.println();

    }

}
