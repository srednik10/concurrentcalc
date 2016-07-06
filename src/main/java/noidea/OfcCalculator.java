package noidea;

import pokerhands.PokerHand;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by kot on 06.05.16.
 */
public class OfcCalculator {

    private OfcBoard board;
    private List<PokerCard> cards;
    private final Executor exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public OfcCalculator(OfcBoard board, List<PokerCard> cards) {
        this.board = board;
        this.board.addToDeadCards(cards);
        this.cards = cards;
    }

    public OfcBoard calculate() {
        //board.addToDeadCards(cards);
        List<OfcBoard> listOfPossibleBoards = createPossibleBoards();
//        List<OfcBoard> listOfPossibleBoards = new LinkedList<>();
//        listOfPossibleBoards.add(new OfcBoard(this.board));
//        listOfPossibleBoards.get(0).printBoard();





        List<ComboOfHands> combos = new LinkedList<>();
        List<ComboOfHands> combos2 = new LinkedList<>();
        List<ComboOfHands> combos3 = new LinkedList<>();
        long bestValue=0;
        OfcBoard bestBoard = null;

        for(OfcBoard possibleBoard : listOfPossibleBoards) {
            combos.clear();
            combos2.clear();
            combos3.clear();


            for (PokerHand pokerHand : Main.pokerHands) {
                if (pokerHand.canMake(possibleBoard.thirdRow, possibleBoard.getDeadCards()))

                    combos.add(new ComboOfHands(pokerHand, board.getDeadCards(), pokerHand.getProducedDeadCard()));
            }
            for (ComboOfHands combo : combos) {
                //combo.printCombo();
                for (PokerHand pokerHand : Main.pokerHands) {
                    if (pokerHand.getPriority() < combo.getHandInRow3().getPriority() && pokerHand.canMake(possibleBoard.secondRow, combo.getDeadCards())) {
                        combos2.add(new ComboOfHands(combo.getHandInRow3(), pokerHand, combo.getDeadCards(), pokerHand.getProducedDeadCard()));
                    }
                    //if(pokerHand.canMake(list.get(0).thirdRow,list.get(0).deadCards)) combos.add(new ComboOfHands(pokerHand,3,board.getDeadCards()));
                }

            }

            for (ComboOfHands combo2 : combos2) {
                //combo2.printCombo();
                for (PokerHand pokerHand : Main.shortPokerHands) {
                    if (pokerHand.getPriority() < combo2.getHandInRow2().getPriority() && pokerHand.canMake(possibleBoard.firstRow, combo2.getDeadCards())) {
                        combos3.add(new ComboOfHands(combo2.getHandInRow3(), combo2.getHandInRow2(), pokerHand));
                    }
                }
            }
/*
        combos2.stream().forEach(e ->{
            //System.out.println(e.getHandInRow1().getValue(1) + e.getHandInRow2().getValue(2) + e.getHandInRow3().getValue(3));
            e.printCombo();

        });*/


            long boardValue = 0;

            for (ComboOfHands e : combos3) {

                List<PokerCard> temporaryDeadCards = new LinkedList<>(board.deadCards); // just for test, all with deadCards are TODO
                long allCombinations = 1;
                long value = e.getHandInRow1().getValue(1) + e.getHandInRow2().getValue(2) + e.getHandInRow3().getValue(3);
                //System.out.println("value: " + e.getHandInRow1().getValue(1) + e.getHandInRow2().getValue(2) + e.getHandInRow3().getValue(3));
                //now we figure out how many combinations complete every row and multiple them
                //first step: send req to getHandInRow3 with board.thirdRow and deadCards->return int with count
                //System.out.println("row 3 combos: " + e.getHandInRow3().getCountOfHandCombos(list.get(1).thirdRow, temporaryDeadCards));
                allCombinations = allCombinations * e.getHandInRow3().getCountOfHandCombos(possibleBoard.thirdRow, temporaryDeadCards);
                //second step: actualise deadCards, you cannot use cards which were choosen during counting combos(simply use method getUsedCards and join them to temporary deadCards)
                temporaryDeadCards.addAll(e.getHandInRow3().getComboTemporaryDeadCards());
                //third step: send req to getHandInRow2-> mutiply returning int
                //System.out.println("row 2 combos: " + e.getHandInRow2().getCountOfHandCombos(list.get(1).secondRow, temporaryDeadCards));
                allCombinations = allCombinations * e.getHandInRow2().getCountOfHandCombos(possibleBoard.secondRow, temporaryDeadCards);
                //4th: actualise deadCards
                temporaryDeadCards.addAll(e.getHandInRow2().getComboTemporaryDeadCards());
                //5th: send req -> multiply returning int
                //System.out.println("row 1 combos: " + e.getHandInRow1().getCountOfHandCombos(list.get(1).firstRow,temporaryDeadCards));
                allCombinations = allCombinations * e.getHandInRow1().getCountOfHandCombos(possibleBoard.firstRow, temporaryDeadCards);
                //6th: that is all i hope :)

                //System.out.println(value);
                //System.out.println(allCombinations);
                boardValue = boardValue + value * allCombinations;
                //e.printCombo();

            }

            possibleBoard.printBoard();
            if(boardValue > bestValue) {bestBoard = possibleBoard; bestValue = boardValue;}
            //System.out.println(bestValue);
            System.out.println(boardValue);


        }
        return bestBoard;
    }

    private List<OfcBoard> createPossibleBoards() {

        List<OfcBoard> possibleBoards = new LinkedList<>();
        List<OfcBoard> newPossibleBoards = new LinkedList<>();
        possibleBoards.add(board);
        for (PokerCard card : cards) {
            for (OfcBoard board : possibleBoards) {
                for (int i = 1; i < 4; i++) {
                    if (board.firstRow.size() < 3 && i == 1 || board.secondRow.size() < 5 && i == 2 || board.thirdRow.size() < 5 && i == 3) {

                        OfcBoard newBoard = new OfcBoard(board);
                        newBoard.addToRow(card, i);
                        newPossibleBoards.add(newBoard);
                    }
                }

            }

            possibleBoards = new LinkedList<>(newPossibleBoards);
            newPossibleBoards = new LinkedList<>();
        }

        return possibleBoards;
    }


}
