//import java.util.*;
//import java.io.*;
//
//public class LostPuppy {
//    public static void main(String[] args) {
//        try {
//            Scanner console = new Scanner(System.in);
//            Scanner input = new Scanner(new File("MazeData2.txt"));
//            char[][] maze = getMaze(input);
//            int[][] locations = getStartExitLocation(maze);
//            int startX = locations[0][0];
//            int startY = locations[0][1];
//            int endX = locations[1][0];
//            int endY = locations[1][1];
//            System.out.println("(" + startX + ", " + startY + ")(" + endX + "," + endY + ")");
//            if(doMaze(maze, startX, startY, endX, endY)) {
//                displayMaze(maze);
//            }
//            else {
//                System.out.println("No path found.");
//            }
//
//            console.close();
//        } catch (FileNotFoundException e) {
//            System.out.print("File not found");
//        }
//
//    }
//
//    public static char[][] getMaze(Scanner input) {
//        List<String> lines = new ArrayList<>();
//
//        while (input.hasNextLine()) {
//            String line = input.nextLine();
//            lines.add(line);
//        }
//
//        int width = lines.get(0).length();
//        int length = lines.size();
//        input.close();
//
//        char[][] myMaze = new char[length][width];
//
//        for (int row = 0; row < length; row++) {
//            String line = lines.get(row);
//            for (int col = 0; col < width; col++) {
//                myMaze[row][col] = line.charAt(col);
//            }
//        }
//        return myMaze;
//    }
//
//    public static void displayMaze(char[][] myMaze) {
//        for (int row = 0; row < myMaze.length; row++) {
//            for (int col = 0; col < myMaze[row].length; col++) {
//                System.out.print(myMaze[row][col]);
//            }
//            System.out.println();
//        }
//    }
//
//    public static int[][] getStartExitLocation(char[][] maze) {
//        int[] startLocation = new int[2]; // change to char to store "*"?
//        int[] endLocation = new int[2]; // change to char to store " "
//
//        for (int row = 0; row < maze.length; row++) {
//            for (int col = 0; col < maze[row].length; col++) {
//                if (maze[row][col] == 'S') {
//                    startLocation[0] = row;
//                    startLocation[1] = col;
//                    maze[row][col] = '*';
//                }
//                else if (maze[row][col] == 'E') {
//                    endLocation[0] = row;
//                    endLocation[1] = col;
//                }
//            }
//        }
//        return new int[][]{startLocation, endLocation};
//    }
//
//    public static boolean canMove(char[][] maze, int row, int col) {
//
//        return row >= 0 && col >= 0 && row < maze.length && col < maze[row].length
//                && (maze[row][col] == ' ' || maze[row][col] == 'E');
//
//    }
//
//    public static boolean doMaze (char[][] maze, int startX, int startY, int endX, int endY) {
//        boolean pathFound = false;
//
//        if (startX == endX && startY == endY) {
//            maze[startX][startY] = '*';
//            pathFound = true;
//        }
//
//        if (!(canMove(maze, startX, startY)) || maze[startX][startY] == '*') {
//            pathFound = false;
//        }
//
//        maze[startX][startY] = '*';
//
//        if (startY - 1 >= 0 && doMaze(maze, startX, startY - 1, endX, endY)) { // north
//            pathFound = true;
//        }
//        else if (startX + 1 < maze.length && doMaze(maze, startX + 1, startY, endX, endY)) { // east
//            pathFound = true;
//        }
//        else if (startX - 1 >= 0 && doMaze(maze, startX - 1, startY, endX, endY)) { // west
//            pathFound = true;
//        }
//        else if (startY + 1 < maze[startX].length && doMaze(maze, startX, startY + 1, endX, endY)) { // south
//            pathFound = true;
//        }
//
//        if (!pathFound) {
//            maze[startX][startY] = '-';
//        }
//
//        return pathFound;
//
//        }
//
//}
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
            if(doMaze(maze, startX, startY, endX, endY)) {
                displayMaze(maze);
            }
            else {
                System.out.println("No path found.");
            }
            console.close();
        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        }
    }

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

    public static boolean canMove(char[][] maze, int row, int col) {
        return row >= 0 && col >= 0 && row < maze.length && col < maze[row].length
                && (maze[row][col] == ' ' || maze[row][col] == 'E');
    }

    public static boolean doMaze(char[][] maze, int startX, int startY, int endX, int endY) {
        boolean pathFound = false;

        // Base case: if we've reached the destination
        if (startX == endX && startY == endY) {
            maze[startX][startY] = '*';
            return true;  // Path found
        }

        // If the current cell is not valid (blocked or visited)
        if (!canMove(maze, startX, startY) || maze[startX][startY] == '*') {
            return false;
        }

        // Mark the current cell as part of the path
        maze[startX][startY] = '*';

        // Try all four directions (up, right, down, left)
        if (startY - 1 >= 0 && doMaze(maze, startX, startY - 1, endX, endY)) {  // Up
            pathFound = true;
        }
        else if (startX + 1 < maze.length && doMaze(maze, startX + 1, startY, endX, endY)) {  // Right
            pathFound = true;
        }
        else if (startX - 1 >= 0 && doMaze(maze, startX - 1, startY, endX, endY)) {  // Left
            pathFound = true;
        }
        else if (startY + 1 < maze[startX].length && doMaze(maze, startX, startY + 1, endX, endY)) {  // Down
            pathFound = true;
        }

        // If no path was found, unmark this cell (backtracking)
        if (!pathFound) {
            maze[startX][startY] = '-';
        }

        return pathFound;
    }
}

