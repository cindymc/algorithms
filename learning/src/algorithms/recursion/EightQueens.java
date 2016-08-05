package algorithms.recursion;

/**
 * Place 8 queens on a chessboard so they don't kill each other.
 *
 * Queens can move in rows, columns, or diagonals.
 *
 * Place one queen at a time, one on each row, or one on each column.  Check at every step to see if the queen placed is
 * safe, and if the remaining queens can be placed with that configuration.  Recursively do this until the right
 * position for all queens has been found.
 *
 * Base case:
 * 1. the queens have all been placed
 * 2. we're beyond the boundary of the chessboard
 *
 * Recursive case:
 * 1. keep placing queens in different positions of the same row or column
 * 2. check wither the remaining queens can be successfully placed
 *
 * Complexity is O(N!), as we're trying every possible position for the queens
 *
 * Created by cindymc on 8/5/16.
 */
public class EightQueens
{
    static final int N = 8;   // 8 rows/cols
    static int[][] chessBoard = new int[8][8];

    public static void main(String [] args)
    {
        // Place a queen in each column
        // We know the solution is tractable, and that one solution has just one queen in each column.
    }

    // Recursive method
    public static boolean placeQueen(int[][] chessBoard, int col)
    {
        // Base case: we go beyond the number of columns, so we've placed the last queen
        if (col >= N) return true;

        for (int row=0; row<N; row++)
        {
            chessBoard[row][col] = 1;   // Place a queen
            if (isSafe(chessBoard, row, col))
            {
                // Even if it's safe, we need to make sure remaining queens can be placed with this configuration
                // Wow!
                if (placeQueen(chessBoard, col+1))
                {
                    return true;
                }
                // If not, then remove the queen from the current position and try a new one
                chessBoard[row][col] = 0;
            }
        }

        // Unsuccessful try
        return false;
    }

    // 1 in a cell represents the presence of a queen in that cell
    private static boolean isSafe(int[][] chessBoard, int row, int col)
    {
        if (!isColumnSafe(chessBoard, col)) return false;
        if (!isRowSafe(chessBoard, row)) return false;
        if (!isLeftDiagonalSafe(chessBoard, row, col)) return false;
        if (!isRightDiagonalSafe(chessBoard, row, col)) return false;
        return true;
    }

    private static boolean isColumnSafe(int[][] chessBoard, int col)
    {
        int colSum = 0;
        // Check to see whether there is another queen on the column
        for (int r=0; r<N; r++)
        {
            colSum += chessBoard[r][col];
        }
        return colSum == 1;   // 1 and not 0, because we've already placed the queen in this position?
    }

    private static boolean isRowSafe(int[][] chessBoard, int row)
    {
        int rowSum = 0;
        // Check to see whether there is another queen on the column
        for (int c=0; c<N; c++)
        {
            rowSum += chessBoard[row][c];
        }
        return rowSum == 1;
    }

    private static boolean isLeftDiagonalSafe(int[][] chessBoard, int row, int col)
    {
        int ldSum = 0;
        int r = 0;
        int c = 0;

        // Find the initial position tracing the left diagonal from the first cell in the line of the placed queen
        if (row > col)
        {
            r = row - col;
        }
        else
        {
            c = col - row;
        }

        // Visit each cell in the diagonal and check that there is exactly one queen in that diagonal
        while (r<N && c<N)
        {
            ldSum += chessBoard[r++][c++];    // increment rows and cols to traverse left giagonal
        }
        return ldSum == 1;
    }

    // Why is this code different?
    private static boolean isRightDiagonalSafe(int[][] chessBoard, int row, int col)
    {
        int rdSum = 0;
        int r = 0;
        int c = 7;

        if (row+col < N)
        {
            c = Math.min(row+col, N-1);
        }
        else
        {
            r = (row+col) % (N-1);
        }
        while (r<N && c>=0)
        {
            rdSum += chessBoard[r++][c--];   // increment rows and decrement cols to traverse right diagonal.
        }
        return rdSum == 1;
    }
}
