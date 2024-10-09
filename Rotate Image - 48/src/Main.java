public class Main {

    public static void rotate(int[][] matrix) {
        // transpose of the matrix
        int row = matrix.length;
        int col = matrix[0].length;
        for(int i = 0; i < row-1; i++){// swap only when j > i this means we don't have to go for the last row
            for(int j = i+1; j < col; j++){ //  and j is started from i+1
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //swap the columns
        int startCol = 0, endCol = col-1;
        while(startCol < endCol){
            //swapping the columns' elements
            for(int i = 0; i < row; i++){
                int temp = matrix[i][startCol];
                matrix[i][startCol] = matrix[i][endCol];
                matrix[i][endCol] = temp;
            }

            // next pair columns
            startCol++;
            endCol--;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate(matrix);
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}