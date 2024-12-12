package edu.ntnu.idi.idatt.modules;

import java.util.HashMap;
import java.util.Set;

/**
 * The Recipe class.
 *
 * @author bjberild
 * @version 0.5
 * @since 0.2
 */
public class Recipe {
  private final String name;
  private final String description;
  private final String instructions;
  private final HashMap<String, Double> recipeGroceries;
  private final int portions;

  /**
   * Basic constructor for the Recipe class that assigns the given parameters to the associated
   * parameters.
   *
   * @param name a String representing the name of the dish the recipe is for.
   * @param description a String meant to contain short description of the associated dish
   * @param instructions a String containing the instructions to make the recipe
   * @param recipeGroceries a HashMap representing the groceries needed for the recipe by their
   *                        names and needed amount.
   * @param portions an int representing the amount of portions the recipe is for.
   */
  public Recipe(String name, String description, String instructions,
      HashMap<String, Double> recipeGroceries, int portions) {
    this.name = name;
    this.description = description;
    this.instructions = instructions;
    this.recipeGroceries = recipeGroceries;
    this.portions = portions;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getInstructions() {
    return instructions;
  }

  public HashMap<String, Double> getRecipeGroceries() {
    return recipeGroceries;
  }

  public int getPortions() {
    return portions;
  }

}
