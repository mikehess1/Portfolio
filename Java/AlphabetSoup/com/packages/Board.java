package com.packages;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    //declare board attributes
    char[][] boardArray;
    int rows;
    int cols;
    ArrayList<String> wordsList = new ArrayList<String>();
    boolean found;

    public Board () {

        //initialize new scanner to read input file
        Scanner scanner = new Scanner(System.in);

        //scan in grid size
        String grid = scanner.nextLine();

        //split grid string at the 'x' and assign value to rows, cols
        String[] tokens = grid.split("x");
        rows = Integer.parseInt(tokens[0]);
        cols = Integer.parseInt(tokens[1]);

        //initialize string array to hold rows of the word search
        String[] lines = new String[rows];

        //loop to scan in each row, remove whitespace and store in array
        for (int i = 0; i < rows; i++) {
            //System.out.print("Enter line " + (i + 1) + " of the word search: ");
            lines[i] = scanner.nextLine();
            lines[i] = lines[i].replaceAll("\\s+","");
        }

        //initialize character array for game board
        boardArray = new char[rows][cols];

        //load characters from string array into game board
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                boardArray[r][c] = lines[r].charAt(c);
            }
        }

        //scan remaining lines and store in arraylist
        while (scanner.hasNextLine()) {
            wordsList.add(scanner.nextLine());
        }

        //finished with scanner, close
        scanner.close();
    }

    //search for each word in the board
    public void findWords() {

        //for each word in the word array, search for the location
        for (int w = 0; w < wordsList.size(); w++) {

            //load next word to string
            String word = wordsList.get(w);
            System.out.print(word + " ");

            //specify word w hasn't been found yet
            found = false;

            //parse the board for the first letter of the word
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {

                    //if word hasnt been found yet and first letter is found, check each direction
                    if (boardArray[r][c] == word.charAt(0)) {
                        if (!found) checkRight(r, c, word);
                        if (!found) checkDown(r, c, word);
                        if (!found) checkLeft(r, c, word);
                        if (!found) checkUp(r, c, word);
                        if (!found) checkUpright(r, c, word);
                        if (!found) checkDownright(r, c, word);
                        if (!found) checkDownleft(r, c, word);
                        if (!found) checkUpleft(r, c, word);
                    }
                }
            }

            //if word wasn't found, print word was not found
            if (!found) System.out.println("was not found");
        }
    }

    //method to check if the word is to the right
    public void checkRight(int row, int column, String word) {
        for (int i = 1; i < word.length(); i++) {
            if (column + i > cols - 1) return;
            if (word.charAt(i) != boardArray[row][column + i]) return;
        }

        //if found, set found to true and print start and end points
        found = true;
        System.out.println(row + ":" + column + " " + row + ":" + (--column + word.length()));
    }

    //method to check if the word is down
    public void checkDown(int row, int column, String word) {
        for (int i = 1; i < word.length(); i++) {
            if (row + i > rows - 1) return;
            if (word.charAt(i) != boardArray[row + i][column]) return;
        }

        //if found, set found to true and print start and end points
        found = true;
        System.out.println(row + ":" + column + " " + (--row + word.length()) + ":" + column);
    }

    //method to check if the word is to the left
    public void checkLeft(int row, int column, String word) {
        for (int i = 1; i < word.length(); i++) {
            if (column - i < 0) return;
            if (word.charAt(i) != boardArray[row][column - i]) return;
        }

        //if found, set found to true and print start and end points
        found = true;
        System.out.println(row + ":" + column + " " + row + ":" + (++column - word.length()));
    }

    //method to check if the word is up
    public void checkUp(int row, int column, String word) {
        for (int i = 1; i < word.length(); i++) {
            if (row - i < 0) return;
            if (word.charAt(i) != boardArray[row - i][column]) return;
        }

        //if found, set found to true and print start and end points
        found = true;
        System.out.println(row + ":" + column + " " + (++row - word.length()) + ":" + column);
    }

    //method to check if the word is up to the right
    public void checkUpright(int row, int column, String word) {
        for (int i = 1; i < word.length(); i++) {
            if (row - i < 0) return;
            if (column + i > cols - 1) return;
            if (word.charAt(i) != boardArray[row - i][column + i]) return;
        }

        //if found, set found to true and print start and end points
        found = true;
        System.out.println(row + ":" + column + " " + (++row - word.length()) + ":" + (--column + word.length()));
    }

    //method to check if the word is down to the right
    public void checkDownright(int row, int column, String word) {
        for (int i = 1; i < word.length(); i++) {
            if (row + i > rows - 1) return;
            if (column + i > cols - 1) return;
            if (word.charAt(i) != boardArray[row + i][column + i]) return;
        }

        //if found, set found to true and print start and end points
        found = true;
        System.out.println(row + ":" + column + " " + (--row + word.length()) + ":" + (--column + word.length()));
    }

    //method to check if the word is down to the left
    public void checkDownleft(int row, int column, String word) {
        for (int i = 1; i < word.length(); i++) {
            if (row + i > rows - 1) return;
            if (column - i < 0) return;
            if (word.charAt(i) != boardArray[row + i][column - i]) return;
        }

        //if found, set found to true and print start and end points
        found = true;
        System.out.println(row + ":" + column + " " + (--row + word.length()) + ":" + (++column - word.length()));
    }

    //method to check if the word is up to the left
    public void checkUpleft(int row, int column, String word) {
        for (int i = 1; i < word.length(); i++) {
            if (row - i < 0) return;
            if (column - i < 0) return;
            if (word.charAt(i) != boardArray[row - i][column - i]) return;
        }

        //if found, set found to true and print start and end points
        found = true;
        System.out.println(row + ":" + column + " " + (++row - word.length()) + ":" + (++column - word.length()));
    }
}

