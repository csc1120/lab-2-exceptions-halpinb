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
    public DieNotRolledException(String message) {
        super("Die not rolled: " + message);
    }
}
