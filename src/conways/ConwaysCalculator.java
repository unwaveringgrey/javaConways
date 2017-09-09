package conways;

public class ConwaysCalculator {

    //Iterations takes an $initial two dimensional array representation of a Conway's plane and
    //returns what it would look like for an arbitrary number of $iterations from now.
    //The iterations value is optional and defaults to 1.
    public boolean[][] iterate(boolean[][] $initial) {
        return iterate($initial, 1);
    }

    //Recursive function. Calculates the next state of the conway's plane provided in $initial.
    //If $iterations > 1, it decrements it and calls itself again, calculating the next state.
    public boolean[][] iterate(boolean[][] $initial, int $iterations) {
        int $rows = $initial.length;
        int $columns = 0;
        if ($rows > 0) {
            $columns = $initial[0].length;
        }
        int[][] $live_neighbor_count = new int[$rows][$columns];

        for (int $row = 0; $row <$rows; $row++)
        {
            for (int $column = 0; $column <$columns; $column++)
            {
                //if this cell is alive, update the living count on it's neighbors
                if ($initial[$row][$column]) {
                    if ($row - 1 >= 0 && $column - 1 >= 0) {
                        $live_neighbor_count[$row - 1][$column - 1]++;
                    }
                    if ($row - 1 >= 0) {
                        $live_neighbor_count[$row - 1][$column]++;
                    }
                    if ($row - 1 >= 0 && $column + 1 < $columns) {
                        $live_neighbor_count[$row - 1][$column + 1]++;
                    }

                    if ($column - 1 >= 0) {
                        $live_neighbor_count[$row][$column - 1]++;
                    }
                    if ($column + 1 < $columns) {
                        $live_neighbor_count[$row][$column + 1]++;
                    }


                    if ($row + 1 < $rows && $column - 1 >= 0) {
                        $live_neighbor_count[$row + 1][$column - 1]++;
                    }
                    if ($row + 1 < $rows) {
                        $live_neighbor_count[$row + 1][$column]++;
                    }
                    if ($row + 1 < $rows && $column + 1 < $columns) {
                        $live_neighbor_count[$row + 1][$column + 1]++;
                    }
                }

            }
        }

        //update initial with the new state
        $initial = calculateNextState( $initial, $live_neighbor_count);

        //this is where the recursion lives
        if ($iterations > 1) {
            $initial = iterate($initial, $iterations - 1);
        }

        return $initial;
    }

    //takes a starting state and a count of the live neighbors for each of the cells in the starting state
    //uses that data to calculate the next state of each of the cells in the starting state
    //returns a 2d array of that data
    private boolean[][] calculateNextState(boolean[][] $initial, int[][] $live_neighbor_count) {
        for (int $row = 0; $row <$initial.length; $row++)
        {
            for (int $column = 0; $column <$initial[$row].length; $column++)
            {
                if ($initial[$row][$column]) {
                    if ($live_neighbor_count[$row][$column] < 2 || $live_neighbor_count[$row][$column] > 3) {
                        $initial[$row][$column] = false;
                    }
                } else {
                    if ($live_neighbor_count[$row][$column] == 3) {
                        $initial[$row][$column] = true;
                    }
                }
            }
        }

        return $initial;
    }



}