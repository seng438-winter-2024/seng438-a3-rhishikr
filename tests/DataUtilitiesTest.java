package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.Arrays;

import org.junit.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jmock.*;
import org.jmock.integration.junit4.JUnit4Mockery;

public class DataUtilitiesTest {
	
	private static Mockery context = new JUnit4Mockery();
    private static Values2D values;

    @BeforeClass public static void setUpBeforeClass() throws Exception {
    	values = context.mock(Values2D.class);
    }


    @Before
    public void setUp() throws Exception { 
    }

/***	Start of Ethan's tests    ***/

    @Test
	 public void calculateColumnTotalWithNoRows() {
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(0));
	             allowing(values).getColumnCount();
	             will(returnValue(1));
	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     assertEquals("", 0, result, .000000001d);
    }
    
    @Test
	 public void calculateColumnTotalSingleRow() {
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getRowCount();
	             will(returnValue(1));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     assertEquals("", 7.5, result, .000000001d);
	}
    
    @Test
	 public void calculateColumnTotalMultiRowPositive() {
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 1);
	             will(returnValue(10.0));
	             one(values).getValue(1, 1);
	             will(returnValue(15.5));
	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, 1);
	     assertEquals("calling calculateColumnTotal(Values2D, 1) with zero rows in data", 25.5, result, .000000001d);
	}
    
    @Test
	 public void calculateColumnTotalMultiRowNegative() {
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
		     will(returnValue(2));
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 1);
	             will(returnValue(-5.0));
	             one(values).getValue(1, 1);
	             will(returnValue(-8.5));
	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, 1);
	     assertEquals("Calling calculateColumnTotal(Values2D, 1) with negative expected value", -13.5, result, .000000001d);
	}
    
    @Test
	 public void calculateColumnTotalMultiRowZero() {
	     Mockery mockingContext = new Mockery();
	     final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getRowCount();
	             will(returnValue(2));
	             one(values).getValue(0, 1);
	             will(returnValue(-5.0));
	             one(values).getValue(1, 1);
	             will(returnValue(5.0));
	         }
	     });
	     double result = DataUtilities.calculateColumnTotal(values, 1);
	     assertEquals("Calling calculateColumnTotal(Values2D, 1) with expected value of zero", 0.0, result, .000000001d);
	}
    
    @Test
	public void calculateColumnTotalNullData() {
    	
            try {
    		DataUtilities.calculateColumnTotal(null, 1);
	    // Verify that proper exception is thrown
    	    } catch (InvalidParameterException e) {
    		return;
    	    } catch (Exception e) { // Incorrect Exception type
    		fail("calculateColumnTotal(null, 1) threw " + e.toString() + " instead of InvalidParameterException");
    	    }
	    // If this point is reached, no exception was thrown
	    fail("calculateColumnTotal(null, 1) did not throw an exception with null data");
	}
    
    @Test
	public void calculateColumnTotalColumnOutOfRange() {
    	
    	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	        	 one(values).getColumnCount();
	        	 will(returnValue(5));
			// Allowing these method calls so that the test does not end prematurely
	        	 allowing(values).getRowCount();
	        	 will(returnValue(1));
	        	 allowing(values).getValue(0, 5);
	        	 will(returnValue(0.0));
                }
	    });
	    // Verify that proper exception is thrown
    	    try {
    		DataUtilities.calculateColumnTotal(values, 5);
   	    } catch (InvalidParameterException e) { // Expected behaviour
    		return;
    	    } catch (Exception e) { // Check for incorrect Exception type
    		fail("calculateColumnTotal(Values2D, 5) threw " + e.toString() + " instead of InvalidParameterException");
    	    }
	    // If this point is reached, no exception was thrown
	    fail("calculateColumnTotal(Values2D, 5) did not throw an exception with out of range column parameter");
	}
    
    @Test
	public void calculateColumnTotalColumnNegative() {
   	
   	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
			
	        	 one(values).getColumnCount();
	        	 will(returnValue(5));
			// Allowing these method calls so that the test does not end prematurely
	        	 allowing(values).getRowCount();
	        	 will(returnValue(1));
	        	 allowing(values).getValue(0, -1);
	        	 will(returnValue(0.0)); 
	        }
	    });
	    // Verify that proper exception is thrown
   	    try {
   		DataUtilities.calculateColumnTotal(values, -1);
   	    } catch (InvalidParameterException e) { // Expected behaviour
   		return;
   	    } catch (Exception e) { // Check for incorrect Exception type
   		fail("calculateColumnTotal(Values2D, -1) threw " + e.toString() + " instead of InvalidParameterException");
   	    }
	    // If this point is reached, no exception was thrown
	    fail("calculateColumnTotal(Values2D, -1) did not throw an exception with negative column parameter");
	}
    
    /***    End of Ethan's tests    ***/

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

	/**   Start of Kaniz's test 
		 * This test will simulate creating an empty Values2D table. The empty table is
		 * passed to calculateRowTotal() with a row number of 0 and expects that the
		 * total of the values in the row equal to 0.
		 */
		@Test
		public void calculateRowTotalEmptyChart() {
			Mockery mockingContext = new Mockery();
			// creating a new mock object called mockingContext
			final Values2D values = mockingContext.mock(Values2D.class);
			// mock object (mockingContext) is stored in the local variable 'values'
			// 'values' is final so it can be referred to from within expectation blocks

			mockingContext.checking(new Expectations() {
				// a mock expectation block containing expectations of value
				{
					one(values).getColumnCount();
					// invocation of getColumnCount() is expected once
					will(returnValue(0));
					// will always returns 0 when getColumnCount() is called

					one(values).getRowCount();
					// invocation of getRowCount is expected once
					will(returnValue(0));
					// will always returns 0 when getRowCount is called
				}
			});
			int rowNumber = 0; // setting rowNumber to have an int value of 0
			double result = DataUtilities.calculateRowTotal(values, rowNumber);
			// calling calculateRowTotal with values and rowNumber
			assertEquals("The row total is adding up to 0", 0, result, .000000001d);
			// asserting the result adds up to 0
		}
	
	/**
	 * This test will simulate passing a null object to calculateRowTotal() with a
	 * row number of 0 and expects that an IllegalArgumentException is thrown.
	 */
	@Test
	public void calculateRowTotalNull() {
		try {
			DataUtilities.calculateRowTotal(null, 0);
			// calling calculateRowTotal() with a null object
			fail("This method should throw an exception!");
			// creating a failure message for if createNumberArray2D does not throw an
			// exception
		} catch (Exception e) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					e.getClass());
			// catching the exception, asserting that an IllegalArgumentException was thrown
		}
		
	}
	
	/**
	 * This test will simulate creating a Values2D table with positive values. The
	 * table is passed to calculateRowTotal() with a row number of 1 and expects
	 * that the total of the values in the row is correct.
	 */
	@Test
	public void calculateRowTotalPositive() {
		Mockery mockingContext = new Mockery();
		// creating a new mock object called mockingContext
		final Values2D values = mockingContext.mock(Values2D.class);
		// mock object (mockingContext) is stored in the local variable 'values'
		// 'values' is final so it can be referred to from within expectation blocks

		mockingContext.checking(new Expectations() {
			// a mock expectation block containing expectations of value
			{
				one(values).getColumnCount();
				// invocation of getColumnCount() is expected once
				will(returnValue(4));
				// will always returns 4 when getColumnCount() is called

				one(values).getValue(1, 0);
				// invocation of getValue(1, 0) is expected once
				will(returnValue(1.0));
				// will always returns 1 when getValue(1, 0) is called

				one(values).getValue(1, 1);
				// invocation of getValue(1, 1) is expected once
				will(returnValue(2.0));
				// will always returns 2 when getValue(1, 1) is called

				one(values).getValue(1, 2);
				// invocation of getValue(1, 2) is expected once
				will(returnValue(3.0));
				// will always returns 3 when getValue(1, 2) is called

				one(values).getValue(1, 3);
				// invocation of getValue(1, 3) is expected once
				will(returnValue(4.0));
				// will always returns 4 when getValue(1, 3) is called
			}
		});
		int rowNumber = 1; // setting rowNumber to have an int value of 1
		double result = DataUtilities.calculateRowTotal(values, rowNumber);
		// calling calculateRowTotal with Values2D = values and at rowNumber 1
		assertEquals("The row total is adding up to 10", 10, result, .000000001d);
		// asserting the result adds up to 10 (1 + 2 + 3 + 4 = 10)
	}
	
	/**
	 * This test will simulate creating a Values2D table with negative values. The
	 * table is passed to calculateRowTotal() with a row number of 1 and expects
	 * that the total of the values in the row is correct.
	 */
	@Test
	public void calculateRowTotalNegative() {
		Mockery mockingContext = new Mockery();
		// creating a new mock object called mockingContext
		final Values2D values = mockingContext.mock(Values2D.class);
		// mock object (mockingContext) is stored in the local variable 'values'
		// 'values' is final so it can be referred to from within expectation blocks

		mockingContext.checking(new Expectations() {
			// a mock expectation block containing expectations of value
			{
				one(values).getColumnCount();
				// invocation of getColumnCount() is expected once
				will(returnValue(5));
				// will always returns 5 when getColumnCount() is called

				one(values).getValue(1, 0);
				// invocation of getValue(1, 0) is expected once
				will(returnValue(-1.0));
				// will always returns -1.0 when getValue(1, 0) is called

				one(values).getValue(1, 1);
				// invocation of getValue(1, 1) is expected once
				will(returnValue(-2.0));
				// will always returns -2.0 when getValue(1, 1) is called

				one(values).getValue(1, 2);
				// invocation of getValue(1, 2) is expected once
				will(returnValue(-3.0));
				// will always returns -3.0 when getValue(1, 2) is called

				one(values).getValue(1, 3);
				// invocation of getValue(1, 3) is expected once
				will(returnValue(-4.0));
				// will always returns -4.0 when getValue(1, 3) is called

				one(values).getValue(1, 4);
				// invocation of getValue(1, 4) is expected once
				will(returnValue(-5.0));
				// will always returns -5.0 when getValue(1, 4) is called
			}
		});
		int rowNumber = 1; // setting rowNumber to have an int value of 1
		double result = DataUtilities.calculateRowTotal(values, rowNumber);
		// calling calculateRowTotal with Values2D = values and at rowNumber 1
		assertEquals("The row total is adding up to -15", -15, result, .000000001d);
		// asserting the result adds up to 15
		// (-1) + (-2) + (-3) + (-4) + (-5) = (-15)
	}

	

	/**
	 * This test will simulate creating a Values2D table with a negative index.
	 * Expectation is that an exception should be thrown.
	 */
	@Test
	public void calculateRowTotalNegativeIndex() {
		Mockery mockingContext = new Mockery();
		// creating a new mock object called mockingContext
		final Values2D values = mockingContext.mock(Values2D.class);
		// mock object (mockingContext) is stored in the local variable 'values'
		// 'values' is final so it can be referred to from within expectation blocks

		mockingContext.checking(new Expectations() {
			// a mock expectation block containing expectations of value
			{
				one(values).getColumnCount();
				// invocation of getColumnCount() is expected once
				will(returnValue(2));
				// will always returns 5 when getColumnCount() is called

				one(values).getValue(-1, 0);
				// invocation of getValue(1, 0) is expected once
				will(returnValue(-1.0));
				// will always returns -1.0 when getValue(1, 0) is called

				one(values).getValue(-1, 1);
				// invocation of getValue(1, 1) is expected once
				will(returnValue(-2.0));
				// will always returns -2.0 when getValue(1, 1) is called
			}
		});

		int rowNumber = -1; // setting rowNumber to have an int value of -1
		try {
			DataUtilities.calculateRowTotal(values, rowNumber);
			// calling calculateRowTotal with Values2D = values and at rowNumber -1
			fail("This method should throw an exception!");
			// creating a failure message for if an exception is not thrown
		} catch (Exception e) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					e.getClass());
			// catching the exception, asserting that an IllegalArgumentException was thrown
		}
	}

	//END of Kaniz's test

	@Test
    public void testCreateNumberArray2DWithValidData() {
        // Valid input with multiple rows and columns
        double[][] validData = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        Number[][] result = DataUtilities.createNumberArray2D(validData);
        assertTrue("Result should not be null", result != null);
        assertTrue("Number of rows should match", result.length == 2);
        assertTrue("Number of columns in the first row should match", result[0].length == 3);
    }
