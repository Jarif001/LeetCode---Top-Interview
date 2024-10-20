import java.util.ArrayList;

public class Main {

    public static int countLiveNeighbours(int[][] board, int row, int col){ // row wise calculation
        int count = 0;
        int totalRows = board.length;
        int totalCols = board[0].length;
        // same row
        if(col + 1 < totalCols && board[row][col+1] == 1){
            count++;
        }
        if(col-1 >= 0 && board[row][col-1] == 1){
            count++;
        }
        // previous row
        if(row - 1 >= 0){
            if(col+1 < totalCols && board[row-1][col+1] == 1){
                count++;
            }
            if(board[row-1][col] == 1){
                count++;
            }
            if(col-1 >= 0 && board[row-1][col-1] == 1){
                count++;
            }
        }
        // next row
        if(row + 1 < totalRows){
            if(col - 1 >= 0 && board[row+1][col-1] == 1){
                count++;
            }
            if(board[row+1][col] == 1){
                count++;
            }
            if(col+1 < totalCols && board[row+1][col+1] == 1){
                count++;
            }
        }
        return count;
    }

    public static void gameOfLife(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        // searching for changing indices
        ArrayList<int[]> indices = new ArrayList<>();
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int liveNeighbours = countLiveNeighbours(board, i, j);
                if(board[i][j] == 0){ // becomes live if it has 3 neighbours
                    if(liveNeighbours == 3){ // becomes live
                        indices.add(new int[]{i,j});
                    }
                }
                else{
                    if(liveNeighbours < 2 || liveNeighbours > 3){
                        indices.add(new int[]{i,j});
                    }
                }
            }
        }

        // updating the state
        for(int[] idx : indices){
            board[idx[0]][idx[1]] = 1 - board[idx[0]][idx[1]];
        }

    }

    public static int countLiveNeighbours2(int[][] board, int row, int col){
        int count = 0;
        int totalRows = board.length;
        int totalCols = board[0].length;
        // same row
        if(col + 1 < totalCols){
            if(board[row][col+1] % 10 == 1){
                count++;
            }
        }
        if(col-1 >= 0){
            if(board[row][col-1] % 10 == 1){
                count++;
            }
        }
        // previous row
        if(row - 1 >= 0){
            if(col+1 < totalCols){
                if(board[row-1][col+1] % 10 == 1){
                    count++;
                }
            }
            if(board[row-1][col] % 10 == 1){
                count++;
            }
            if(col-1 >= 0){
                if(board[row-1][col-1] % 10 == 1){
                    count++;
                }
            }
        }
        // next row
        if(row + 1 < totalRows){
            if(col - 1 >= 0){
                if(board[row+1][col-1] % 10 == 1){
                    count++;
                }
            }
            if(board[row+1][col] % 10 == 1){
                count++;
            }
            if(col+1 < totalCols){
                if(board[row+1][col+1] % 10 == 1){
                    count++;
                }
            }
        }
        return count;
    }

    // Solution 2 - Using constant space. But running time is bad comparatively
    // Storing the next jump address and its changing value. Also storing its own value as the last digit (for calc neighbours)
    public static void gameOfLife2(int[][] board) { // using constant space. use the board to store the changes
        int row = board.length;
        int col = board[0].length;
        // searching for changing indices
        int prevRow = 0, prevCol = 0;
        boolean firstChange = false; // if the first element has to be changed
        int firstValue = board[0][0];
        int changesCount = 0; // total changes
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int liveNeighbours = countLiveNeighbours2(board, i, j); // counting live neighbours
                if(board[i][j] == 0){ // becomes live if it has 3 neighbours
                    if(liveNeighbours == 3){ // becomes live - 1 (10000RowCol0 coz row and col can have 2 digits)
                        if(i != 0 || j != 0){
                            // changes
                            String currentRow = String.format("%02d", i);
                            String currentCol = String.format("%02d", j);
                            String ownValue = String.valueOf(board[prevRow][prevCol]); // cell's own value for calculating neighbours
                            // Storing the jump address and '1' for making it 1
                            board[prevRow][prevCol] = Integer.parseInt("1" + currentRow + currentCol + ownValue);
                            prevRow = i;
                            prevCol = j;
                            changesCount++;
//                            System.out.println("Change to 1 - " + i + ", " + j + ",,, coz neighbours - " + liveNeighbours);
                        }
                        else{// 1st element
                            firstChange = true;
                        }
                    }
                }
                else{
                    if(liveNeighbours < 2 || liveNeighbours > 3){ // it will be dead - 0 (20000RowCol1 something)
                        if(i != 0 || j != 0){
                            // changes
                            String currentRow = String.format("%02d", i);
                            String currentCol = String.format("%02d", j);
                            String ownValue = String.valueOf(board[prevRow][prevCol]);; // for calc neighbours
                            // string the jump address and '2' for make it zero
                            board[prevRow][prevCol] = Integer.parseInt("2" + currentRow + currentCol + ownValue);
                            prevRow = i;
                            prevCol = j;
                            changesCount++;
//                            System.out.println("Change to 0 - " + i + ", " + j + ",,, coz neighbours - " + liveNeighbours);
                        }
                        else{ // 1st elem
                            firstChange = true;
                        }
                    }
                }
            }
        }

        // updating the state
        int changesMade = 0;
        if(board[0][0] > 1){
            board[0][0] = board[0][0] / 10; // last appended own value digit is removed
        }
        int currCol = board[0][0] % 100;
        int currRow = (board[0][0] / 100) % 100;
        int currentValue = board[0][0] / 20000;
        while(changesMade < changesCount){
            board[currRow][currCol] = board[currRow][currCol] / 10; // last digit is removed as it was its own value
            int nextCol = board[currRow][currCol] % 100;
            int nextRow = (board[currRow][currCol] / 100) % 100;
            int nextValue = board[currRow][currCol] / 20000;
//            System.out.println("Changes gonna be for row - " + currRow + ", col - " + currCol);
//            System.out.println("Current value - " + board[currRow][currCol]);
            board[currRow][currCol] = 1 - currentValue; // flip the value
            currRow = nextRow;
            currCol = nextCol;
            currentValue = nextValue;
            changesMade++;
        }
        if(firstChange){
            board[0][0] = 1 - firstValue;
        }
        else{
            board[0][0] = firstValue;
        }

    }

    // Solution 3 - constant space and double loop
    public static int countLiveNeighboursLoop(int[][] board, int row, int col){ // Using loops to calculate neighbours
        int count = 0;
        int totalRows = board.length;
        int totalCols = board[0].length;
        for(int i = Math.max(row-1,0); i <= Math.min(row+1, totalRows-1); i++){
            for(int j = Math.max(0, col-1); j <= Math.min(totalCols-1, col+1); j++){
                if(board[i][j] > 0){
                    count++;
                }
            }
        }
        if(board[row][col] > 0){ // Itself is not a neighbour
            count--;
        }
        return count;
    }

    public static void gameOfLife3(int[][] board){
        int row = board.length;
        int col = board[0].length;
        // searching for changing indices
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int liveNeighbours = countLiveNeighboursLoop(board, i, j);
//                System.out.println("Row - " + i + ", Col - " + j + ": Live neighbours - " + liveNeighbours);
                if(board[i][j] == 0){ // becomes live if it has 3 neighbours
                    if(liveNeighbours == 3){ // becomes live
                        board[i][j] = -1;
                    }
                }
                else{
                    if(liveNeighbours < 2 || liveNeighbours > 3){
                        board[i][j] = 2;
                    }
                }
            }
        }

        // updating the state
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(board[i][j] == -1){
                    board[i][j] = 1;
                }
                else if(board[i][j] == 2){
                    board[i][j] = 0;
                }
            }
        }

    }


    public static void main(String[] args) {
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        int[][] board2 = {{1,1}, {1,0}};
        int[][] board3 = {{0,0,0,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,1,0,0},{0,0,0,0,0}};
        int[][] myBoard = board3;
        gameOfLife3(myBoard);
        for(int i = 0; i < myBoard.length; i++){
            for(int j = 0; j < myBoard[0].length; j++){
                System.out.print(myBoard[i][j] + "\t");
            }
            System.out.println();
        }
    }
}