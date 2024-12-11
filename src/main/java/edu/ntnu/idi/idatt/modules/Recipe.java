package edu.ntnu.idi.idatt.modules;


import java.util.ArrayList;

/**
 * The Recipe class.
 *
 * @author bjberild
 * @version 0.2
 * @since 0.2
 */
public class Recipe {
  private String name;
  private String description;
  private String instructions;
  private ArrayList<Grocery> groceries = new ArrayList<>();
}
