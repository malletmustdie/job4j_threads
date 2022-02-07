package ru.job4j.pool;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class RolColSumTest {

    @Test
    public void whenSumWithNotAsyncMethod() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] expected =
                {
                        new RolColSum.Sums(6, 12),
                        new RolColSum.Sums(15, 15),
                        new RolColSum.Sums(24, 18)
                };
        RolColSum.Sums[] actual = RolColSum.sum(matrix);
        Assert.assertEquals(expected[0].getRowSum(), actual[0].getRowSum());
        Assert.assertEquals(expected[0].getColSum(), actual[0].getColSum());
        Assert.assertEquals(expected[1].getColSum(), actual[1].getColSum());
        Assert.assertEquals(expected[1].getColSum(), actual[1].getColSum());
        Assert.assertEquals(expected[2].getColSum(), actual[2].getColSum());
        Assert.assertEquals(expected[2].getColSum(), actual[2].getColSum());
    }

    @Test
    public void asyncSum() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] expected =
                {
                        new RolColSum.Sums(6, 12),
                        new RolColSum.Sums(15, 15),
                        new RolColSum.Sums(24, 18)
                };
        RolColSum.Sums[] actual = new RolColSum.Sums[0];
        try {
            actual = RolColSum.asyncSum(matrix);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        Assert.assertEquals(expected[0].getRowSum(), actual[0].getRowSum());
        Assert.assertEquals(expected[0].getColSum(), actual[0].getColSum());
        Assert.assertEquals(expected[1].getColSum(), actual[1].getColSum());
        Assert.assertEquals(expected[1].getColSum(), actual[1].getColSum());
        Assert.assertEquals(expected[2].getColSum(), actual[2].getColSum());
        Assert.assertEquals(expected[2].getColSum(), actual[2].getColSum());
    }

}