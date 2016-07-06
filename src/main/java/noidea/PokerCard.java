package noidea;

/**
 * Created by kot on 06.05.16.
 */
public class PokerCard {
    public char value;
    public char color;

    public PokerCard(String s){
        this.value = s.charAt(0);
        this.color = s.charAt(1);
    }

    public String toString(){
        return new StringBuilder().append(value).append(color).toString();
    }
}
