package tn.esprit.spring.config;


import java.util.Comparator;
import java.util.Map;

public class ValueComparatorInt implements Comparator<Integer> {
    private final Map<Integer, Double> base;

    public ValueComparatorInt(Map<Integer, Double> base) {
        this.base = base;
    }

    public int compare(Integer a, Integer  b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }

	 

	 
}