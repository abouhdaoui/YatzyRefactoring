import java.util.*;
import java.util.stream.Collectors;

public class Yatzy {
    private List<Integer> dices = new ArrayList<>();
    private final static long PAIR_VALUE = 2L;
    private final static long BRELAN_VALUE = 3L;

    public Yatzy(int[] dices) {
        if (Arrays.stream(dices).anyMatch(n -> n <= 6 && n > 0 )) {
            this.dices.add(dices[0]);
            this.dices.add(dices[1]);
            this.dices.add(dices[2]);
            this.dices.add(dices[3]);
            this.dices.add(dices[4]);
        }
    }

    public List<Integer> getDices() {
        return dices;
    }

    /*
    This method replace { one(), two(), three(), four(), fives(), six() } methods in Legacy class
     */
    public int sumDiceWithSameValue(Yatzy yatzy, int whichNumberToCount) {
        int score = 0;
        if (whichNumberToCount > 0 && whichNumberToCount <= 6) {
            score = yatzy.getDices().stream()
                    .filter(d -> d.equals(whichNumberToCount)).mapToInt(Integer::intValue).sum();
        }
        return score;
    }

    /*
    This class replace chance() method in legacy class
     */
    public int sumValueofDieces(Yatzy yatzy) {
        int score = 0;
        score = yatzy.getDices().stream().mapToInt(Integer::intValue).sum();
        return score;
    }

    public static int smallAndLargeStraight(Yatzy yatzy) {
        List<Integer> dices = yatzy.getDices();
        dices.sort(Comparator.naturalOrder());

        int notSuccessiveCpt = 0;
        for (int index = 1; index < dices.size(); index++) {
            if (dices.get(index - 1) + 1 != dices.get(index)) notSuccessiveCpt++;
        }
        if (notSuccessiveCpt == 0) return 20;
        if (notSuccessiveCpt == 1) return 15;
        return 0;
    }

    public static int yatzy(Yatzy yatzy) {
        Map<Integer, Long> countDuplicatesValue = groupByValue(yatzy);
        if (countDuplicatesValue.size() == 1 && countDuplicatesValue.containsValue(5L)) return 50;
        return 0;
    }

    /*
    Method takes into account cases : one_pair, two_pair
     */
    public static int oneOrTwo_pair(Yatzy yatzy, int numberOfPairExpected) {
        Map<Integer, Long> groupByDuplicateValue = groupByValue(yatzy);

        Map<Integer, Long> collect = groupByDuplicateValue.entrySet()
                .stream()
                .filter(elem -> elem.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        int res = 0;
        if (!collect.isEmpty()) {
            Optional<Map.Entry<Integer, Long>> maxEntry = collect.entrySet()
                    .stream().max(Comparator.comparing(Map.Entry::getKey));

            if (numberOfPairExpected == 1) {
                res = maxEntry.get().getKey() * 2;
                return res;
            }
            if (collect.size() == 2 && numberOfPairExpected == 2) {
                res = collect.keySet().stream().mapToInt(elem -> elem).sum() * 2;
                return res;
            }
        }
        return res;
    }

    public static int three_of_a_kind(Yatzy yatzy) {
        Map<Integer, Long> groupByDuplicateValue = groupByValue(yatzy);
        long res = groupByDuplicateValue.keySet().stream().filter(x -> groupByDuplicateValue.get(x) > 2)
                .collect(Collectors.toList()).get(0) * 3;
        if (res != 0) return (int) res;
        return 0;
    }

    public static int four_of_a_kind(Yatzy yatzy) {
        Map<Integer, Long> groupByDuplicateValue = groupByValue(yatzy);
        long res = groupByDuplicateValue.keySet().stream().filter(x -> groupByDuplicateValue.get(x) > 3)
                .collect(Collectors.toList()).get(0) * 4;
        if (res != 0) return (int) res;
        return 0;
    }

    public static int fullHouse(Yatzy yatzy) {
        Map<Integer, Long> groupByDuplicateValue = groupByValue(yatzy);
        if (groupByDuplicateValue.size() == 2 &&
                (groupByDuplicateValue.containsValue(PAIR_VALUE) && groupByDuplicateValue.containsValue(BRELAN_VALUE))) {
            return 18;
        }
        return 0;
    }

    private static Map<Integer, Long> groupByValue(Yatzy yatzy) {
        List<Integer> dices;
        dices = yatzy.getDices();
        Map<Integer, Long> groupByDuplicateValue = dices.stream().collect(Collectors.groupingBy(k -> k, Collectors.counting()));
        return groupByDuplicateValue;
    }

}

