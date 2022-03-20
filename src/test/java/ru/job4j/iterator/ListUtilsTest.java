package ru.job4j.iterator;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ListUtilsTest {
    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void removeEven() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 4));
        ListUtils.removeIf(input, n -> n % 2 == 0);
        assertThat(input, is(Arrays.asList(0, 2, 4)));
    }


    @Test
    public void replaceEven() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 4));
        ListUtils.replaceIf(input, n -> n % 2 == 0, 7);
        assertThat(input, is(Arrays.asList(7, 1, 7, 7)));
    }


    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 4));
        ListUtils.removeAll(input, Arrays.asList(7, 2, 4));
        assertThat(input, is(Arrays.asList(0, 1)));
    }


    @Test
    public void whenRemoveAll2() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<Integer> el = new ArrayList<>(Arrays.asList(2, 3, 4));
        ListUtils.removeAll(input, el);
        assertThat(Arrays.asList(0, 1, 5), is(input));
    }
}