package algorithms.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Find a path a rat can travel through a maze.
 *
 * Assume the maze is made up of cells with 4 walls.  Each wall MAY have a door.  The rat has been placed in one of the
 * cells and needs to make its way to a cell which is the 'end' cell.  Limitations: it cannot go through the same cell
 * twice.
 *
 * Algorithm: start exploring each door in turn.  Keep track of the cells it has visited, and don't revisit cells.
 * Recursively do this until the rat gets to the destination.
 *
 * Base case:
 * 1. the rat has reached the destination (end cell)
 *
 * Recursive case:
 * 1. keep visiting cells by passing through doors when they exist
 * 2. do not visit cells if they are present on the current path (we don't want rat to go in circles)
 *
 * The complexity depends on the number of neighbors a cell has, so it's not a simple number
 *
 * Created by cindymc on 8/5/16.
 */
public class RatAndMaze
{
    static boolean findPath(Cell current, List<Cell> path)
    {
        // Add the current cell to the path
        path.add(current);

        // Base case
        if (current.isEnd()) return true;

        // Neighbors are cells with open doors
        for (Cell neighbor : current.getNeighbors())
        {
            // Make sure neighbor cell isn't already on path
            if (!path.contains(neighbor))
            {
                // Create the neighbor path and add in the current path
                List<Cell> neighborPath = new ArrayList<>();
                neighborPath.addAll(path);

                // Recurse to find the destination
                if (findPath(neighbor, neighborPath))
                {
                    // Once here, we have found the destination
                    path.clear();
                    path.addAll(neighborPath);
                    return true;
                }
            }
        }

        return false;  // didn't find it
    }

    static class Cell
    {
        private String id;
        private boolean isEnd = false;
        private List<Cell> neighbors = new ArrayList<>();

        public Cell(String id){this.id = id;}

        public boolean isEnd() {
            return isEnd;
        }

        public List<Cell> getNeighbors() {
            return neighbors;
        }

        public String toString(){return id;}
    }
}
