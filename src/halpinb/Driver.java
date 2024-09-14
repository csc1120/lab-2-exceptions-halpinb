/*
 * Course: CSC1020 131
 * Fall 2024
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Brady Halpin
 * Last Updated: 9/9/2024
 */
package halpinb;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The driver program will ask the user for
 * three numbers to create and roll dice. If
 * the user gives a bad input, the program
 * will start from the beginning. The
 * result will be printed to the console in
 * the form of asterisks.
 */
public class Driver {
    /**
     * number for the minimum amount of dice needed
     */
    private static final int MIN_DICE = 2;
    /**
     * number for the maximum amount of dice needed
     */
    private static final int MAX_DICE = 10;
    /**
     * number for how long the list of possible numbers is
     */
    private static int range;
    /**
     * number for the lowest possible roll
     */
    private static int minimum;
    public static void main(String[] args) {
        //will go until it has gone through all steps with no exceptions
        boolean retry = true;
        while (retry) {
            try {
                System.out.println("Please enter the number of dice to roll, " +
                        "how many sides the dice have,\n" +
                        "and how many rolls to complete, " +
                        "separating the values by a space.\n" +
                        "Example: \"2 6 1000\"\n");
                System.out.print("Enter configuration: ");
                int[] input = getInput();
                System.out.println();
                Die[] dice = createDice(input[0], input[1]);
                int[] rolls = rollDice(dice, input[1], input[2]);
                range = (input[0] * input[1]) - input[0] + 1;
                minimum = input[0];
                int max = findMax(rolls);
                report(minimum, rolls, max);
                retry = false;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * method to receive input from the user for the dice game
     * @return int[] is a list of numbers to create dice with
     * @throws InputMismatchException when the input contains letters or symbols
     * or when the input does not have three designated numbers
     * @throws IllegalArgumentException when the number of dice is not between 2 or 10
     */
    public static int[] getInput() {
        //setting up scanner and lists
        Scanner input = new Scanner(System.in);
        int[] list = null;
        String in = input.nextLine();
        List<String> integerStrings = new ArrayList<>();
        //collecting all the numbers
        while(in.contains(" ")) {
            integerStrings.add(in.substring(0, in.indexOf(" ")));
            in = in.substring(in.indexOf(" ") + 1);
        }
        integerStrings.add(in);
        //finding if any contain non-numbers
        String invalid = "abcdefghijklmnopqrstuvwxyz!@#$%^&*()~_+{}|:\"<>?`-=[]\\;',./";
        for(int i = 0; i < integerStrings.size(); i++) {
            for(int j = 0; j < integerStrings.get(i).length(); j++) {
                if (invalid.contains(integerStrings.get(i).substring(j, j + 1))) {
                    throw new InputMismatchException("Cannot contain letters or symbols");
                }
            }
        }
        //finding if less or more than three numbers were given
        if(!(integerStrings.size() == 3)) {
            throw new InputMismatchException("Must have 3 whole numbers, not " +
                    integerStrings.size());
        }
        //finally placing them in the int[] list
        list = new int[]{Integer.valueOf(integerStrings.get(0)),
                Integer.valueOf(integerStrings.get(1)),
                Integer.valueOf(integerStrings.get(2)) };
        //finding if too little or too many dice were inputted
        if (list[0] < MIN_DICE || list[0] > MAX_DICE) {
            throw new IllegalArgumentException("Number of dice must be between 2 and 10");
        }
        return list;
    }

    /**
     * method to create the dice used to roll
     * @param numDice is an int for how many dice are being rolled
     * @param numSides is an int for the number of sides each die has
     * @return Die[] is a list of the Die objects created
     */
    public static Die[] createDice(int numDice, int numSides) {
        //making list of die objects and filling it out
        Die[] dice = new Die[numDice];
        for(int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }

    /**
     * method to roll all the dice created in the previous method
     * @param dice is a Die[] list that contains die objects
     * @param numSides is an int for the number of sides each die has
     * @param numRolls is an int for how many times the dice will be rolled
     * @return int[] of the values of dice rolled
     */
    public static int[] rollDice(Die[] dice, int numSides, int numRolls) {
        //making a list of ints
        int[] rolls = new int[numRolls];
        //rolling and inspecting each die the amount of time requested
        for(int i = 0; i < numRolls; i++) {
            for(int j = 0; j < dice.length; j++) {
                dice[j].roll();
                rolls[i] = rolls[i] + dice[j].getCurrentValue();
            }
        }
        return rolls;
    }

    /**
     * method to find the maximum amount of a single number rolled
     * @param rolls is an int[] list of the rolls the dice produced
     * @return int of the highest amount of rolls a single number got
     */
    public static int findMax(int[] rolls) {
        //finding how many times each possible number was rolled
        int[] list = new int[range];
        for(int i = 0; i < list.length; i++) {
            for(int j = 0; j < rolls.length; j++) {
                if(i + minimum == rolls[j]) {
                    list[i]++;
                }
            }
        }
        //going through the list and finding the largest value
        int max = list[0];
        for(int i = 1; i < list.length; i++) {
            if(max < list[i]) {
                max = list[i];
            }
        }
        return max;
    }

    /**
     * method to print the results to the user in a readable fashion
     * @param numDice is an int for how many dice are being rolled
     * @param rolls is an int[] list of the rolls the dice produced
     * @param max is the maximum amount of rolls a single number got
     */
    public static void report(int numDice, int[] rolls, int max) {
        //recreating the list from findMax
        int[] list = new int[range];
        for(int i = 0; i < list.length; i++) {
            for(int j = 0; j < rolls.length; j++) {
                if(i + minimum == rolls[j]) {
                    list[i]++;
                }
            }
        }
        //formating the report so colons and asterisks line up
        for(int i = 0; i < range; i++) {
            System.out.printf("%4s:" + list[i] + "\t\t", i + minimum);
            //printing asterisks according to the scale and rolls of each possible number
            if(!(max / 10 == 0)) {
                for(int j = 0; j < list[i] / (max / 10); j++) {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }
}