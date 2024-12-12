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
import java.util.Locale;
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

  /**
   * Method to start the app.
   */
  public void start() {
    boolean finished = false;
    while (!finished) {
      int menuChoice = utils.showMenu();
      switch (menuChoice) {
        case LIST_ALL_GROCERIES:
          listAllGroceries();
          break;
        case ADD_GROCERY:
          addGrocery();
          break;
        case SEARCH_GROCERIES:
          searchGroceries();
          break;
        case REMOVE_GROCERY:
          removeGrocery();
          break;
        case HANDLE_EXPIRED_GROCERIES:
          handleExpiredGroceries();
          break;
        case LIST_ALL_RECIPES:
          listAllRecipes();
          break;
        case ADD_RECIPE:
          addRecipe();
          break;
        case SEARCH_RECIPE:
          searchRecipe();
          break;
        case REMOVE_RECIPE:
          removeRecipe();
          break;
        case GET_TOTAL_STORAGE_VALUE:
          getTotalStorageValue();
          break;
        case UPDATE_ALL_GROCERIES:
          updateAllGroceries();
          break;
        case EXIT:
          System.out.println("Thanks for using the Food Waste Manager!");
          finished = true;
          break;
        default:
          System.out.println("Invalid choice");
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

  /**
   * Adds a grocery object if the name doesn't exist in FoodStorage. Else it adds a LocalDate and
   * GroceryInstance to ExpiryDate
   */
  public void addGrocery() {
    utils.clearNext();
    System.out.println("Type in the name of the grocery you want to add:");
    String name = utils.inputString().toLowerCase();
    if (!groceryTreeMap.containsKey(name)) {
      System.out.println("""
          Type in the unit of the grocery. Choose between kilograms, liters and pieces.""");
      String unit;
      unit = utils.inputString().toLowerCase();
      foodStorage.getGroceries().put(name, new Grocery(name, unit));
    }
    LocalDate expiryDate = null;
    while (expiryDate == null) {
      System.out.println("Type in the expiry date");
      expiryDate = utils.inputDate();
    }
    double amount = 0;
    System.out.println("Type in the amount.");
    utils.clearNext();
    amount = utils.inputDouble();
    System.out.println("Type in the price per unit of the grocery.");
    double price = utils.inputDouble();
    GroceryInstance instance = new GroceryInstance(price, amount);
    groceryTreeMap.get(name).addExpiryDate(expiryDate, instance);
  }

  /**
   * Searches for a grocery given a name.
   */
  public void searchGroceries() {
    utils.clearNext();
    System.out.println("Type in the name of the grocery you want to search for:");
    String name = utils.inputString().toLowerCase();
    Grocery grocery = foodStorage.searchGroceries(name);
    if (grocery == null) {
      System.out.println("Couldn't find grocery.");
    } else {
      System.out.println(grocery.printableGroceryString());
    }
  }

  /**
   * Removes a grocery given a name.
   */
  public void removeGrocery() {
    utils.clearNext();
    System.out.println("Type in the name of the grocery you want to remove:");
    String name = utils.inputString().toLowerCase();
    groceryTreeMap.remove(name);
  }

  /**
   * A method for handling expired goods by showing total value of all expired goods in FoodStorage.
   */
  public void handleExpiredGroceries() {
    utils.clearNext();
    TreeMap<String, Grocery> expiredGroceries;
    expiredGroceries = foodStorage.getExpiredGroceries();
    double total = foodStorage.totalGroceriesValue(expiredGroceries.values());
    System.out.println("Total expired groceries worth: " + total);
    System.out.println("Want to remove all expired groceries? Type (y/n)");
    String yesNo = utils.inputString().toLowerCase();
    while (!(yesNo.equals("y") || yesNo.equals("n"))) {
      System.out.println("Type in y or n");
      yesNo = utils.inputString().toLowerCase();
    }
    if (yesNo.equals("y")) {
      foodStorage.removeExpiredGroceries();
      System.out.println("Removed all expired groceries");
    }
  }

  public void listAllRecipes() {
    recipeBook.getRecipes().forEach(recipe -> System.out.println(recipe.getName() + "\n"));
  }

  /**
   * Asks the user for user input to make a recipe to then add into RecipeBook.
   */
  public void addRecipe() {
    utils.clearNext();
    System.out.println("Type in the name of the recipe you want to add:");
    String name = utils.inputString().toLowerCase();
    System.out.println("Type the description of the dish you want to add:");
    String description = utils.inputString().toLowerCase();
    System.out.println("Type the instructions of the dish you want to add:");
    String instructions = utils.inputString().toLowerCase();
    utils.clearNext();
    System.out.println("Type the amount of portions the recipe makes");
    int portions = utils.inputInt();
    System.out.println("Type the amount of different ingredients needed:");
    int differentIngredients = utils.inputInt();
    HashMap<String, Double> ingredients = new HashMap<String, Double>();
    for (int i = differentIngredients; i > 0; i--) {
      System.out.println("Type the name of the ingredient you want to add:");
      String ingredientName = utils.inputString().toLowerCase();
      utils.clearNext();
      System.out.println("Type the amount needed of that ingredient:");
      double amountNeeded = utils.inputDouble();
      ingredients.put(ingredientName, amountNeeded);
      System.out.println(differentIngredients + " ingredients left to add.");
    }
    Recipe recipe = new Recipe(name, description, instructions, ingredients, portions);
    recipeBook.addRecipe(recipe);
    System.out.println("Recipe added.");
  }

  /**
   * Searches for a recipe by its name given a String.
   */
  public void searchRecipe() {
    utils.clearNext();
    System.out.println("Type in the name of the recipe you want to search:");
    String name = utils.inputString().toLowerCase();
    Recipe recipe = recipeBook.getRecipeByName(name);
    if (recipe == null) {
      System.out.println("Recipe not found.");
    } else {
      System.out.println("Found recipe: " + recipe.getName());

    }
  }

  /**
   * Removes a Recipe based on a given name.
   */
  public void removeRecipe() {
    utils.clearNext();
    System.out.println("Type in the name of the recipe you want to remove:");
    String name = utils.inputString().toLowerCase();
    Recipe recipe = recipeBook.getRecipeByName(name);
    if (recipe == null) {
      System.out.println("Recipe not found.\n");
    } else {
      recipeBook.removeRecipe(recipe);
      System.out.println("Recipe removed.\n");
    }
  }

  public void getTotalStorageValue() {
    double totalValue = foodStorage.totalFoodStorageValue();
    System.out.println("Total storage value: " + totalValue + "\n");
  }

  public void updateAllGroceries() {
    groceryTreeMap.values().forEach(Grocery::updateGrocery);
    System.out.println("All groceries updated.\n");
  }
}
