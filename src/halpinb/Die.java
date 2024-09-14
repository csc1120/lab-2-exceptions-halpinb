/*
 * Course: CSC1020 131
 * Fall 2024
 * Lab 2 - Exceptions
 * Die class
 * Name: Brady Halpin
 * Last Updated: 9/9/2024
 */
package halpinb;

/**
 * The Die class creates a single die and
 * holds the values for its sides and current
 * value. The die can be rolled and then the
 * value it landed on can be inspected.
 */
public class Die {
    /**
     * int for the maximum sides the die must have
     */
    private static final int MAX_SIDES = 100;
    /**
     * int for the minimum sides the die must have
     */
    private static final int MIN_SIDES = 2;
    /**
     * int for the number of sides the die has
     */
    private int numSides;
    /**
     * int for the current value of the die
     */
    private int currentValue;

    /**
     * constructor for creating a die
     * @param numSides is an int for the number of sides the die should have
     * @throws IllegalArgumentException when the number of sides is not between 2 and 10
     */
    public Die(int numSides) {
        currentValue = 0;
        //throws exception if the sides are not between 2 and 100
        if(numSides < MIN_SIDES || numSides > MAX_SIDES) {
            throw new IllegalArgumentException("Number of sides must be between 2 and 100");
        }
        this.numSides = numSides;
    }

    /**
     * a method for getting the current value of the die
     * @return int for the current value of the die
     * @throws DieNotRolledException when the die has not been
     * rolled before checking its value again
     */
    public int getCurrentValue() throws DieNotRolledException {
        //throws exception when if the die was not rolled
        if(currentValue < 1 || currentValue > numSides) {
            throw new DieNotRolledException("The dice was not rolled");
        }
        int x = currentValue;
        currentValue = 0;
        return x;
    }

    /**
     * a method for rolling the die by setting
     * it to a random value between one and
     * the number of sides it has.
     */
    public void roll() {
        currentValue = (int)((Math.random() * numSides) + 1);
    }
}