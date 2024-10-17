package edu.grinnell.csc207.util;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * e A variety of tests for the Matrix class.
 *
 * @author Alex Pollock
 */
public class StudentTests {
  /**
   * Looking at a 0X0 matrix and testing equality
   */
  @Test
  public void testZeroByZero() {
    Matrix<Integer> zeroByZero = new MatrixV0<Integer>(0, 0);
    Matrix<Integer> oneByOne = new MatrixV0<Integer>(1, 1);
    oneByOne.deleteCol(0);
    oneByOne.deleteRow(0);
    assertTrue(zeroByZero.equals(oneByOne), "A 0X0 matrix should equal a (1-1)X(1-1) matrix");
    zeroByZero.insertCol(0);
    zeroByZero.insertRow(0);
    assertTrue(zeroByZero.width() == 1 && zeroByZero.height() == 1,
        "A (0+1)X(0+1) matrix should have a height and width of 1");
    try {
      zeroByZero.insertCol(0, new Integer[] {1});
    } catch (ArraySizeException e) {
      fail();
    } // try/catch
    assertEquals(1, zeroByZero.get(0, 0), "The value at (0,0) should be 1");

    try {
      zeroByZero.set(1, 1, 1);
      fail();
    } catch (IndexOutOfBoundsException e) {
      // This should happen
    } // try/catch

    try {
      zeroByZero.insertRow(0, new Integer[] {2, 3});
    } catch (ArraySizeException e) {
      fail();
    } // try/catch

    try {
      zeroByZero.deleteRow(1);
    } catch (IndexOutOfBoundsException e) {
      fail();
    } // try/catch

    try {
      zeroByZero.deleteCol(1);
    } catch (IndexOutOfBoundsException e) {
      fail();
    } // try/catch

    assertEquals(2, zeroByZero.get(0, 0), "The value at (0,0) should be 2");

    try {
      zeroByZero.insertRow(1);
      zeroByZero.insertRow(1);
      zeroByZero.insertRow(1);
      zeroByZero.insertRow(1);
      zeroByZero.insertCol(1);
      zeroByZero.insertCol(1);
      zeroByZero.insertCol(1);
      zeroByZero.insertCol(1);
    } catch (IndexOutOfBoundsException e) {
      fail();
    } // try/catch

    assertTrue(zeroByZero.height() == 5 && zeroByZero.width() == 5,
        "The width and height should now be 5X5");

    zeroByZero.fillLine(0, 0, 1, 1, 5, 5, 1);

    for (int i = 0; i<zeroByZero.height(); i++){
      assertTrue(zeroByZero.get(i, i) == 1);
    }

    zeroByZero.fillRegion(0, 0, zeroByZero.height(), zeroByZero.width(), 2);
    for (int r = 0; r<zeroByZero.height(); r++){
      for (int c = 0; c<zeroByZero.width(); c++){
        assertTrue(zeroByZero.get(r, c) == 2, "Everything should now be 2");
      } // for
    } // for

    Matrix<Integer> newMatrix = zeroByZero.clone();
    assertEquals(zeroByZero, newMatrix, "A clone should equal the original matrix");


  } // testZeroByZero()

} // class StudentTests
