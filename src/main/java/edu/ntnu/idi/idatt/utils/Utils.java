package edu.ntnu.idi.idatt.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Utility class containing commonly used methods. For example parameter testing, input handling and
 * printing commonly used Strings like the menu.
 *
 * @author bjberild
 * @version 0.6
 * @since 0.3
 */
public class Utils {
  private static final Scanner scanner = new Scanner(System.in);


  /**
   * Verifies that a String contains information. The check fails is the String parameter is blank.
   *
   * @param parameter     String to be verified
   * @param parameterName is the name of the String being checked.
   * @throws IllegalArgumentException if the String parameter is either "" or only whitespace,
  this will be considered illegal.
   */
  public static void verifyStringParameter(String parameter, String parameterName)
      throws IllegalArgumentException {
    if (parameter.isBlank()) {
      throw new IllegalArgumentException("The string for the parameter "
          + parameterName + " was a blank string, try to register again");
    }
  }

  /**
   *  Simple method to avoid exceptions when scanning for integers.
   *  Uses Scanner.nextLine() before returning to get rid of the line break that gets left behind
   *  after specific next methods of Scanners.
   *
   * @return an int from the user.
   */
  public int inputInt() {
    while (!scanner.hasNextInt()) {
      System.out.println("Invalid number, try again");
      scanner.next();
    }
    scanner.nextLine();
    return scanner.nextInt();
  }

  /**
   *  Simple method to avoid exceptions when scanning for doubles.
   *  Uses Scanner.nextLine() before returning to get rid of the line break that gets left behind
   *  after specific next methods of Scanners.
   *
   * @return a double from the user.
   */
  public double inputDouble() {
    while (!scanner.hasNextDouble()) {
      System.out.println("Invalid number, try again");
      scanner.next();
    }
    scanner.nextLine();
    return scanner.nextDouble();
  }

  /**
   * Simple method to avoid exceptions when scanning for user input in the terminal.
   *
   * @return a String from the user.
   */
  public String inputString() {
    while (!scanner.hasNextLine()) {
      System.out.println("Invalid input, try again");
      scanner.next();
    }
    return scanner.nextLine();
  }

  public void clearNext() {
    scanner.nextLine();
  }

  /**
   * Method for getting LocalDate variables from user input.
   *
   * @return a non-null LocalDate value.
   */
  public LocalDate inputDate() {
    LocalDate localDate = null;
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    System.out.println("Enter date in format (yyyy-MM-dd):");
    while (localDate == null) {
      try {
        localDate = LocalDate.parse(scanner.nextLine(), myFormatObj);
      } catch (DateTimeParseException e) {
        System.out.println("Invalid date, try again");
      }
    }
    return localDate;
  }

  /**
   * Prints the menu then asks the user for choice.
   *
   * @return an integer representing user choice based on written menu.
   */
  public int showMenu() {
    printMenu();
    return inputInt();

  }

  /**
   * Prints the main user choice menu.
   */
  private void printMenu() {
    System.out.println("""
        ~~~~~~ Food Waste Manager ~~~~~~~
        1. List all groceries
        2. Add a grocery
        3. Search groceries
        4. Remove a grocery
        5. Handle expired groceries
        6. List all recipes
        7. Add a recipe
        8. Search recipes
        9. Remove a recipe
        10. Retrieve total value of groceries in storage
        11. Update all groceries
        0. Exit program
        
        Please enter a number from 0-11.""");
  }
}
