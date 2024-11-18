/*
 * Course: TCSS143 - Fundamentals of Object-Oriented Programming-Theory
 *                   and Application
 *  Name:		   Alex Douk
 *  Instructor:	Mr. Schuessler
 *  Assignment:   Programming Assignment 5
 *
 *  File Name:	Assignment5.java
 */
import java.util.*;
import java.io.*;

/**
 * The LostPuppy program reads a maze from a file,
 * finds the start and exit locations,
 * and determines if there is a valid path
 * from the start to the exit using a recursive algorithm.
 * If a path is found, the solved maze is displayed; otherwise,
 * an appropriate message is printed.
 *
 * @author Alex Douk
 * @version Fall 2024
 */
public class LostPuppy {

    /**
     * The main method serves as the entry point of the program.
     * It reads the maze file, initializes the maze, identifies
     * the start and exit locations, and attempts to solve the maze.
     *
     * @param args Command-line arguments, not used in this application.
     */
    /**
     * The main method drives the program by reading a maze from a file,
     * finding the start and end positions, and attempting to solve it.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner input = null;
        try {
            input = new Scanner(new File("MazeData2.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

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
    }

    /**
     * Reads the maze data from a file and
     * converts it into a 2D character array.
     *
     * @param input A Scanner object to read the maze data.
     * @return A 2D char array representing the maze.
     */
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
            maze[row] = line.toCharArray();
        }

        return maze;
    }

    /**
     * Displays the current state of the maze.
     *
     * @param maze The 2D char array representing the maze.
     */
    public static void displayMaze(char[][] maze) {
        for (char[] row : maze) {
            System.out.println(new String(row));
        }
    }

    /**
     * Identifies the starting 'S' and ending 'E' positions in the maze.
     *
     * @param maze The 2D char array representing the maze.
     * @return A 2D int array where the first element is the start position
     *         and the second is the exit position.
     */
    public static int[][] getStartExitLocation(char[][] maze) {
        int[] startLocation = new int[2];
        int[] endLocation = new int[2];

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                if (maze[row][col] == 'S') {
                    startLocation[0] = row;
                    startLocation[1] = col;
                    maze[row][col] = '*';
                } else if (maze[row][col] == 'E') {
                    endLocation[0] = row;
                    endLocation[1] = col;
                }
            }
        }

        return new int[][]{startLocation, endLocation};
    }

    /**
     * Checks if the current cell is a valid position to move to.
     *
     * @param maze The 2D char array representing the maze.
     * @param row The row index of the current position.
     * @param col The column index of the current position.
     * @return True if the position is valid; false otherwise.
     */
    public static boolean canMove(char[][] maze, int row, int col) {
        return row >= 0 && col >= 0 &&
                row < maze.length && col < maze[row].length &&
                (maze[row][col] == ' ' || maze[row][col] == 'E');
    }

    /**
     * Recursively solves the maze by attempting
     * to move in all possible directions.
     *
     * @param maze The 2D char array representing the maze.
     * @param startX The current row index of the position.
     * @param startY The current column index of the position.
     * @param endX The row index of the exit position.
     * @param endY The column index of the exit position.
     * @return True if a path to the exit is found; false otherwise.
     */
    public static boolean doMaze(char[][] maze, int startX,
                                 int startY, int endX, int endY) {

        if (startX == endX && startY == endY) {
            maze[startX][startY] = '*';
            return true;
        }

        // Mark the current position as visited
        maze[startX][startY] = '*';

        // West
        if (canMove(maze, startX - 1, startY)
                && doMaze(maze, startX - 1, startY, endX, endY)) {
            return true;
        }

        // South
        if (canMove(maze, startX, startY + 1)
                && doMaze(maze, startX, startY + 1, endX, endY)) {
            return true;
        }

        // East
        if (canMove(maze, startX + 1, startY)
                && doMaze(maze, startX + 1, startY, endX, endY)) {
            return true;
        }

        // North
        if (canMove(maze, startX, startY - 1)
                && doMaze(maze, startX, startY - 1, endX, endY)) {
            return true;
        }

        // Backtrack: Unmark the current cell
        maze[startX][startY] = '-';
        return false;
    }
}
