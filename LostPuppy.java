import java.util.*;
import java.io.*;

public class LostPuppy {
    public static void main(String[] args) {
        try {
            Scanner console = new Scanner(System.in);
            Scanner input = new Scanner(new File("MazeData3.txt"));
            char[][] maze = getMaze(input);
            console.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        }

    }

    public static char[][] getMaze(Scanner input) {
        char walls = 'X';
        char start = 'S';
        char end = 'E';
        char open = ' ';

        //gets the width and length of maze
        String firstLine = input.nextLine();
        int width = firstLine.length();
        int length = 1;
        while (input.hasNextLine()) {
            length++;
            input.nextLine();
        }
        // Create a new Scanner to start reading again
        // Reset the scanner (input) by re-initializing it (assuming you can do this)
        // or use another method to read input in a single pass
        // For this example, assume we can pass a new Scanner (for demonstration):
        //test to repo
        Scanner input2 = new Scanner(firstLine + "\n" + input);  // Replace this with proper input
        char[][] myMaze = new char[width][length];

        //fills mae
        for (int row = 0; row < myMaze.length; row++) {
            String line = input2.nextLine();
            for (int col = 0; col < myMaze[row].length; col++) {
                myMaze[row][col] = line.charAt(col);
            }
        }

        return myMaze;
    }

    public boolean doMaze (char[][] maze, int row, int col) {
        //calls the starting position and maze
        //call south first

        return false;
    }

}
