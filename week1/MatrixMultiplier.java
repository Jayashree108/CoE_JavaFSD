import java.util.concurrent.*;

class MatrixMultiplier {
    private static class Worker implements Runnable {
        private final int row;
        private final int[][] matrixA;
        private final int[][] matrixB;
        private final int[][] result;

        public Worker(int row, int[][] matrixA, int[][] matrixB, int[][] result) {
            this.row = row;
            this.matrixA = matrixA;
            this.matrixB = matrixB;
            this.result = result;
        }

        @Override
        public void run() {
            int colsB = matrixB[0].length;
            int colsA = matrixA[0].length;
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[row][j] += matrixA[row][k] * matrixB[k][j];
                }
            }
        }
    }

    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsB = matrixB[0].length;
        int[][] result = new int[rowsA][colsB];
        ExecutorService executor = Executors.newFixedThreadPool(rowsA);
        
        for (int i = 0; i < rowsA; i++) {
            executor.execute(new Worker(i, matrixA, matrixB, result));
        }
        
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[][] matrixA = { {1, 2}, {3, 4} };
        int[][] matrixB = { {2, 0}, {1, 2} };
        
        int[][] result = multiplyMatrices(matrixA, matrixB);
        
        System.out.println("Result of the multiplication:");
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}
