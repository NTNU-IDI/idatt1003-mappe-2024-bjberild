package edu.ntnu.idi.idatt.modules;


import java.util.ArrayList;

/**
 * The RecipeBook class manages a collection of {@code Recipe} objects.
 *
 * @author bjberild
 * @version 0.2
 * @since 0.2
 */
public class RecipeBook {
  private final ArrayList<Recipe> recipes = new ArrayList<>();

  public void addRecipe(Recipe recipe) {
    recipes.add(recipe);
  }

  public void removeRecipe(Recipe recipe) {
    recipes.remove(recipe);
  }

  public ArrayList<Recipe> getRecipes() {
    return recipes;
  }
}
