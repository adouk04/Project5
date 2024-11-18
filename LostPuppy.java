import java.util.*;
import java.io.*;

public class LostPuppy {
    public static void main(String[] args) {
        try  {
            Scanner input = new Scanner(new File("MazeData2.txt"));
            char[][] maze = getMaze(input);
            int[][] locations = getStartExitLocation(maze);
            int startX = locations[0][0];
            int startY = locations[0][1];
            int endX = locations[1][0];
            int endY = locations[1][1];

            // Check if a path exists
            if (doMaze(maze, startX, startY, endX, endY)) {
                displayMaze(maze);
            } else {
                System.out.println("No path found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    // Reads the maze data into a 2D character array
    public static char[][] getMaze(Scanner input) {
        List<String> lines = new ArrayList<>();
        while (input.hasNextLine()) {
            lines.add(input.nextLine());
        }

        int rows = lines.size();
        int cols = lines.get(0).length();
        char[][] maze = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            maze[row] = line.toCharArray(); // Convert each line into a character array
        }

        return maze;
    }

    // Displays the maze
    public static void displayMaze(char[][] maze) {
        for (char[] row : maze) {
            System.out.println(new String(row));
        }
    }

    // Finds the start and end positions in the maze
    public static int[][] getStartExitLocation(char[][] maze) {
        int[] startLocation = new int[2];
        int[] endLocation = new int[2];

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                if (maze[row][col] == 'S') {
                    startLocation[0] = row;
                    startLocation[1] = col;
                    maze[row][col] = '*'; // Mark the start location as visited
                } else if (maze[row][col] == 'E') {
                    endLocation[0] = row;
                    endLocation[1] = col;
                }
            }
        }

        return new int[][]{startLocation, endLocation};
    }

    // Checks if the movement is valid
    public static boolean canMove(char[][] maze, int row, int col) {
        return row >= 0 && col >= 0 && row < maze.length && col < maze[row].length && (maze[row][col] == ' ' || maze[row][col] == 'E');
    }

    // Solves the maze recursively
    public static boolean doMaze(char[][] maze, int startX, int startY, int endX, int endY) {
        // Base case: if the current position is the end
        if (startX == endX && startY == endY) {
            maze[startX][startY] = '*';
            return true;
        }

        // Mark the current position as visited
        maze[startX][startY] = '*';

        // Try all four directions
        if (canMove(maze, startX - 1, startY) && doMaze(maze, startX - 1, startY, endX, endY)) { // North
            return true;
        }
        if (canMove(maze, startX, startY + 1) && doMaze(maze, startX, startY + 1, endX, endY)) { // East
            return true;
        }
        if (canMove(maze, startX + 1, startY) && doMaze(maze, startX + 1, startY, endX, endY)) { // South
            return true;
        }
        if (canMove(maze, startX, startY - 1) && doMaze(maze, startX, startY - 1, endX, endY)) { // West
            return true;
        }

        // Backtrack: Unmark the current cell
        maze[startX][startY] = '-';
        return false;
    }
}
