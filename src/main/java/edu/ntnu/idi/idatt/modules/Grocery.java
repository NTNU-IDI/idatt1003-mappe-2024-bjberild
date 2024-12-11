package edu.ntnu.idi.idatt.modules;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeMap;

/**
 * Class representing a given type of grocery/ingredient,
 * with information regarding its name, totalAmount, unit, price and a
 * TreeMap of expiryDates with associated amounts.
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
  private double totalAmount;
  private final double price;
  // Using a Treemap to sort by expiryDates with their associated amounts
  private final TreeMap<LocalDate, Double> expiryDates;

  /**
   * .Constructor for the Grocery class. Only used if a Grocery of the same name doesn't exist.
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
    this.totalAmount = amount;
    this.price = price;
    this.expiryDates = new TreeMap<LocalDate, Double>();
    this.expiryDates.put(expiryDate, amount);
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

  public double getPrice() {
    return price;
  }

  public TreeMap<LocalDate, Double> getExpiryDates() {
    return expiryDates;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }

  @Override
  public String toString() {
    return "Grocery{" + "name='" + name + '\'' + ", unit='" + unit + '\'' + ", totalAmount="
        + totalAmount + ", price=" + price + ", expiryDates=" + expiryDates.keySet() + '}';
  }
}
