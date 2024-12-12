package edu.ntnu.idi.idatt.views;

import edu.ntnu.idi.idatt.modules.*;
import edu.ntnu.idi.idatt.utils.*;

/**
 * The UserInterface class.
 *
 * @author bjberild
 * @version 0.2
 * @since 0.2
 */
public class UserInterface {
  //Constants representing menu choices
  private static final int LIST_ALL_GROCERIES = 1;
  private static final int ADD_GROCERY = 2;
  private static final int SEARCH_GROCERIES = 3;
  private static final int REMOVE_GROCERY = 4;
  private static final int HANDLE_EXPIRED_GROCERIES = 5;
  private static final int LIST_ALL_RECIPES = 6;
  private static final int ADD_RECIPE = 7;
  private static final int SEARCH_RECIPE = 8;
  private static final int REMOVE_RECIPE = 9;
  private static final int GET_TOTAL_STORAGE_VALUE = 10;
  private static final int EXIT = 0;
  private final Utils utils = new Utils();
  private RecipeBook recipeBook;

  /**
   * Initializes the application by creating a RecipeBook.
   */
  public void init() {
    this.recipeBook = new RecipeBook();
  }

  public void start() {}
}
