package edu.ntnu.idi.idatt.modules;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * The FoodStorage class manages a collection of {@code Grocery} objects.
 *
 * @author bjberild
 * @version 0.4
 * @since 0.2
 */
public class FoodStorage {
  private final ArrayList<Grocery> groceries = new ArrayList<Grocery>();

  public void addGrocery(Grocery grocery) {
    groceries.add(grocery);
  }

  public void removeGrocery(Grocery grocery) {
    groceries.remove(grocery);
  }

  public ArrayList<Grocery> getGroceries() {
    return groceries;
  }

  /**
   * Retrieves a Grocery object based on a given String search parameter.
   *
   * @param searchString The name to be searched for.
   * @return a Grocery object if found. otherwise returns null.
   */
  public Grocery searchGroceries(String searchString) {
    return groceries.stream()
        .filter(grocery -> grocery.getName().equals(searchString))
        .findFirst().orElse(null);
  }

  /**
   * Retrieves an ArrayList of groceries containing all expired groceries based on today's LocalDate.
   *
   * @return An ArrayList of {@code Grocery} objects with dates before today's LocalDate.
   */
  public ArrayList<Grocery> getExpiredGroceries() {
    ArrayList<Grocery> expiredGroceries = groceries;
    expiredGroceries.stream().peek(grocery -> grocery.expiryDates.tailMap(LocalDate.now()).clear())
        .peek(Grocery::updateTotalAmount)
        .forEach(Grocery::updateTotalPrice);
    return expiredGroceries;
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
      total += grocery.getTotalPrice();
    }
    return total;
  }

  public double totalFoodStorageValue() {
    return totalGroceriesValue(groceries);
  }

  private void sortGroceriesByName() {
    groceries.sort(Comparator.comparing(Grocery::getName));
  }

}
