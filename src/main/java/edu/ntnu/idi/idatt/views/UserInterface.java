package edu.ntnu.idi.idatt.views;

import edu.ntnu.idi.idatt.modules.FoodStorage;
import edu.ntnu.idi.idatt.modules.Grocery;
import edu.ntnu.idi.idatt.modules.GroceryInstance;
import edu.ntnu.idi.idatt.modules.Recipe;
import edu.ntnu.idi.idatt.modules.RecipeBook;
import edu.ntnu.idi.idatt.utils.Utils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * The UserInterface class.
 *
 * @author bjberild
 * @version 0.6
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
  private static final int UPDATE_ALL_GROCERIES = 11;
  private static final int EXIT = 0;
  private final Utils utils = new Utils();
  private final RecipeBook recipeBook = new RecipeBook();
  FoodStorage foodStorage = recipeBook.getFoodStorage();
  TreeMap<String, Grocery> groceryTreeMap = foodStorage.getGroceries();


  /**
   * Initializes the application by creating a RecipeBook.
   */
  public void init() {
    try {
      ArrayList<GroceryInstance> kilograms = new ArrayList<>();
      kilograms.add(new GroceryInstance(54.5, 0.650));
      kilograms.add(new GroceryInstance(82.9, 0.850));
      kilograms.add(new GroceryInstance(54.5, 1.325));
      Grocery bacon = new Grocery("bacon", "kilograms");
      Grocery flour = new Grocery("flour", "kilograms");
      Grocery cheese = new Grocery("cheese", "kilograms");
      for (GroceryInstance gi : kilograms) {
        bacon.addExpiryDate(LocalDate.of(2025, 1, 1), gi);
        flour.addExpiryDate(LocalDate.of(2025, 1, 1), gi);
        cheese.addExpiryDate(LocalDate.of(2025, 1, 1), gi);
      }
      Grocery eggs = new Grocery("eggs", "pieces");
      GroceryInstance instancePcs1 = new GroceryInstance(32, 6);
      GroceryInstance instancePcs2 = new GroceryInstance(50, 12);
      eggs.addExpiryDate(LocalDate.of(2025, 1, 1), instancePcs1);
      eggs.addExpiryDate(LocalDate.of(2023, 1, 1), instancePcs2);

      Grocery milk = new Grocery("milk", "liters");
      GroceryInstance instanceLiter1 = new GroceryInstance(25, 1);
      GroceryInstance instanceLiter2 = new GroceryInstance(35, 1.5);
      milk.addExpiryDate(LocalDate.of(2025, 1, 1), instanceLiter1);
      milk.addExpiryDate(LocalDate.of(2024, 1, 1), instanceLiter2);

      foodStorage.addGrocery(bacon.getName(), bacon);
      foodStorage.addGrocery(flour.getName(), flour);
      foodStorage.addGrocery(cheese.getName(), cheese);
      foodStorage.addGrocery(eggs.getName(), eggs);
      foodStorage.addGrocery(milk.getName(), milk);
    } catch (Exception e) {
      System.out.println("Something went wrong with the groceries.\n Exception: " + e);
    }

    try {
      HashMap<String, Double> recipeGroceries1 = new HashMap<>();
      recipeGroceries1.put("eggs", 2.0);
      recipeGroceries1.put("bacon", 0.075);
      Recipe eggAndBacon = new Recipe("eggs and bacon", "description",
          "instructions", recipeGroceries1, 1);

      HashMap<String, Double> recipeGroceries2 = new HashMap<>();
      recipeGroceries2.put("ground meat", 0.400);
      Recipe burgerPatties = new Recipe("burger patties", "description",
           "instructions", recipeGroceries2, 4);
      recipeBook.addRecipe(eggAndBacon);
      recipeBook.addRecipe(burgerPatties);
    } catch (Exception e) {
      System.out.println("Something went wrong with the recipes.\n Exception: " + e);
    }

  }

  public void start() {

    boolean finished = false;
    while (!finished) {
      int menuChoice = utils.showMenu();
      switch (menuChoice) {
        case LIST_ALL_GROCERIES:
          break;
        case ADD_GROCERY:
          break;
        case SEARCH_GROCERIES:
          break;
        case REMOVE_GROCERY:
          break;
        case HANDLE_EXPIRED_GROCERIES:
          break;
        case LIST_ALL_RECIPES:
          break;
        case ADD_RECIPE:
          break;
        case SEARCH_RECIPE:
          break;
        case REMOVE_RECIPE:
          break;
        case GET_TOTAL_STORAGE_VALUE:
          break;
        case UPDATE_ALL_GROCERIES:
          break;
        case EXIT:
          System.out.println("Thanks for using the Food Waste Manager!");
          finished = true;
          break;
      }
    }
  }

  /**
   * Prints out a list of all the registered groceries.
   */
  public void listAllGroceries() {
    if (groceryTreeMap.isEmpty()) {
      System.out.println("There are no groceries in the food storage");
    } else {
      ArrayList<Grocery> groceries = new ArrayList<>(groceryTreeMap.values());
      System.out.println("All registered groceries:");
      for (Grocery g : groceries) {
        System.out.println(g.printableGroceryString());
      }
      System.out.println("\n");
    }
  }
}
