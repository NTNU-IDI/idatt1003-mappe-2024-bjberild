package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GroceryTest {

  @Test
  void testToString() {
    Grocery grocery = new Grocery("Kjøtt", 10);

    assertEquals("Kjøtt 10", grocery.toString());

  }

  @Test
  void nyTest() {

  }
}