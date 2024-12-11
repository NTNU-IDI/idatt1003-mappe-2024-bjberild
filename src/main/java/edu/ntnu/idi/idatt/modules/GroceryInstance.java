package edu.ntnu.idi.idatt.modules;

/**
 * A simple class made to be used as a value in Maps so multiple values can be attached to one key.
 *
 * @author bjberild
 * @version 0.4
 * @since 0.4
 */
public class GroceryInstance {
  private double pricePerUnit = 0;
  private double amount = 0;

  /**
   * A constructor for the GroceryInstance class. Takes in the values amount and price per unit.
   *
   * @param pricePerUnit is a double representing the price per unit of this instance of groceries
   * @param amount is a double representing the amount
   */
  public GroceryInstance(double pricePerUnit, double amount) {
    this.pricePerUnit = pricePerUnit;
    this.amount = amount;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getPricePerUnit() {
    return pricePerUnit;
  }

  public void setPricePerUnit(double pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }
}
