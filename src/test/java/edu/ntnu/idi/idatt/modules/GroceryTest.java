package edu.ntnu.idi.idatt.modules;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GroceryTest {
  Grocery grocery;

  @BeforeEach
  void setUp() {
    grocery = new Grocery("eggs", "pieces");
    GroceryInstance groceryInstance = new GroceryInstance(3, 18);
    GroceryInstance groceryInstance2 = new GroceryInstance(3, 0);
    LocalDate date = LocalDate.of(2020, 1, 1);
    LocalDate date2 = LocalDate.of(2026, 1, 2);
    grocery.addExpiryDate(date, groceryInstance);
  }

  @Test
  void getName() {
    assertEquals("eggs", grocery.getName());
  }
}