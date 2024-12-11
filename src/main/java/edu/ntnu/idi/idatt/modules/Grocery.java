package edu.ntnu.idi.idatt.modules;

import java.time.LocalDate;
import java.util.Collection;
import java.util.TreeMap;

/**
 * Class representing a given type of grocery/ingredient,
 * with information regarding its name, unit, totalAmount, totalPrice and a
 * TreeMap of expiryDates with amount and price values through the {@code GroceryInstance} class.
 *
 * <p>Contains a constructor to create a grocery.
 * Has getters for each parameter and setters for unit and totalAmount.</p>
 *
 * @author bjberild
 * @version 0.4
 * @since 0.1
 */
public class Grocery {
  private final String name;
  private final String unit;
  private double totalAmount = 0;
  private double totalPrice = 0;
  /* Using a Treemap where LocalDate objects acts as keys so they are automatically sorted by
   * lowest/earliest date. The values are a custom class for the purpose of having a pair of values
   * attached to the same key/date in the Treemap.
   */
  protected TreeMap<LocalDate, GroceryInstance> expiryDates;

  /**
   * Constructor for the Grocery class.
   *
   * @param name is the name of the grocery.
   * @param unit is String declaring a given unit that grocery will be measured as, e.g. grams or
   *             liters.
   * @param amount is a double for the totalAmount of the given unit.
   * @param price is a double representing the price per unit the grocery was bought for.
   * @param expiryDate is a {@code LocalDate} representing the expiry date of the grocery.
   */
  public Grocery(String name, String unit, double amount, double price, LocalDate expiryDate) {
    this.name = name;
    this.unit = unit;
    this.expiryDates = new TreeMap<LocalDate, GroceryInstance>();
  }

  public String getName() {
    return name;
  }

  public String getUnit() {
    return unit;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public TreeMap<LocalDate, GroceryInstance> getExpiryDates() {
    return expiryDates;
  }


  /**
   * Updates the totalAmount attribute by getting the values from the expiryDates TreeMap and
   * summing the amount attributes of the instances.
   */
  public void updateTotalAmount() {
    double totalAmount = 0;
    Collection<GroceryInstance> instances = expiryDates.values();
    for (GroceryInstance instance : instances) {
      totalAmount += instance.getAmount();
    }
    this.totalAmount = totalAmount;
  }

  /**
   * Updates the totalPrice attribute by getting the values from the expiryDates TreeMap and
   * multiplying them together.
   */
  public void updateTotalPrice() {
    double totalPrice = 0;
    Collection<GroceryInstance> instances = expiryDates.values();
    for (GroceryInstance instance : instances) {
      totalPrice += instance.getAmount() * instance.getPricePerUnit();
    }
    this.totalPrice = totalPrice;
  }

  public void removeExpiryDate(LocalDate expiryDate) {
    expiryDates.remove(expiryDate);
  }

  @Override
  public String toString() {
    return "Grocery{" + "name='" + name + '\'' + ", unit='" + unit + '\'' + ", totalAmount="
        + totalAmount + ", totalPrice=" + totalPrice + ", expiryDates=" + expiryDates + '}';
  }
}