// Start of Rhishik's Test
    @Test
    public void testCreateNumberArray2DWithNullData() {
        // Passing null data should return false
        assertFalse("Should throw InvalidParameterException", throwsException(() -> DataUtilities.createNumberArray2D(null)));
    }

    @Test
    public void testCreateNumberArray2DWithEmptyData() {
        // Passing empty data should return false
        double[][] emptyData = {};
        assertFalse("Should throw InvalidParameterException", throwsException(() -> DataUtilities.createNumberArray2D(emptyData)));
    }

    @Test
    public void testCreateNumberArray2DWithInvalidData() {
        // Passing data with inconsistent row lengths should return false
        double[][] invalidData = {{1.0, 2.0, 3.0}, {4.0, 5.0}};
        assertFalse("Should throw InvalidParameterException", throwsException(() -> DataUtilities.createNumberArray2D(invalidData)));
    }

    @Test
    public void testCreateNumberArray2DWithSingleRow() {
        // Valid input with a single row
        double[][] singleRowData = {{1.0, 2.0, 3.0}};
        Number[][] result = DataUtilities.createNumberArray2D(singleRowData);
        assertTrue("Result should not be null", result != null);
        assertTrue("Number of rows should match", result.length == 1);
        assertTrue("Number of columns in the first row should match", result[0].length == 3);
    }

    // Additional test cases can be added based on specific requirements

    // Helper method to check if an exception is thrown
    private static boolean throwsException(Runnable runnable) {
        try {
            runnable.run();
            return false;  // No exception thrown, return false
        } catch (Exception e) {
            return true;  // Exception thrown, return true
        }
    }

	@Test
	public void getCumulativePercentages() {
	    // create sample KeyedValues
	    DefaultKeyedValues testData = new DefaultKeyedValues();
	    testData.addValue("0", 5);
	    testData.addValue("1", 9);
	    testData.addValue("2", 2);

	    Mockery mockingContext = new Mockery();

	    final KeyedValues mockedKeyedValues = mockingContext.mock(KeyedValues.class);

	    mockingContext.checking(new Expectations() {{
	        allowing(mockedKeyedValues).getItemCount();
	        will(returnValue(testData.getItemCount()));
	        allowing(mockedKeyedValues).getKey(0);
	        will(returnValue(testData.getKey(0)));
	        allowing(mockedKeyedValues).getValue(0);
	        will(returnValue(testData.getValue(0)));
	        allowing(mockedKeyedValues).getKey(1);
	        will(returnValue(testData.getKey(1)));
	        allowing(mockedKeyedValues).getValue(1);
	        will(returnValue(testData.getValue(1)));
	        allowing(mockedKeyedValues).getKey(2);
	        will(returnValue(testData.getKey(2)));
	        allowing(mockedKeyedValues).getValue(2);
	        will(returnValue(testData.getValue(2)));
	    }});

	    KeyedValues result = DataUtilities.getCumulativePercentages(mockedKeyedValues);

	    assertNotNull("result should not be null", result);

	    assertEquals("cumulative percentage for '0' is incorrect", 0.3125, result.getValue("0"));
	    assertEquals("cumulative percentage for '1' is incorrect", 0.875, result.getValue("1"));
	    assertEquals("cumulative percentage for '2' is incorrect", 1.0, result.getValue("2"));
	}

	@Test
	public void getCumulativePercentagesWithEmptyData() {
	    // create an empty KeyedValues 
	    DefaultKeyedValues emptyData = new DefaultKeyedValues();
	    
	    Mockery mockingContext = new Mockery();

	    final KeyedValues mockedKeyedValues = mockingContext.mock(KeyedValues.class);

	    mockingContext.checking(new Expectations() {{
	        allowing(mockedKeyedValues).getItemCount();
	        will(returnValue(emptyData.getItemCount()));
	    }});

	    KeyedValues result = DataUtilities.getCumulativePercentages(mockedKeyedValues);

	    assertNotNull(result);
	    assertEquals(0, result.getItemCount());
	}

	@Test
	public void getCumulativePercentagesWithNullData() {
	    Mockery mockingContext = new Mockery();

	    final KeyedValues result = mockingContext.mock(KeyedValues.class);

	    mockingContext.checking(new Expectations() {{
	        allowing(result).getItemCount();
	        will(throwException(new InvalidParameterException("null 'data' argument.")));
	    }});

	    assertNull("result should be null for null data", result);
	}

	@Test
	public void createNumberArrayPositiveDouble() {
	    Number[] expectedArray = { 1.7, 2.2, 3.4 };
	    double[] arrayToPass = { 1.7, 2.2, 3.4 };
	    
	    // Call the method
	    Number[] actualArray = DataUtilities.createNumberArray(arrayToPass);

	    // Debugging output
	    System.out.println("Expected Array: " + Arrays.toString(expectedArray));
	    System.out.println("Actual Array: " + Arrays.toString(actualArray));

	    // Assert with a delta value
	    assertArrayEquals(expectedArray, actualArray); // Adjust delta value as needed
	}

	/**
	 * This test will simulate creating a 1D array of doubles and passing the object
	 * to a createNumberArray. The double array passed contains 3 negative double
	 * elements. The test asserts that the expected negative 3 element array of
	 * doubles is created.
	 */
	@Test
	public void createNumberArrayNegativeDouble() {
	    Number[] expectedArray = { -1.7, -2.2, -3.4 };
	    double[] arrayToPass = { -1.7, -2.2, -3.4 };
	    
	    // Call the method
	    Number[] actualArray = DataUtilities.createNumberArray(arrayToPass);

	    // Debugging output
	    System.out.println("Expected Array: " + Arrays.toString(expectedArray));
	    System.out.println("Actual Array: " + Arrays.toString(actualArray));

	    // Assert with a delta value
	    assertArrayEquals(expectedArray, actualArray); // Adjust delta value as needed
	}

	/**
	 * This test will simulate creating a null double array and passing the object
	 * to a createNumberArray. The test asserts that the operation will throw an
	 * exception.
	 */
	@Test
	public void createNumberArrayNull() {
		try {
			double[] arrayToPass = null;
			// creating a null double 1D array
			DataUtilities.createNumberArray(arrayToPass);
			// passing the null object to the createNumberArray2D function
			fail("This method should throw an exception!");
			// creating a failure message for if createNumberArray2D does not throw an
			// exception
		} catch (Exception e) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					e.getClass());
			// catching the exception, asserting that an IllegalArgumentException was thrown
		}
	}

	/**
	 * This test will simulate creating a 1D array of doubles and passing the object
	 * to a createNumberArray. The double array passed contains one elements of the
	 * value 0.0. The test asserts that the expected single element array of 0.0 is
	 * created.
	 */
	@Test
	public void createNumberArrayZero() {
        // Expected array
        Number[] expectedArray = {0.0};

        // Input array
        double[] arrayToPass = {0.0};

        // Call the method
        Number[] actualArray = DataUtilities.createNumberArray(arrayToPass);

        // Assert the result
        assertArrayEquals(expectedArray, actualArray);
    }

	/**
	 * This test will simulate creating a 1D array of doubles and passing the object
	 * to a createNumberArray. The double array passed is an empty array. The test
	 * asserts that the expected empty array of doubles is created.
	 */
	@Test
	public void createNumberArrayEmpty() {
		Number[] expectedArray = {};
		// creating the expected empty 1D array to be used in the assert
		double[] arrayToPass = {};
		// creating a 1D array of type double to pass to createNumberArray()
		Number[] actualArray = DataUtilities.createNumberArray(arrayToPass);
		// calling the function to test
		assertArrayEquals("The expected array should contain double values of {}", expectedArray, actualArray);
		// asserting the resulting Number array created matches the expected array
	}

	/**
	 * This test will simulate creating a 1D array of doubles and passing the object
	 * to a createNumberArray. The double array passed is an array with a positive
	 * and negative element. The test asserts that the expected array matching the
	 * passed array is created of type Number.
	 */
	@Test
	public void createNumberArrayNegativePositive() {
	    Number[] expectedArray = { 1.7, 2.2, -3.4 };
	    double[] arrayToPass = { 1.7, 2.2, -3.4 };
	    
	    // Call the method
	    Number[] actualArray = DataUtilities.createNumberArray(arrayToPass);

	    // Debugging output
	    System.out.println("Expected Array: " + Arrays.toString(expectedArray));
	    System.out.println("Actual Array: " + Arrays.toString(actualArray));

	    // Assert with a delta value
	    assertArrayEquals(expectedArray, actualArray); // Adjust delta value as needed
	}

	/**
	 * This test will simulate creating a 1D array of doubles and passing the object
	 * to a createNumberArray. The double array passed is an array containing 4
	 * elements of the max double value. The test asserts that the expected array of
	 * max positive doubles is created.
	 */
	@Test
	public void createNumberArrayMaxDoubleFour() {
		Number[] expectedArray = { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE };
		// creating the expected 1D array of max double values to be used in the assert
		double[] arrayToPass = { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE };
		// creating a 1D array of type double to pass to createNumberArray()
		Number[] actualArray = DataUtilities.createNumberArray(arrayToPass);
		// calling the function to test
		assertArrayEquals("The expected array should contain 4 elements of the max double value;", expectedArray,
				actualArray);
		// asserting the resulting Number array created matches the expected array
	}

	/**
	 * This test will simulate creating a 1D array of doubles and passing the object
	 * to a createNumberArray. The double array passed is an array containing 4
	 * elements of the minimum double value. The test asserts that the expected
	 * array of minimum negative doubles is created.
	 */
	@Test
	public void createNumberArrayMinDoubleFour() {
		Number[] expectedArray = { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE };
		// creating the expected 1D array of minimum double values to be used in the
		// assert
		double[] arrayToPass = { Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE };
		// creating a 1D array of type double to pass to createNumberArray()
		Number[] actualArray = DataUtilities.createNumberArray(arrayToPass);
		// calling the function to test
		assertArrayEquals("The expected array should contain 4 elements of the min double value;", expectedArray,
				actualArray);
		// asserting the resulting Number array created matches the expected array
	}
	
    
	@Test
	public void calculateRowTotalEmptyChart1() {
		Mockery mockingContext = new Mockery();
		// creating a new mock object called mockingContext
		final Values2D values = mockingContext.mock(Values2D.class);
		// mock object (mockingContext) is stored in the local variable 'values'
		// 'values' is final so it can be referred to from within expectation blocks

		mockingContext.checking(new Expectations() {
			// a mock expectation block containing expectations of value
			{
				one(values).getColumnCount();
				// invocation of getColumnCount() is expected once
				will(returnValue(0));
				// will always returns 0 when getColumnCount() is called

				one(values).getRowCount();
				// invocation of getRowCount is expected once
				will(returnValue(0));
				// will always returns 0 when getRowCount is called
			}
		});
		int rowNumber = 0; // setting rowNumber to have an int value of 0
		double result = DataUtilities.calculateRowTotal(values, rowNumber);
		// calling calculateRowTotal with values and rowNumber
		assertEquals("The row total is adding up to 0", 0, result, .000000001d);
		// asserting the result adds up to 0
	}
