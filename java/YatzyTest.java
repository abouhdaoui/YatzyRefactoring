import org.junit.*;
import static org.junit.Assert.*;

public class YatzyTest {
    @Test
    public void sumDicesWithSameValueThatIsSpecified() {
        Yatzy yatzy = new Yatzy(new int[]{1, 2, 4, 4, 1});
        assertEquals(2,yatzy.sumDiceWithSameValue(yatzy,1));
        assertEquals(8,yatzy.sumDiceWithSameValue(yatzy,4));
        assertEquals(2, yatzy.sumDiceWithSameValue(yatzy, 2));
        assertNotEquals(3, yatzy.sumDiceWithSameValue(yatzy, 2));
    }

    @Test
    public void sumValuesOfDieces() {
        Yatzy yatzy = new Yatzy(new int[] {1,6,5,2,2} );
        assertEquals(16,yatzy.sumValueofDieces(yatzy));
    }

    @Test
    public void smallAndLargeStraight() {
        assertEquals(15, Yatzy.smallAndLargeStraight(new Yatzy(new int[] {3,4,5,6,6})));
        assertEquals(15, Yatzy.smallAndLargeStraight(new Yatzy(new int[] {2,3,2,5,4})));
        assertEquals(15, Yatzy.smallAndLargeStraight(new Yatzy(new int[] {2,1,4,3,1})));
        assertEquals(0, Yatzy.smallAndLargeStraight(new Yatzy(new int[] {1,2,2,4,5})));
        assertEquals(0, Yatzy.smallAndLargeStraight(new Yatzy(new int[] {1,5,5,5,5})));

        assertEquals(20, Yatzy.smallAndLargeStraight(new Yatzy(new int[] {3,2,1,4,5})));
        assertEquals(20, Yatzy.smallAndLargeStraight(new Yatzy(new int[] {2,3,4,5,6})));
        assertEquals(0, Yatzy.smallAndLargeStraight(new Yatzy(new int[] {1,2,2,4,5})));
    }

    @Test public void yatzy_scores_50() {
        assertEquals(50, Yatzy.yatzy(new Yatzy(new int[] {4,4,4,4,4})));
        assertEquals(50, Yatzy.yatzy(new Yatzy(new int[] {6,6,6,6,6} )));
        assertEquals(0, Yatzy.yatzy(new Yatzy(new int[] {6,6,6,6,3})));
    }

    @Test
    public void one_pair() {
        assertEquals(6, Yatzy.oneOrTwo_pair(new Yatzy(new int[] {3,4,3,5,6}),1));
        assertEquals(10, Yatzy.oneOrTwo_pair(new Yatzy(new int[] {5,3,3,3,5}),1));
        assertEquals(12,Yatzy.oneOrTwo_pair(new Yatzy(new int[] {5,3,6,6,5}),1));
        assertEquals(0,Yatzy.oneOrTwo_pair(new Yatzy(new int[] {5,3,6,2,1}),1));
    }

    @Test
    public void two_Pair() {
        assertEquals(0, Yatzy.oneOrTwo_pair(new Yatzy(new int[] {6,6,6,6,6}),2));
        assertEquals(16, Yatzy.oneOrTwo_pair(new Yatzy(new int[] {3,3,5,4,5}),2));
        assertEquals(16, Yatzy.oneOrTwo_pair(new Yatzy(new int[] {3,3,5,5,5}),2));
        assertEquals(6, Yatzy.oneOrTwo_pair(new Yatzy(new int[] {1,1,2,6,2}),2));
        assertEquals(0, Yatzy.oneOrTwo_pair(new Yatzy(new int[] {1,1,2,3,4}),2));
    }

    @Test
    public void three_of_a_kind()
    {
        assertEquals(9, Yatzy.three_of_a_kind(new Yatzy(new int[] {3,3,3,4,5})));
        assertEquals(15, Yatzy.three_of_a_kind(new Yatzy(new int[] {5,3,5,4,5})));
        assertEquals(9, Yatzy.three_of_a_kind(new Yatzy(new int[] {3,3,3,3,5})));
        assertEquals(9, Yatzy.three_of_a_kind(new Yatzy(new int[] {3,3,3,3,3})));
    }

    @Test
    public void four_of_a_kind() {
        assertEquals(12, Yatzy.four_of_a_kind(new Yatzy(new int[] {3,3,3,3,5})));
        assertEquals(20, Yatzy.four_of_a_kind(new Yatzy(new int[] {5,5,5,4,5})));
    }

    @Test
    public void fullHouse() {
        assertEquals(18, Yatzy.fullHouse(new Yatzy(new int[] {6,2,2,2,6})));
        assertEquals(0, Yatzy.fullHouse(new Yatzy(new int[] {2,3,4,5,6})));
    }
}

