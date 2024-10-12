package edu.grinnell.csc207.util;


/**
 * An implementation of two-dimensional matrices.
 *
 * @author Alexander Pollock
 * @author Samuel A. Rebelsky
 *
 * @param <T> The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * The height.
   */
  private int h; // The height of the matrix

  /**
   * The width.
   */
  private int w; // The width of the matrix

  /**
   * The default value.
   */
  private T defaultVal; // The default value

  /**
   * The inputed locations and associated values. (row,col) -> val
   */
  private AssociativeArray<KVPair<Integer, Integer>, T> values;


  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the given value as the default.
   *
   * @param width The width of the matrix.
   * @param height The height of the matrix.
   * @param def The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException If either the width or height are negative.
   */
  @SuppressWarnings({"unchecked"})
  public MatrixV0(int width, int height, T def) {
    this.w = width;
    this.h = height;
    this.defaultVal = def;
    this.values = new AssociativeArray<KVPair<Integer, Integer>, T>();
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with null as the default value.
   *
   * @param width The width of the matrix.
   * @param height The height of the matrix.
   *
   * @throws NegativeArraySizeException If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row The row of the element.
   * @param col The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    if (row > this.w || col > this.h) {
      throw new IndexOutOfBoundsException();
    } // if
    try {
      KVPair<Integer, Integer> toFind = new KVPair<Integer, Integer>(row, col);
      return values.get(toFind);
    } catch (Exception e) {
      return defaultVal;
    } // try/catch
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row The row of the element.
   * @param col The column of the element.
   * @param val The value to set.
   *
   * @throws IndexOutOfBoundsException If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (row > this.w || col > this.h) {
      throw new IndexOutOfBoundsException();
    } // if
    KVPair<Integer, Integer> toAdd = new KVPair<Integer, Integer>(row, col);
    try {
      this.values.set(toAdd, val);
    } catch (Exception e) {
      System.err.println("Failed to add value at row: " + row + " col: " + col);
    } // try/catch
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.h;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.w;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if (row < 0 || row > this.h) {
      throw new IndexOutOfBoundsException();
    } // if
    try {
      for (int i = 0; i < this.w; i++) {
        KVPair<Integer, Integer> toAdd = new KVPair<Integer, Integer>(row, i);
        this.values.set(toAdd, defaultVal);
      } // for
    } catch (Exception e) {
      System.err.println("Failed to add row: " + row);
    } // try/catch
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row The number of the row to insert.
   * @param vals The values to insert.
   *
   * @throws IndexOutOfBoundsException If the row is negative or greater than the height.
   * @throws ArraySizeException If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (row < 0 || row > this.h) {
      throw new IndexOutOfBoundsException();
    } // if
    if (vals.length > this.w) {
      throw new ArraySizeException();
    } // if
    try {
      for (int i = 0; i < this.w; i++) {
        KVPair<Integer, Integer> toAdd = new KVPair<Integer, Integer>(row, i);
        this.values.set(toAdd, vals[i]);
      } // for
    } catch (Exception e) {
      System.err.println("Failed to add row: " + row);
    } // try/catch
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if (col < 0 || col > this.w) {
      throw new IndexOutOfBoundsException();
    } // if
    try {
      for (int i = 0; i < this.h; i++) {
        KVPair<Integer, Integer> toAdd = new KVPair<Integer, Integer>(i, col);
        this.values.set(toAdd, defaultVal);
      } // for
    } catch (Exception e) {
      System.err.println("Failed to add row: " + col);
    } // try/catch
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col The number of the column to insert.
   * @param vals The values to insert.
   *
   * @throws IndexOutOfBoundsException If the column is negative or greater than the width.
   * @throws ArraySizeException If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (col < 0 || col > this.w) {
      throw new IndexOutOfBoundsException();
    } // if
    if (vals.length > this.h) {
      throw new ArraySizeException();
    } // if
    try {
      for (int i = 0; i < this.h; i++) {
        KVPair<Integer, Integer> toAdd = new KVPair<Integer, Integer>(i, col);
        this.values.set(toAdd, vals[i]);
      } // for
    } catch (Exception e) {
      System.err.println("Failed to add row: " + col);
    } // try/catch
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException If the row is negative or greater than or equal to the
   *         height.
   */
  public void deleteRow(int row) {
    if (row < 0 || row >= this.h) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int i = row + 1; i < this.h; i++) {
      for (int n = 0; n < this.w; n++) {
        if (values.hasKey(new KVPair<>(i, n))) {
          try {
            values.set(new KVPair<>(i - 1, n), values.get(new KVPair<>(i, n)));
          } catch (Exception e) {
            System.err.println("Failed to remove row: " + row);
          } // try/catch
        } // if
      } // for
    } // for
    insertRow(row);
    this.h--;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException If the column is negative or greater than or equal to the
   *         width.
   */
  public void deleteCol(int col) {
    if (col < 0 || col >= this.w) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int i = col + 1; i < this.w; i++) {
      for (int n = 0; n < this.h; n++) {
        if (values.hasKey(new KVPair<>(n, i))) {
          try {
            values.set(new KVPair<>(n, i - 1), values.get(new KVPair<>(n, i - 1)));
          } catch (Exception e) {
            System.err.println("Failed to remove col: " + col);
          } // try/catch
        } // if
      } // for
    } // for
    insertCol(col);
    this.w--;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow The top edge / row to start with (inclusive).
   * @param startCol The left edge / column to start with (inclusive).
   * @param endRow The bottom edge / row to stop with (exclusive).
   * @param endCol The right edge / column to stop with (exclusive).
   * @param val The value to store.
   *
   * @throw IndexOutOfBoundsException If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol, T val) {
    if (startCol < 0 || startCol > this.w || startRow < 0 || startRow > this.h) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int r = startRow; r < endRow; r++) {
      for (int c = startCol; c < endCol; c++) {
        try {
          values.set(new KVPair<>(r, c), val);
        } catch (NullKeyException e) {
          System.err.println("Failed to add element at row: " + r + " col: " + c);
        } // try/catch
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow The row to start with (inclusive).
   * @param startCol The column to start with (inclusive).
   * @param deltaRow How much to change the row in each step.
   * @param deltaCol How much to change the column in each step.
   * @param endRow The row to stop with (exclusive).
   * @param endCol The column to stop with (exclusive).
   * @param val The value to store.
   *
   * @throw IndexOutOfBoundsException If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol, int endRow,
      int endCol, T val) {
    if (startCol < 0 || startCol > this.w || startRow < 0 || startRow > this.h) {
      throw new IndexOutOfBoundsException();
    } // if
    int r = startRow;
    int c = startCol;
    while (r < endRow && c < endCol) {
      try {
        values.set(new KVPair<>(r, c), val);
      } catch (NullKeyException e) {
        System.err.println("Failed to add value");
      } // try/catch
      r += deltaRow;
      c += deltaCol;
    } // while
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual elements are mutable,
   * mutating them in one matrix may affect the other matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    MatrixV0<T> cloned = new MatrixV0<T>(this.w, this.h, this.defaultVal);
    cloned.values = this.values;
    return cloned;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other The object to compare.
   *
   * @return true if the other object is a matrix with the same width, height, and equal elements;
   *         false otherwise.
   */
  public boolean equals(Object other) {
    return this == other; // STUB
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object that implements `equals` is
   * expected to implement `hashCode` and ensure that the hash codes for two equal objects are the
   * same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
