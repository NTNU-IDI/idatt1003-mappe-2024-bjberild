package edu.ntnu.idi.idatt.modules;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * The RecipeBook class manages and handles a collection of {@code Recipe} objects.
 *
 * @author bjberild
 * @version 0.5
 * @since 0.2
 */
public class RecipeBook {
  private final ArrayList<Recipe> recipes = new ArrayList<>();
  private final FoodStorage foodStorage = new FoodStorage();

  public void addRecipe(Recipe recipe) {
    recipes.add(recipe);
  }

  public void removeRecipe(Recipe recipe) {
    recipes.remove(recipe);
  }

  public ArrayList<Recipe> getRecipes() {
    return recipes;
  }

  public FoodStorage getFoodStorage() {
    return foodStorage;
  }

  /**
   * Search method using a given name to find the recipe.
   * Return null if no recipe is found.
   *
   * @param name String representing name of recipe to be found
   * @return Recipe or null if no recipe is found.
   */
  public Recipe getRecipeByName(String name) {
    for (Recipe recipe : recipes) {
      if (recipe.getName().equals(name)) {
        return recipe;
      }
    }
    return null;
  }


  /**
   * Method for removing a set amount of a given grocery. Works by making a copy of the Grocery,
   * then replacing the original with the adjusted copy.
   * If the amount to be removed is bigger than the amount available in the Grocery then the method
   * removes the Grocery from the TreeMap.
   *
   * @param name String of the name of the grocery that gets adjusted.
   * @param amount A double of the amount that gets adjusted.
   */
  public void removeAmountOfGroceries(String name, double amount) {
    try {
      double groceryAmount = foodStorage.getGroceries().get(name).getTotalAmount();
      if (groceryAmount < amount) {
        foodStorage.getGroceries().remove(name);
      } else {
        Grocery grocery = foodStorage.getGroceries().get(name);
        double remainingAmount = amount;
        Iterator<LocalDate> iterator = grocery.getExpiryDates().keySet().iterator();
        while (iterator.hasNext() && remainingAmount > 0) {
          LocalDate expiryDate = iterator.next();
          GroceryInstance instance = grocery.getExpiryDates().get(expiryDate);
          if (instance.getAmount() >= remainingAmount) {
            iterator.remove();
            remainingAmount -= instance.getAmount();
          } else {
            instance.setAmount(instance.getAmount() - remainingAmount);
          }
        }
        foodStorage.getGroceries().replace(name, grocery);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Something went wrong");
    }

  }

  /**
   * The getUsableRecipes method loops through every registered recipe and checks if the FoodStorage
   * object contains enough groceries to make the recipe. If no recipes are usable then returns an
   * empty ArrayList.
   *
   * @return an ArrayList containing all usable recipes.
   */
  public ArrayList<Recipe> getUsableRecipes() {
    ArrayList<Recipe> usableRecipes = new ArrayList<>();
    TreeMap<String, Grocery> groceries = foodStorage.getGroceries();
    for (Recipe recipe : recipes) {
      boolean isUsable = true;
      Set<String> groceryNames = recipe.getRecipeGroceries().keySet();
      Iterator<String> iterator = groceryNames.iterator();
      while (iterator.hasNext() && isUsable) {
        String groceryName = iterator.next();
        Grocery grocery = groceries.get(groceryName);
        if (grocery.getTotalAmount() < recipe.getRecipeGroceries().get(groceryName)) {
          isUsable = false;
        }
      }
      if (isUsable) {
        usableRecipes.add(recipe);
      }
    }
    return usableRecipes;
  }
}
