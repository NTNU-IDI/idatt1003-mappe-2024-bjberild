package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.views.UserInterface;

/**
 * Wrapper class for the static main method to run the Food Waste Manager application.
 */
public class FoodManagerApp {
  /**
   * Main method that runs the application.
   *
   * @param args are the arguments for the main method.
   */
  public static void main(String[] args) {
    UserInterface userInterface = new UserInterface();
    userInterface.init();
    userInterface.start();
  }
}