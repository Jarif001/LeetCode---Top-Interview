import java.util.ArrayList;

public class Main {

    public static void setZeroes(int[][] matrix) { // using arraylist to store the row and col of zero
        int matRow = matrix.length;
        int matCol = matrix[0].length;
        // searching for zeroes
        ArrayList<int[]> indices = new ArrayList<>();
        for(int i = 0; i < matRow; i++){
            for(int j = 0; j < matCol; j++){
                if(matrix[i][j] == 0){
                    indices.add(new int[]{i, j});
                }
            }
        }
        // making row col zero
        for(int[] idx : indices){
            int row = idx[0];
            int col = idx[1];
            for(int i = 0; i < matRow; i++){
                matrix[i][col] = 0;
            }
            for(int i = 0; i < matCol; i++){
                matrix[row][i] = 0;
            }
        }
    }

    public static void setZeroes2(int[][] matrix) { // using row and column flag to detect zero row-col
        int matRow = matrix.length;
        int matCol = matrix[0].length;
        // searching for zeroes
        int[] rowFlag = new int[matRow];
        int[] colFlag = new int[matCol];
        for(int i = 0; i < matRow; i++){
            for(int j = 0; j < matCol; j++){
                if(matrix[i][j] == 0){
                    rowFlag[i] = 1;
                    colFlag[j] = 1;
                }
            }
        }
        // making rows zero
        for(int i = 0; i < matRow; i++){
            if(rowFlag[i] == 1){
                for(int j = 0; j < matCol; j++){
                    matrix[i][j] = 0;
                }
            }
        }
        // making cols zero
        for(int i = 0; i < matCol; i++){
            if(colFlag[i] == 1){
                for(int j = 0; j < matRow; j++){
                    matrix[j][i] = 0;
                }
            }
        }
    }

    public static void setZeroes3(int[][] matrix) { // using row and column flag to detect zero row-col
        int matRow = matrix.length;
        int matCol = matrix[0].length;
        // searching for zeroes
        boolean firstRow = false;
        boolean firstCol = false;
        for(int i = 0; i < matRow; i++){
            for(int j = 0; j < matCol; j++){
                if(matrix[i][j] == 0){
                    if(i == 0){
                        firstRow = true;
                    }
                    if(j == 0){
                        firstCol = true;
                    }
                    matrix[i][0] = 0; // placing zero to the 1st column of that row
                    matrix[0][j] = 0; // placing zero to the 1st row of that column
                }
            }
        }
        // making the rows and cols zero except the first row and first col (it is handled later)
        // making rows zero
        for(int i = 1; i < matRow; i++){
            if(matrix[i][0] == 0){
                for(int j = 0; j < matCol; j++){
                    matrix[i][j] = 0;
                }
            }
        }
        // making cols zero
        for(int i = 1; i < matCol; i++){
            if(matrix[0][i] == 0){
                for(int j = 0; j < matRow; j++){
                    matrix[j][i] = 0;
                }
            }
        }
        // handling first row and first col
        if(firstRow){
            for(int i = 0; i < matCol; i++){
                matrix[0][i] = 0;
            }
        }
        if(firstCol){
            for(int i = 0; i < matRow; i++){
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] mat = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes3(mat);
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }
    }
}