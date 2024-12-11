package edu.ntnu.idi.idatt.modules;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * The FoodStorage class manages a collection of {@code Grocery} objects.
 *
 * @author bjberild
 * @version 0.2
 * @since 0.2
 */
public class FoodStorage {
  private final ArrayList<Grocery> groceries = new ArrayList<>();

  public void addGrocery(Grocery grocery) {
    groceries.add(grocery);
  }

  public void removeGrocery(Grocery grocery) {
    groceries.remove(grocery);
  }

  /**
   * Retrieves a list of groceries with names matching the search parameter.
   *
   * @param searchString The name to be searched for.
   * @return An ArrayList of {@code Grocery} objects with names matching the searchString.
   */
  public ArrayList<Grocery> searchGroceries(String searchString) {
    sortGroceriesByDate();
    return (ArrayList<Grocery>) groceries.stream()
        .filter(grocery -> grocery.getName().equals(searchString))
        .collect(Collectors.toList());
  }

  /**
   * Retrieves a list of groceries containing all expired groceries based on today's date.
   *
   * @return An ArrayList of {@code Grocery} objects with dates before today's date.
   */
  public ArrayList<Grocery> getExpiredGroceries() {
    sortGroceriesByDate();
    return (ArrayList<Grocery>) groceries.stream()
        .filter(grocery -> grocery.getExpiryDate().isBefore(LocalDate.now()))
        .collect(Collectors.toList());
  }

  /**
   * Sums the prices of all given groceries and return the total.
   *
   * @param groceries An {@code ArrayList} of {@code Grocery} objects.
   * @return a double with the total price of given groceries.
   */
  public double totalGroceriesValue(ArrayList<Grocery> groceries) {
    double total = 0;
    for (Grocery grocery : groceries) {
      total += grocery.getPrice();
    }
    return total;
  }

  private void sortGroceriesByDate() {
    groceries.sort(Comparator.comparing(Grocery::getExpiryDate));
  }

  private void sortGroceriesByName() {
    groceries.sort(Comparator.comparing(Grocery::getName));
  }

}
