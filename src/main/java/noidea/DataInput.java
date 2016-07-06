package noidea;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by kot on 06.05.16.
 */
public class DataInput {

    private String input;
    private List<PokerCard> cards = new LinkedList<>();

    public DataInput() {
        Scanner scanner = new Scanner(System.in);
        this.input = scanner.nextLine();
        String[] parts = input.split(" ");
        for (String s : parts) cards.add(new PokerCard(s));
    }

    public List<PokerCard> getCards(){
        return cards;
    }



}
