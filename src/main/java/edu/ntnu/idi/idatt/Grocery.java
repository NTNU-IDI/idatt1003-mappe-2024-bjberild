package edu.ntnu.idi.idatt;

import java.time.LocalDate;

/**
 * Class representing a given grocery/ingredient, with information regarding its name, amount, unit,
 * price and expiry date.
 *
 * <p>Contains a constructor to create a grocery.
 * Has getters for each parameter and setters for unit and amount.</p>
 *
 * @author bjberild
 * @version 0.1
 * @since 0.1
 */
public class Grocery {
  private String name;
  private String unit;
  private double amount;
  private double price;
  private LocalDate expiryDate;

  /**
   * .Constructor for the Grocery class.
   *
   * @param name is the name of the grocery.
   * @param unit is String declaring a given unit that grocery will be measured as, e.g. grams or
   *             milliliters.
   * @param amount is a double the amount of the given unit.
   * @param price is a double representing the price per unit the grocery was bought for.
   * @param expiryDate is the expiry date of the grocery.
   */
  public Grocery(String name, String unit, double amount, double price, LocalDate expiryDate) {
    this.name = name;
    this.unit = unit;
    this.amount = amount;
    this.price = price;
    this.expiryDate = expiryDate;
  }
}
