package edu.ntnu.idi.idatt.modules;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * The FoodStorage class manages a collection of {@code Grocery} objects.
 *
 * @author bjberild
 * @version 0.4
 * @since 0.2
 */
public class FoodStorage {
  private final TreeMap<String, Grocery> groceries = new TreeMap<>();

  public void addGrocery(String key, Grocery grocery) {
    groceries.put(key, grocery);
  }

  public void removeGrocery(String key) {
    groceries.remove(key);
  }

  public TreeMap<String, Grocery> getGroceries() {
    return groceries;
  }

  /**
   * Retrieves a Grocery object based on a given String search parameter.
   *
   * @param searchString The name to be searched for.
   * @return a Grocery object if found. otherwise returns null.
   */
  public Grocery searchGroceries(String searchString) {
    return groceries.getOrDefault(searchString, null);
  }

  /**
   * Retrieves all expired groceries in the TreeMap by making a copy of the original TreeMap
   * and removing any Keys past today's LocalDate.
   *
   * @return a TreeMap of Grocery objects with their names as keys.
   */
  public TreeMap<String, Grocery> getExpiredGroceries() {
    TreeMap<String, Grocery> expiredGroceries = new TreeMap<>();
    expiredGroceries.putAll(groceries);
    expiredGroceries.values().stream()
        .peek(grocery -> grocery.getExpiryDates().tailMap(LocalDate.now()).clear())
        .peek(Grocery::updateTotalAmount)
        .forEach(Grocery::updateTotalPrice);
    return expiredGroceries;
  }

  /**
   * Removes the expired Groceries based on today's date.
   */
  public void removeExpiredGroceries() {
    Collection<Grocery> tempGroceries = groceries.values();
    for (Grocery grocery : tempGroceries) {
      SortedMap<LocalDate, GroceryInstance> dates
          = grocery.getExpiryDates().headMap(LocalDate.now());
      Set<LocalDate> keys = dates.keySet();
      for (LocalDate key : keys) {
        grocery.removeExpiryDate(key);
      }
      groceries.replace(grocery.getName(), grocery);
    }
  }

  /**
   * Sums the prices of all given groceries and return the total.
   *
   * @param groceries An {@code ArrayList} of {@code Grocery} objects.
   * @return a double with the total price of given groceries.
   */
  public double totalGroceriesValue(Collection<Grocery> groceries) {
    double total = 0;
    for (Grocery grocery : groceries) {
      grocery.updateGrocery();
      total += grocery.getTotalPrice();
    }
    return total;
  }

  public double totalFoodStorageValue() {
    return totalGroceriesValue(groceries.values());
  }

}
