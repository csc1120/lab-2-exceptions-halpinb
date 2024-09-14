/*
 * Course: CSC1020 131
 * Fall 2024
 * Lab 2 - Exceptions
 * DieNotRolledException class
 * Name: Brady Halpin
 * Last Updated: 9/9/2024
 */
package halpinb;

/**
 * The DieNotRolledException program extends from
 * RuntimeException and is called when a die is not
 * rolled before looking for its value.
 */
public class DieNotRolledException extends RuntimeException {
    /**
     * the constructor for the exception
     * @param message is a string to print out when
     *                an exception occurs.
     */
    public DieNotRolledException(String message) {
        super("Die not rolled: " + message);
    }
}
