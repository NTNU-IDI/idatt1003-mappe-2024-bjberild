package edu.ntnu.idi.idatt.modules;

import static edu.ntnu.idi.idatt.utils.Utils.verifyStringParameter;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Class representing a given type of grocery/ingredient,
 * with information regarding its name, unit, totalAmount, totalPrice and a
 * TreeMap of expiryDates with amount and price values through the {@code GroceryInstance} class.
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
  private final TreeMap<LocalDate, GroceryInstance> expiryDates = new TreeMap<>();

  /**
   * Constructor for the Grocery class.
   *
   * @param name a String for the name of the grocery.
   * @param unit a String declaring a given unit that grocery will be measured as, e.g. kilograms,
   *             pieces or liters.
   */
  public Grocery(String name, String unit) {
    verifyStringParameter(name, "Name");
    verifyStringParameter(unit, "Unit");
    this.name = name;
    this.unit = unit;
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

  public void updateGrocery() {
    updateTotalAmount();
    updateTotalPrice();
  }

  public void addExpiryDate(LocalDate date, GroceryInstance instance) {
    expiryDates.put(date, instance);
  }

  public void removeExpiryDate(LocalDate expiryDate) {
    expiryDates.remove(expiryDate);
  }

  /**
   * Method that removes empty instances of the grocery.
   */
  public void removeEmptyExpiryDate() {
    Iterator<LocalDate> iterator = expiryDates.keySet().iterator();
    while (iterator.hasNext()) {
      LocalDate expiryDate = iterator.next();
      GroceryInstance instance = expiryDates.get(expiryDate);
      if (instance.getAmount() <= 0) {
        iterator.remove();
      }
    }
  }

  /**
   * Outputs a String for the Grocery object which is meant to be printed out.
   */
  public String printableGroceryString() {
    updateGrocery();
    return ("Name: " + name + " Unit: " + unit + " Amount: " + totalAmount
        + " Price: " + totalPrice);
  }

  @Override
  public String toString() {
    return "Grocery{" + "name='" + name + '\'' + ", unit='" + unit + '\'' + ", totalAmount="
        + totalAmount + ", totalPrice=" + totalPrice + ", expiryDates=" + expiryDates + '}';
  }
}
