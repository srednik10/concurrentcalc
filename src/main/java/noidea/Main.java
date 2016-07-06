package noidea;

import pokerhands.*;
import java.util.*;
import java.util.List;

/**
 * Created by kot on 06.05.16.
 */
public class Main {
    static List<PokerHand> pokerHands = new LinkedList<>();
    static List<PokerHand> shortPokerHands = new LinkedList<>();


    public static void main(String[] args) {

        //init
        pokerHands.add(new Nothing());
        pokerHands.add(new PairOf7s());
        pokerHands.add(new PairOf8s());
        pokerHands.add(new PairOf9s());
        pokerHands.add(new PairOfTs());
        pokerHands.add(new PairOfJs());
        pokerHands.add(new PairOfQs());
        pokerHands.add(new PairOfKs());
        pokerHands.add(new PairOfAs());
        pokerHands.add(new TwoPairs3s());
        pokerHands.add(new TwoPairs4s());
        pokerHands.add(new TwoPairs5s());
        pokerHands.add(new TwoPairs6s());
        pokerHands.add(new TwoPairs7s());
        pokerHands.add(new TwoPairs8s());
        pokerHands.add(new TwoPairs9s());
        pokerHands.add(new TwoPairsTs());
        pokerHands.add(new TwoPairsJs());
        pokerHands.add(new TwoPairsQs());
        pokerHands.add(new TwoPairsKs());
        pokerHands.add(new TwoPairsAs());
        pokerHands.add(new ThreeOfKind2s());
        pokerHands.add(new ThreeOfKind3s());
        pokerHands.add(new ThreeOfKind4s());
        pokerHands.add(new ThreeOfKind5s());
        pokerHands.add(new ThreeOfKind6s());
        pokerHands.add(new ThreeOfKind7s());
        pokerHands.add(new ThreeOfKind8s());
        pokerHands.add(new ThreeOfKind9s());
        pokerHands.add(new ThreeOfKindTs());
        pokerHands.add(new ThreeOfKindJs());
        pokerHands.add(new ThreeOfKindQs());
        pokerHands.add(new ThreeOfKindKs());
        pokerHands.add(new ThreeOfKindAs());
        pokerHands.add(new Straight5High());
        pokerHands.add(new Straight6High());
        pokerHands.add(new Straight7High());
        pokerHands.add(new Straight8High());
        pokerHands.add(new Straight9High());
        pokerHands.add(new StraightTHigh());
        pokerHands.add(new StraightJHigh());
        pokerHands.add(new StraightQHigh());
        pokerHands.add(new StraightKHigh());
        pokerHands.add(new StraightAHigh());
        pokerHands.add(new Flush7High());
        pokerHands.add(new Flush8High());
        pokerHands.add(new Flush9High());
        pokerHands.add(new FlushTHigh());
        pokerHands.add(new FlushJHigh());
        pokerHands.add(new FlushQHigh());
        pokerHands.add(new FlushKHigh());
        pokerHands.add(new FlushAHigh());
        pokerHands.add(new FullHouse2s());
        pokerHands.add(new FullHouse3s());
        pokerHands.add(new FullHouse4s());
        pokerHands.add(new FullHouse5s());
        pokerHands.add(new FullHouse6s());
        pokerHands.add(new FullHouse7s());
        pokerHands.add(new FullHouse8s());
        pokerHands.add(new FullHouse9s());
        pokerHands.add(new FullHouseTs());
        pokerHands.add(new FullHouseJs());
        pokerHands.add(new FullHouseQs());
        pokerHands.add(new FullHouseKs());
        pokerHands.add(new FullHouseAs());
        pokerHands.add(new FourOfKind2s());
        pokerHands.add(new FourOfKind3s());
        pokerHands.add(new FourOfKind4s());
        pokerHands.add(new FourOfKind5s());
        pokerHands.add(new FourOfKind6s());
        pokerHands.add(new FourOfKind7s());
        pokerHands.add(new FourOfKind8s());
        pokerHands.add(new FourOfKind9s());
        pokerHands.add(new FourOfKindTs());
        pokerHands.add(new FourOfKindJs());
        pokerHands.add(new FourOfKindQs());
        pokerHands.add(new FourOfKindKs());
        pokerHands.add(new FourOfKindAs());
        pokerHands.add(new Poker5High());
        pokerHands.add(new Poker6High());
        pokerHands.add(new Poker7High());
        pokerHands.add(new Poker8High());
        pokerHands.add(new Poker9High());
        pokerHands.add(new PokerTHigh());
        pokerHands.add(new PokerJHigh());
        pokerHands.add(new PokerQHigh());
        pokerHands.add(new PokerKHigh());
        pokerHands.add(new PokerAHigh());

        shortPokerHands.add(new ShortNothing());
        shortPokerHands.add(new ShortPairOf6s());
        shortPokerHands.add(new ShortPairOf7s());
        shortPokerHands.add(new ShortPairOf8s());
        shortPokerHands.add(new ShortPairOf9s());
        shortPokerHands.add(new ShortPairOfTs());
        shortPokerHands.add(new ShortPairOfJs());
        shortPokerHands.add(new ShortPairOfQs());
        shortPokerHands.add(new ShortPairOfKs());
        shortPokerHands.add(new ShortPairOfAs());
        shortPokerHands.add(new ShortThreeOfKind2s());
        shortPokerHands.add(new ShortThreeOfKind3s());
        shortPokerHands.add(new ShortThreeOfKind4s());
        shortPokerHands.add(new ShortThreeOfKind5s());
        shortPokerHands.add(new ShortThreeOfKind6s());
        shortPokerHands.add(new ShortThreeOfKind7s());
        shortPokerHands.add(new ShortThreeOfKind8s());
        shortPokerHands.add(new ShortThreeOfKind9s());
        shortPokerHands.add(new ShortThreeOfKindTs());
        shortPokerHands.add(new ShortThreeOfKindJs());
        shortPokerHands.add(new ShortThreeOfKindQs());
        shortPokerHands.add(new ShortThreeOfKindKs());
        shortPokerHands.add(new ShortThreeOfKindAs());


        //main program

        OfcBoard board = new OfcBoard();
        List<PokerCard> cards = new DataInput().getCards();

/*
        board.addToRow(new PokerCard("QD"), 1);
        board.addToRow(new PokerCard("JD"), 1);
        board.addToRow(new PokerCard("TD"), 3);
        board.addToRow(new PokerCard("AS"), 2);
        board.addToRow(new PokerCard("KS"), 3);*/
        //board.printBoard();

        //System.out.println(cards.size());
        board = new OfcCalculator(board, cards).calculate();
        board.printBoard();
        for(int i=0; i < 8 ; i++) {
            cards = new DataInput().getCards();
            board = new OfcCalculator(board, cards).calculate();
            board.printBoard();
        }

    /*    Scanner scanner = new Scanner(System.in);
        int tmp = 0;
        while (true) {
            try {
                System.out.println(scanner.nextInt());

            } catch (InputMismatchException e) {
                System.out.println("err");
                //System.out.println(scanner.next());
                //System.out.println(scanner);
            }

        }
  */      //System.out.println(tmp);

    }
}