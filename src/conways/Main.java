package conways;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //get the initial state
        boolean[][] $initial = loadFile();
        //initialize the calculator class
        ConwaysCalculator $calculator = new ConwaysCalculator();
        //iterate the initial state
        boolean[][] $iterated = $calculator.iterate($initial);
        //output the solution
        conwaysOutput($iterated);
    }

    //Takes a file path and loads the data from that file path
    //returns a 2d array of that data
    private static boolean[][] loadFile() {
        return loadFile("");
    }

    //Takes a file path and loads the data from that file path
    //returns a 2d array of that data
    private static boolean[][] loadFile(String $file) {
        //gets the file path if no path was provided
        if($file.equals(""))
        {
            $file = getFilePath();
        }

        //setup an array list for the data
        ArrayList<boolean[]> $input = new ArrayList<>();

        //try to read the file and populate the array list
        try{
            String $line = "";
            BufferedReader $br = new BufferedReader(new FileReader($file));
            while( ($line = $br.readLine())!=null) {
                $input.add(lineToCells($line));
            }
            //convert the arraylist of boolean arrays into a 2d boolean array
            boolean[][] $input_arrayed = $input.toArray(new boolean[][]{});

            return $input_arrayed;

            //just catch all of the exceptions
            //this could be more robust, but meh...
        } catch(Exception $e) {
            System.out.println("Error reading file.");
        }
        //if it fails just return a completely empty 2d boolean array
        return new boolean[][]{};
    }

    //takes a string (presumably from a file) and converts it into the apropriate true/false values
    //returns a boolean array
    //accepts 0/1 values
    private static boolean[] lineToCells(String $line) {
        char[] $split = $line.trim().toCharArray();
        boolean[] $bool_array = new boolean[$split.length];

        for(int $i=0;$i<$split.length;$i++)
        {
            char $curr = $split[$i];
            //Another conditional would be needed here to allow for null areas in non-rectangular fields.
            if($curr=='1')
            {
                $bool_array[$i] = true;
            } else {
                $bool_array[$i] = false;
            }
        }

        return $bool_array;
    }

    //outputs the data
    private static void conwaysOutput(boolean[][] $iterated) {
        for (int $row = 0; $row <$iterated.length; $row++) {
            for (int $column = 0; $column <$iterated[$row].length; $column++) {
                if ($iterated[$row][$column]) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }

    //queries the user for a file path
    private static String getFilePath() {
        System.out.println("Enter file path for seed file:");
        Scanner input_scanner = new Scanner(System.in);
        String $file = input_scanner.nextLine();
        return $file;
    }

}
