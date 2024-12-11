package edu.ntnu.idi.idatt.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Utility class containing methods using java.util.Scanner to receive user input from the terminal.
 * The class also contains often printed Strings for the user menu.
 *
 * @author bjberild
 * @version 0.3
 * @since 0.3
 */
public class Utils {


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
        10. Retrieve total value of groceries
        0. Exit program
        
        Please enter a number between 0-10.""");
  }
}