// End of Rhishik's test
	
	@Test
    public void testBothArraysNull_ShouldReturnTrue() {
        assertTrue("When both arrays are null, should return true.", DataUtilities.equal(null, null));
    }

    @Test
    public void testFirstArrayNullSecondNotNull_ShouldReturnFalse() {
        double[][] b = {{1.0}};
        assertFalse("When first array is null and second is not, should return false.", DataUtilities.equal(null, b));
    }

    @Test
    public void testFirstArrayNotNullSecondNull_ShouldReturnFalse() {
        double[][] a = {{1.0}};
        assertFalse("When first array is not null and second is null, should return false.", DataUtilities.equal(a, null));
    }

    @Test
    public void testArraysDifferentLengths_ShouldReturnFalse() {
        double[][] a = {{1.0}};
        double[][] b = {{1.0}, {2.0}};
        assertFalse("When arrays have different lengths, should return false.", DataUtilities.equal(a, b));
    }

    @Test
    public void testArraysSameLengthInnerArraysDiffer_ShouldReturnFalse() {
        double[][] a = {{1.0, 2.0}, {3.0}};
        double[][] b = {{1.0, 2.0}, {4.0}};
        assertFalse("When arrays are the same length but an inner array differs, should return false.", DataUtilities.equal(a, b));
    }

    @Test
    public void testIdenticalArrays_ShouldReturnTrue() {
        double[][] a = {{1.0, 2.0}, {3.0}};
        double[][] b = {{1.0, 2.0}, {3.0}};
        assertTrue("When both arrays are identical, should return true.", DataUtilities.equal(a, b));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCloneWithNullSource() {
        double[][] source = null;
        DataUtilities.clone(source);
    }

    @Test
    public void testCloneEmptyArray() {
        double[][] source = new double[0][];
        double[][] cloned = DataUtilities.clone(source);
        assertNotSame(source, cloned);
        assertEquals(0, cloned.length);
    }

    @Test
    public void testCloneArrayWithNullRows() {
        double[][] source = new double[3][];
        source[0] = new double[]{1.0, 2.0};
        source[1] = null;
        source[2] = new double[]{3.0, 4.0};
        
        double[][] cloned = DataUtilities.clone(source);
        assertNotSame(source, cloned);
        assertNotNull(cloned[0]);
        assertNull(cloned[1]);
        assertNotNull(cloned[2]);

        // Manually checking first and last element of non-null rows
        assertEquals("First row, first element mismatch", 1.0, cloned[0][0], 0.0001);
        assertEquals("First row, last element mismatch", 2.0, cloned[0][1], 0.0001);
        assertEquals("Third row, first element mismatch", 3.0, cloned[2][0], 0.0001);
        assertEquals("Third row, last element mismatch", 4.0, cloned[2][1], 0.0001);
    }

    @Test
    public void testCloneFullArray() {
        double[][] source = {{4.0, 5.0}, {6.0, 7.0}};
        
        double[][] cloned = DataUtilities.clone(source);
        assertNotSame(source, cloned);
        assertEquals("Array length mismatch", source.length, cloned.length);

        // Loop through each row and check first, last, and length
        for (int i = 0; i < source.length; i++) {
            assertNotSame(source[i], cloned[i]);
            assertEquals("Row length mismatch at index " + i, source[i].length, cloned[i].length);
            assertEquals("First element mismatch in row " + i, source[i][0], cloned[i][0], 0.0001);
            assertEquals("Last element mismatch in row " + i, source[i][source[i].length - 1], cloned[i][cloned[i].length - 1], 0.0001);
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateColumnTotalWithNullData() {
        DataUtilities.calculateColumnTotal(null, 0);
    }

    @Test
    public void testCalculateColumnTotalWithValidData() {
        context.checking(new Expectations() {{
            oneOf(values).getRowCount(); will(returnValue(3));
            oneOf(values).getValue(0, 1); will(returnValue(10));
            oneOf(values).getValue(1, 1); will(returnValue(20));
            oneOf(values).getValue(2, 1); will(returnValue(30));
        }});
        double total = DataUtilities.calculateColumnTotal(values, 1);
        assertEquals(60.0, total, 0.00001);
        context.assertIsSatisfied();
    }

    @Test
    public void testCalculateColumnTotalWithSomeNullValues() {
        context.checking(new Expectations() {{
            oneOf(values).getRowCount(); will(returnValue(3));
            oneOf(values).getValue(0, 1); will(returnValue(null));
            oneOf(values).getValue(1, 1); will(returnValue(20));
            oneOf(values).getValue(2, 1); will(returnValue(null));
        }});
        double total = DataUtilities.calculateColumnTotal(values, 1);
        assertEquals(20.0, total, 0.00001);
        context.assertIsSatisfied();
    }

    @Test
    public void testCalculateColumnTotalWithAllNullValues() {
        context.checking(new Expectations() {{
            oneOf(values).getRowCount(); will(returnValue(3));
            allowing(values).getValue(with(any(Integer.class)), with(any(Integer.class))); will(returnValue(null));
        }});
        double total = DataUtilities.calculateColumnTotal(values, 1);
        assertEquals(0.0, total, 0.00001);
        context.assertIsSatisfied();
    }

    @Test
    public void testCalculateColumnTotalWithEmptyDataSet() {
        context.checking(new Expectations() {{
            oneOf(values).getRowCount(); will(returnValue(0));
        }});
        double total = DataUtilities.calculateColumnTotal(values, 1);
        assertEquals(0.0, total, 0.00001);
        context.assertIsSatisfied();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateColumnTotalWithDataNull() {
        DataUtilities.calculateColumnTotal(null, 0, new int[]{0});
    }

    @Test
    public void testValidRowsOutsideRowCount() {
        context.checking(new Expectations() {{
            oneOf(values).getRowCount(); will(returnValue(3));
        }});
        int[] validRows = {3, 4};
        assertEquals(0.0, DataUtilities.calculateColumnTotal(values, 0, validRows), 0.0000001);
    }

    @Test
    public void testValidRowsWithinRowCountIncludingNullValues() {
        context.checking(new Expectations() {{
            oneOf(values).getRowCount(); will(returnValue(5));
            oneOf(values).getValue(1, 0); will(returnValue(10));
            oneOf(values).getValue(3, 0); will(returnValue(null));
        }});
        int[] validRows = {1, 3};
        assertEquals(10.0, DataUtilities.calculateColumnTotal(values, 0, validRows), 0.0000001);
    }

    @Test
    public void testValidRowsWithAllValidValues() {
        context.checking(new Expectations() {{
            oneOf(values).getRowCount(); will(returnValue(5));
            oneOf(values).getValue(1, 0); will(returnValue(10));
            oneOf(values).getValue(2, 0); will(returnValue(20));
        }});
        int[] validRows = {1, 2};
        assertEquals(30.0, DataUtilities.calculateColumnTotal(values, 0, validRows), 0.0000001);
    }

    @Test
    public void testEmptyValidRows() {
        context.checking(new Expectations() {{
            oneOf(values).getRowCount(); will(returnValue(5));
            // No need for further expectations as validRows is empty
        }});
        int[] validRows = {};
        assertEquals(0.0, DataUtilities.calculateColumnTotal(values, 0, validRows), 0.0000001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotalWithNullData() {
        DataUtilities.calculateRowTotal(null, 0, new int[]{0});
    }

    @Test
    public void testCalculateRowTotalWithEmptyValidCols() {
        context.checking(new Expectations() {{
            oneOf(values).getColumnCount(); will(returnValue(5));
        }});
        assertEquals(0.0, DataUtilities.calculateRowTotal(values, 0, new int[]{}), 0.0000001);
    }

    @Test
    public void testCalculateRowTotalWithAllValidCols() {
        context.checking(new Expectations() {{
            oneOf(values).getColumnCount(); will(returnValue(5));
            oneOf(values).getValue(0, 0); will(returnValue(10));
            oneOf(values).getValue(0, 1); will(returnValue(20));
        }});
        assertEquals(30.0, DataUtilities.calculateRowTotal(values, 0, new int[]{0, 1}), 0.0000001);
    }

    @Test
    public void testCalculateRowTotalWithSomeNullValues() {
        context.checking(new Expectations() {{
            oneOf(values).getColumnCount(); will(returnValue(5));
            oneOf(values).getValue(0, 0); will(returnValue(10));
            oneOf(values).getValue(0, 1); will(returnValue(null));
        }});
        assertEquals(10.0, DataUtilities.calculateRowTotal(values, 0, new int[]{0, 1}), 0.0000001);
    }

    @Test
    public void testCalculateRowTotalWithInvalidCols() {
        context.checking(new Expectations() {{
            allowing(values).getColumnCount(); will(returnValue(2)); // Only 2 columns are valid
            oneOf(values).getValue(0, 0); will(returnValue(10));
            // No need to mock the call for column index 2 as it's out of bounds
        }});
        assertEquals(10.0, DataUtilities.calculateRowTotal(values, 0, new int[]{0, 2}), 0.0000001); // Index 2 is out of valid range
    }
}
