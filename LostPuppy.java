import java.util.*;
import java.io.*;

public class LostPuppy {
    public static void main(String[] args) {
        try {
            Scanner console = new Scanner(System.in);
            Scanner input = new Scanner(new File("MazeData3.txt"));
            char[][] maze = getMaze(input);
            int[][] locations = getStartExitLocation(maze);
            int startX = locations[0][0];
            int startY = locations[0][1];
            int endX = locations[1][0];
            int endY = locations[1][1];
            System.out.println("(" + startX + ", " + startY + ")(" + endX + "," + endY + ")");
            // if its true, print maze
            if(doMaze(maze, startX, startY, endX, endY)) {
                displayMaze(maze);
            }
            else {
                // else if false, print no path found
                System.out.println("No path found.");
            }
            console.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        }
    }

    // gets the maze char[][] array
    public static char[][] getMaze(Scanner input) {
        List<String> lines = new ArrayList<>();
        while (input.hasNextLine()) {
            lines.add(input.nextLine());
        }

        int cols = lines.get(0).length();
        int rows = lines.size();
        char[][] maze = new char[rows][cols];

//        for (int row = 0; row < rows; row++) {
//            String line = lines.get(row);
//            for (int col = 0; col < width; col++) {
//                myMaze[row][col] = line.charAt(col);
//            }
//        }
        for (int row = 0; row < rows; row++) {
            String line = lines.get(row);
            maze[row] = line.toCharArray();
        }

        return maze;
    }

    // displays maze
    public static void displayMaze(char[][] myMaze) {
//        for (int row = 0; row < myMaze.length; row++) {
//            for (int col = 0; col < myMaze[row].length; col++) {
//                System.out.print(myMaze[row][col]);
//            }
//            System.out.println();
//        }
        for (char[] row : myMaze) {
            System.out.println(new String(row));
        }
    }

    // gets start and exit location of maze
    public static int[][] getStartExitLocation(char[][] maze) {
        int[] startLocation = new int[2];
        int[] endLocation = new int[2];

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                if (maze[row][col] == 'S') {
                    startLocation[0] = row;
                    startLocation[1] = col;
                    maze[row][col] = '*';  // Mark the start location with '*'
                }
                else if (maze[row][col] == 'E') {
                    endLocation[0] = row;
                    endLocation[1] = col;
                }
            }
        }
        return new int[][]{startLocation, endLocation};
    }

    // sees if you can move
    public static boolean canMove(char[][] maze, int row, int col) {
//        boolean move = false;
        // if row and col is not less than or equal to 0
        // and while row is less than length of rows in maze
        // and while col is less than the length of columns in maze
//        if (row >= 0 && col >= 0 && row < maze.length && col < maze[row].length) {
//            // and if maze[row][col] is equal to ' ' or maze[row][col] is equal to 'E'
//            if (maze[row][col] == ' ' || maze[row][col] == 'E') {
//                move = true;
//            }
//        }
        return row >= 0 && col >= 0 && row < maze.length &&
                col < maze[row].length &&
                (maze[row][col] == ' ' || maze[row][col] == 'E');
    }
    // maze, 12, 19, 0, 18
    public static boolean doMaze(char[][] maze, int startX, int startY, int endX, int endY) {
        boolean pathFound = false;

        // base case: if coords equal to end coords
        if (startX == endX && startY == endY) {
            maze[startX][startY] = '*';
            pathFound = true;
        }

        // mark the visited cell
        maze[startX][startY] = '*';



        // west
        if (canMove(maze, startX - 1, startY) && doMaze(maze, startX - 1, startY, endX, endY)) {
            pathFound = true;
        }

        // north
        if (canMove(maze, startX, startY + 1) && doMaze(maze, startX, startY + 1, endX, endY)) {
            pathFound = true;
        }

        // east
        if (canMove(maze, startX + 1, startY) && doMaze(maze, startX + 1, startY, endX, endY)) {
            pathFound = true;
        }

        // south
        if (canMove(maze, startX, startY - 1) && doMaze(maze, startX, startY + 1, endX, endY)) {
            pathFound = true;
        }

        // backtrack if no path found
        if (!pathFound) {
            maze[startX][startY] = '-';
        }

        return pathFound;
    }
}

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//public class LostPuppy {
//    public static void main(String[] args) {
//        try {
//            // Read maze from file
//            char[][] maze = getMaze("MazeData2.txt");
//
//            // Find start and exit positions
//            int[] start = new int[2];
//            int[] exit = new int[2];
//            getStartExitLocation(maze, start, exit);
//
//            // Solve the maze
//            if (doMaze(maze, start[0], start[1], exit[0], exit[1])) {
//                System.out.println("Maze Solved!");
//            } else {
//                System.out.println("No Path Found!");
//            }
//
//            // Display the maze with the solution path
//            displayMaze(maze);
//
//        } catch (FileNotFoundException e) {
//            System.out.println("Maze file not found: " + e.getMessage());
//        }
//    }
//
//    // Reads the maze from the file
//    public static char[][] getMaze(String filename) throws FileNotFoundException {
//        Scanner fileScanner = new Scanner(new File(filename));
//        StringBuilder mazeBuilder = new StringBuilder();
//        int rows = 0;
//
//        // Read the file line by line
//        while (fileScanner.hasNextLine()) {
//            mazeBuilder.append(fileScanner.nextLine()).append("\n");
//            rows++;
//        }
//        fileScanner.close();
//
//        // Determine maze dimensions
//        String[] lines = mazeBuilder.toString().split("\n");
//        int cols = lines[0].length();
//
//        // Create the maze array
//        char[][] maze = new char[rows][cols];
//        for (int i = 0; i < rows; i++) {
//            maze[i] = lines[i].toCharArray();
//        }
//
//        return maze;
//    }
//
//    // Finds the start (S) and exit (E) locations
//    public static void getStartExitLocation(char[][] maze, int[] start, int[] exit) {
//        for (int i = 0; i < maze.length; i++) {
//            for (int j = 0; j < maze[i].length; j++) {
//                if (maze[i][j] == 'S') {
//                    start[0] = i;
//                    start[1] = j;
//                    maze[i][j] = '*'; // Mark the start as visited
//                } else if (maze[i][j] == 'E') {
//                    exit[0] = i;
//                    exit[1] = j;
//                }
//            }
//        }
//    }
//
//    // Checks if a move is valid
//    public static boolean canMove(char[][] maze, int row, int col) {
//        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length &&
//                (maze[row][col] == ' ' || maze[row][col] == 'E');
//    }
//
//    // Recursive method to solve the maze
//    public static boolean doMaze(char[][] maze, int startX, int startY, int endX, int endY) {
//        // Base case: If we reach the exit
//        if (startX == endX && startY == endY) {
//            return true;
//        }
//
//        // Try all possible moves: west, north, east, south
//        int[][] directions = { {0, -1}, {-1, 0}, {0, 1}, {1, 0} };
//        for (int[] dir : directions) {
//            int newX = startX + dir[0];
//            int newY = startY + dir[1];
//
//            if (canMove(maze, newX, newY)) {
//                maze[newX][newY] = '*'; // Mark the move
//                if (doMaze(maze, newX, newY, endX, endY)) {
//                    return true;
//                }
//                maze[newX][newY] = '-'; // Backtrack
//            }
//        }
//
//        return false; // No path found
//    }
//
//    // Displays the maze with the solution path
//    public static void displayMaze(char[][] maze) {
//        for (char[] row : maze) {
//            for (char cell : row) {
//                System.out.print(cell);
//            }
//            System.out.println();
//        }
//    }
//}
