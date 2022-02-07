package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static class Sums {

        private final int rowSum;
        private final int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }

    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < sums.length; i++) {
            int rowSum = calcRowSum(matrix, i);
            int colSum = calcColSum(matrix, i);
            sums[i] = new Sums(rowSum, colSum);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int rowSum = getAsyncRowSum(matrix, i).get();
            int cowSum = getAsyncColSum(matrix, i).get();
            sums[i] = new Sums(rowSum, cowSum);
        }
        return sums;
    }

    private static int calcRowSum(int[][] matrix, int index) {
        int row = 0;
        for (int i = 0; i < matrix.length; i++) {
            row += matrix[index][i];
        }
        return row;
    }

    private static int calcColSum(int[][] matrix, int index) {
        int col = 0;
        for (int[] ints : matrix) {
            col += ints[index];
        }
        return col;
    }

    private static CompletableFuture<Integer> getAsyncRowSum(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> calcRowSum(matrix, index));
    }

    private static CompletableFuture<Integer> getAsyncColSum(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> calcColSum(matrix, index));
    }

}
