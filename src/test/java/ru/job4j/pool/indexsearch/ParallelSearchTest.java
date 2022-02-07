package ru.job4j.pool.indexsearch;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParallelSearchTest {

    @Test
    public void whenSearchIndexOnArrayWithNumbers() {
        Integer[] arr = {5, 4, 7, 1, 2, 6, 3, 9, 10, 8, 11};
        Integer expected = 8;
        Integer actual = ParallelSearch.search(arr, 10);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whenSearchIndexOnArrayWithStrings() {
        String[] arr = {"5", "4", "7", "1", "2", "6", "3", "9", "10", "8", "11"};
        Integer expected = 6;
        Integer actual = ParallelSearch.search(arr, "3");
        Assert.assertEquals(expected, actual);
    }

}