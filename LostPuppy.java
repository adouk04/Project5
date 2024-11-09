import java.util.*;
import java.io.*;

public class LostPuppy {
    public static void main(String[] args) {
        try {
            Scanner console = new Scanner(System.in);
            Scanner input = new Scanner(new File("MazeData2.txt"));
            char[][] maze = getMaze(input);
            displayMaze(maze);
            console.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        }

    }
    //

    public static char[][] getMaze(Scanner input) {
        List<String> lines = new ArrayList<>();

        while (input.hasNextLine()) {
            String line = input.nextLine();
            lines.add(line);
        }

        int width = lines.get(0).length();
        int length = lines.size();
        input.close();

        char[][] myMaze = new char[length][width];

        for (int row = 0; row < length; row++) {
            String line = lines.get(row);
            for (int col = 0; col < width; col++) {
                myMaze[row][col] = line.charAt(col);
            }
        }
        return myMaze;
    }

    public static void displayMaze(char[][] myMaze) {
        for (int row = 0; row < myMaze.length; row++) {
            for (int col = 0; col < myMaze[row].length; col++) {
                System.out.print(myMaze[row][col]);
            }
            System.out.println();
        }
    }

    public int[][] getStartExitLocation(char[][] maze) {
        int[] startLocation = null;
        int[] endLocation = null;

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                if (maze[row][col] == 'S') {
                    startLocation = new int[]{row, col};
                    maze[row][col] = '*';
                }
                else if (maze[row][col] == 'E') {
                    endLocation = new int[]{row, col};
                    maze[row][col] = ' ';
                }
            }
        }
        return new int[][]{startLocation, endLocation};
    }

    public boolean canMove(char[][] maze, int row, int col) {
        boolean validMove = false;

        if (row >= 0 && col >= 0 &&
                row < maze.length && col < maze[row].length) {
            if (maze[row][col] == ' ') {
                validMove = true;
            }
        }

        return validMove;
    }

    public boolean doMaze (char[][] maze, int row, int col) {
        //calls the starting position and maze
        //call south first

        return false;
    }

}
