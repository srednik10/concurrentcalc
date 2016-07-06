package noidea;

import org.apache.commons.math3.stat.interval.BinomialConfidenceInterval;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by kot on 25.06.16.
 */
public class Tryin {
    public static void main(String[] args) {

        //System.out.println( CombinatoricsUtils.binomialCoefficient(52, 4));

        List<Integer> list = new LinkedList<>();
        System.out.println(list.size());
        list.add(1);
        System.out.println(list.size());
        list.clear();
        System.out.println(list.size());

        Map<Character,Integer> map = new HashMap<>();
        map.put('A',1);
        System.out.println(map.size());
        map.clear();
        System.out.println(map.size());

    }
}
