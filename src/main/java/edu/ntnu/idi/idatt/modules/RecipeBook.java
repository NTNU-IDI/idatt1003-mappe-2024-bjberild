package edu.ntnu.idi.idatt.modules;


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
