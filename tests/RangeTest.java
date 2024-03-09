package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {

	private Range exampleRange; // The example range which was given to us
	private Range testRange1; // Created this range to test other functionalities of the test range.
	private Range testExpandRange; // This is the expanded range in use for the expand method.
	private Range testRange;
	private double DELTA;
	
	

    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
		exampleRange = new Range(-1, 1);
		testRange = new Range(-2, 2);
		DELTA = 1e-15;
    }

/***    Start of Ethan's tests    ***/

    @Test
    public void combineAllBoundsEqualLower() {
    	Range r1 = new Range(1.0, 1.0);
    	Range r2 = new Range(1.0, 1.0);
        assertEquals("The lower bound should be 1,0",
        1.0, Range.combine(r1, r2).getLowerBound(), .000000001d);
    }
    
    @Test
    public void combineAllBoundsEqualUpper() {
    	Range r1 = new Range(1.0, 1.0);
    	Range r2 = new Range(1.0, 1.0);
        assertEquals("The upper bound should be 1.0",
        1.0, Range.combine(r1, r2).getUpperBound(), .000000001d);
    }
    
    @Test
    public void combineIntersectingLower() {
    	Range r1 = new Range(1.0, 7.0);
    	Range r2 = new Range(3.0, 10.0);
        assertEquals("The lower bound should be 1.0",
        1.0, Range.combine(r1, r2).getLowerBound(), .000000001d);
    }
    
    @Test
    public void combineIntersectingUpper() {
    	Range r1 = new Range(1.0, 7.0);
    	Range r2 = new Range(3.0, 10.0);
        assertEquals("The upper bound should be 10.0",
        10.0, Range.combine(r1, r2).getUpperBound(), .000000001d);
    }
    
    @Test
    public void combineIntersectingNegativeLower() {
    	Range r1 = new Range(-7.0, -1.0);
    	Range r2 = new Range(-10.0, -3.0);
        assertEquals("The lower bound should be -10.0",
        -10.0, Range.combine(r1, r2).getLowerBound(), .000000001d);
    }
    
    @Test
    public void combineIntersectingNegativeUpper() {
    	Range r1 = new Range(-7.0, -1.0);
    	Range r2 = new Range(-10.0, -3.0);
        assertEquals("The upper bound should be -1.0",
        -1.0, Range.combine(r1, r2).getUpperBound(), .000000001d);
    }
    
    @Test
    public void combineNotIntersectingLower1() {
    	Range r1 = new Range(1.0, 5.0);
    	Range r2 = new Range(6.0, 10.0);
        assertEquals("The lower bound should be 1.0",
        1.0, Range.combine(r1, r2).getLowerBound(), .000000001d);
    }
    
    @Test
    public void combineNotIntersectingUpper1() {
    	Range r1 = new Range(1.0, 5.0);
    	Range r2 = new Range(6.0, 10.0);
        assertEquals("The upper bound should be 10.0",
        10.0, Range.combine(r1, r2).getUpperBound(), .000000001d);
    }
    
    @Test
    public void combineNotIntersectingLower2() {
    	Range r1 = new Range(1.0, 5.0);
    	Range r2 = new Range(6.0, 10.0);
        assertEquals("The lower bound should be 1.0",
        1.0, Range.combine(r2, r1).getLowerBound(), .000000001d);
    }
    
    @Test
    public void combineNotIntersectingUpper2() {
    	Range r1 = new Range(1.0, 5.0);
    	Range r2 = new Range(6.0, 10.0);
        assertEquals("The upper bound should be 10.0",
        10.0, Range.combine(r2, r1).getUpperBound(), .000000001d);
    }
    
    @Test
    public void combineNullWithRangeLower1() {
    	Range nullRange = null;
    	Range instRange = new Range(6.0, 10.0);
        assertEquals("The lower bound should be 6.0",
        6.0, Range.combine(nullRange, instRange).getLowerBound(), .000000001d);
    }
    
    @Test
    public void combineNullWithRangeUpper1() {
    	Range nullRange = null;
    	Range instRange = new Range(6.0, 10.0);
        assertEquals("The upper bound should be 10.0",
        10.0, Range.combine(nullRange, instRange).getUpperBound(), .000000001d);
    }
    
    @Test
    public void combineNullWithRangeLower2() {
    	Range nullRange = null;
    	Range instRange = new Range(6.0, 10.0);
        assertEquals("The lower bound should be 6.0",
        6.0, Range.combine(instRange, nullRange).getLowerBound(), .000000001d);
    }
    
    @Test
    public void combineNullWithRangeUpper2() {
    	Range nullRange = null;
    	Range instRange = new Range(6.0, 10.0);
        assertEquals("The upper bound should be 10.0",
        10.0, Range.combine(instRange, nullRange).getUpperBound(), .000000001d);
    }
    
    @Test
    public void combineNullWithNull() {
    	Range nullRange1 = null;
    	Range nullRange2 = null;
        assertNull("The return value should be null",
        Range.combine(nullRange1, nullRange2));
    }

/***    End of Ethan's tests    ***/

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
// Start of Kaniz's Test 
	/**
	 * This test will simulate creating expand a null range. This test should throw
	 * an exception.
	 */
	@Test
	public void expandNull() {
		try {
			Range nullRange = null; // creating a null range
			testExpandRange = Range.expand(nullRange, 0.25, 0.5); // trying to expand the null range
			fail("This method should throw an exception!");
			// creating a failure message for if expand does not throw an
			// exception
		} catch (Exception e) {
			assertEquals("The exception thrown type is IllegalArgumentException", IllegalArgumentException.class,
					e.getClass());
			// catching the exception, asserting that an IllegalArgumentException was thrown
		}
	}

	/**
	 * This test will simulate expanding the range by 0.25 and 0.5 - asserting that
	 * the lower margin will be expanded to be 1.
	 */
	@Test
	public void expandTestLowerMarginInt() { // This is created the see if the range will change or not.
		// Using the margin value in percentages which will return the lower bound as an
		// integer.
		testRange1 = new Range(2, 6); // This is creating the range for testing purposes.
		testExpandRange = Range.expand(testRange1, 0.25, 0.5); // Using the expand method to pass in the test
		// range and the margin values
		assertEquals("The lower margin range will be 1", 1, testExpandRange.getLowerBound(), .000000001d);
		// assertion that expected value matches the actual value (1)
	}

	/**
	 * This test will simulate expanding the range by 0.25 and 0.5 - asserting that
	 * the upper margin will be expanded to be 8.
	 */
	@Test
	public void expandTestUpperMarginInt() {// This is created the see if the range will change or not.
		// Using the margin value in percentages which will return the upper bound as an
		// integer.
		testRange1 = new Range(2, 6); // This is creating the range for testing purposes.
		testExpandRange = Range.expand(testRange1, 0.25, 0.5);// Using the expand method to pass in the test
		// range and the margin values
		assertEquals("The upper margin range will be 8", 8, testExpandRange.getUpperBound(), .000000001d);
		// assertion that expected value matches the actual value (8)
	}

	/**
	 * This test will simulate expanding the range by 1.25 and 1.5 - asserting that
	 * the upper margin will be expanded to be 12.
	 */
	@Test
	public void expandTestUpperMarginOverOne() {// This is created the see if the range will change or not.
		// Using the margin value in percentages which will return the upper bound as an
		// integer.
		testRange1 = new Range(2, 6); // This is creating the range for testing purposes.
		testExpandRange = Range.expand(testRange1, 1.25, 1.5);// Using the expand method to pass in the test
		// range and the margin values which are greater than 1.
		assertEquals("The upper margin range will be 12", 12, testExpandRange.getUpperBound(), .000000001d);
		// assertion that expected value matches the actual value (12)
	}

	
	/**
	 * This test will simulate expanding the range by 0.33 and 0.44 - asserting that
	 * the lower margin will be expanded to be 0.68.
	 */
	@Test
	public void expandTestLowerMarginDecimal() {// This is created the see if the range will change or not.
		// Using the margin value in percentages which will return the lower bound as a
		// decimal.
		testRange1 = new Range(2, 6); // This is creating the range for testing purposes.
		testExpandRange = Range.expand(testRange1, 0.33, 0.44);// Using the expand method to pass in the test
		// range and the margin values
		assertEquals("The lower margin range will be 0.68", 0.68, testExpandRange.getLowerBound(), .000000001d);
		// assertion that expected value matches the actual value (0.68)
	}

	/**
	 * This test will simulate expanding the range by 0.33 and 0.44 - asserting that
	 * the upper margin will be expanded to be 7.76.
	 */
	@Test
	public void expandTestUpperMarginDecimal() {// This is created the see if the range will change or not.
		// Using the margin value in percentages which will return the upper bound as a
		// decimal.
		testRange1 = new Range(2, 6); // This is creating the range for testing purposes.
		testExpandRange = Range.expand(testRange1, 0.33, 0.44);// Using the expand method to pass in the test
		// range and the margin values
		assertEquals("The upper margin range will be 7.76", 7.76, testExpandRange.getUpperBound(), .000000001d);
		// assertion that expected value matches the actual value (7.76)
	}

	/**
	 * This test will simulate that if we pass in an expansion of zero in the
	 * parameters there should be an exception thrown.
	 */
	@Test
	public void expandTestWithZero() {// This is created the see if the range will change or not.
		// Using the margin value in percentages which are all 0.
		try {
			testRange1 = new Range(2, 6); // This is creating the range for testing purposes.
			testExpandRange = Range.expand(testRange1, 0, 0);// Using the expand method to pass in the test
			assertEquals("The new range after the expansion of zero was different than orginal.", testRange1,
					testExpandRange);
		} catch (Exception e) {
			fail("Expanding it by zero threw an exception");
		}
		// The assert checks for the exception which is being thrown.
	}

	// END of Kaniz's Test

	@Test
    public void validRangeLowerLessThanUpper() {
    	double lower = -5;
    	double upper = 0;
        assertTrue("The range [-5, 0] will intersect the test range [-2, 2]",
         testRange.intersects(lower, upper));
    }
    
    @Test
    public void validRangeLowerEqualToUpper() {
    	double lower = 0;
    	double upper = 0;
        assertTrue("The range [0, 0] will intersect the test range [-2, 2]",
         testRange.intersects(lower, upper));
    }
    
    @Test
    public void invalidRangeLowerGreaterThanUpper() {
    	double lower = 5;
    	double upper = -5;
        assertFalse("The invalid range [5, -5] will not intersect the test range [-2, 2]",
         testRange.intersects(lower, upper));
    }
    
    @Test
    public void validSmallestRangeLessThanLower() {
    	double lower = -1.999999999999998;
    	double upper = -1.999999999999999;
        assertFalse("Checking the smallest range less than the lower value [-2, 2]",
         testRange.intersects(lower, upper));
    }
    
    @Test
    public void validSmallestRangeIntersectingLower() {
    	double lower = -1.999999999999999;
    	double upper = -2.000000000000001;
        assertTrue("Checking the smallest range intersecting the range [-2, 2]",
         testRange.intersects(lower, upper));
    }
    
    @Test
    public void validSmallestRangeGreaterThanLower() {
    	double lower = -2.000000000000001;
    	double upper = -2.000000000000002;
        assertTrue("Checking the smallest range greater than the lower value [-2, 2]",
         testRange.intersects(lower, upper));
    }
    
    @Test
    public void validSmallestRangeLessThanUpper() {
    	double lower = 1.999999999999998;
    	double upper = 1.999999999999999;
        assertTrue("Checking the smallest range less than the upper value [-2, 2]",
         testRange.intersects(lower, upper));
    }
    
    @Test
    public void validSmallestRangeIntersectingUpper() {
    	double lower = 1.999999999999999;
    	double upper = 2.000000000000001;
        assertTrue("Checking the smallest range intersecting the upper value [-2, 2]",
         testRange.intersects(lower, upper));
    }
    
    @Test
    public void validSmallestRangeGreaterThanUpper() {
    	double lower = 2.000000000000001;
    	double upper = 2.000000000000002;
        assertFalse("Checking the smallest range greater than the upper [-2, 2]",
         testRange.intersects(lower, upper));
    }
    
    @Test
    public void validLargestRangeLessThanLower() {
    	double lower = Double.MIN_VALUE;
    	double upper = -1.999999999999999;
        assertFalse("Checking the largest range less than the lower value [-2, 2]",
         testRange.intersects(lower, upper));
    }
    
    @Test
    public void validLargestRangeIntersectingLower() {
    	double lower = Double.MIN_VALUE;
    	double upper = 1.999999999999999;
        assertTrue("Checking the largest range intersecting the range [-2, 2]",
         testRange.intersects(lower, upper));
    }
    
    @Test
    public void validLargestRangeGreaterThanLower() {
    	double lower = -2.000000000000001;
    	double upper = 1.999999999999999;
        assertTrue(testRange.intersects(lower, upper));}
    
    @Test
    public void validLargestRangeIntersectingUpper() {
    	double lower = 1.999999999999999;
    	double upper = Double.MAX_VALUE;
        assertTrue(testRange.intersects(lower, upper));}
    
    @Test
    public void validLargestRangeGreaterThanUpper() {
    	double lower = -2.000000000000001;
    	double upper = Double.MAX_VALUE;
        assertTrue(testRange.intersects(lower, upper));}

	@Test
	public void ContainsValueInRange() {
		assertTrue("Range of -1 to 1 contains 0 ",
		exampleRange.contains(0));
	}
	
	@Test
	public void ContainsValueDecimal() {
		assertTrue("Range of -1 to 1 contains 0.0001",
		exampleRange.contains(0.0001));
	}

	@Test
	public void ContainsLowerBound() {
		assertTrue("Range of -1 to 1 contains -1",
		exampleRange.contains(-1));
	}

	@Test
	public void ContainsPastLowerBound() {
		assertFalse("Range of -1 to 1 does not contain -2.5",
		exampleRange.contains(-2.5));
	}
	
	@Test
	public void ContainsUpperBound() {
		assertTrue("Range of -1 to 1 contains 1",
		exampleRange.contains(1));
	}
	
	@Test
	public void ContainsPastUpperBound() {
		assertFalse("Range of -1 to 1 does not contain 2.5",
		exampleRange.contains(2.5));
	}
	
	@Test
	public void ContainsLargeNumber() {
		assertFalse("Range of -1 to 1 does not contain 1000000000",
		exampleRange.contains(1000000000));
	}

	// Start of Rhishik's Tests

	// Test for a typical valid range and a range where lower bound equals upper bound
    @Test
    public void testGetLowerBound_ValidRange() {
        // Test a typical valid range
        Range range1 = new Range(1.0, 5.0);
        assertEquals(1.0, range1.getLowerBound(), 0.0001); // Lower bound should be 1.0

        // Test a range where lower bound equals upper bound
        Range range2 = new Range(-2.0, -2.0);
        assertEquals(-2.0, range2.getLowerBound(), 0.0001); // Lower bound should be -2.0
    }

    // Test for an invalid range where lower bound > upper bound
    @Test(expected = IllegalArgumentException.class)
    public void testGetLowerBound_InvalidRange() {
        Range range = new Range(5.0, 1.0);
        range.getLowerBound(); // This should throw IllegalArgumentException
    }

    // Test for the boundary cases of the lower bound
    @Test
    public void testGetLowerBound_LowerBoundBoundary() {
        // Test a range where lower bound is just below upper bound
        Range range1 = new Range(1.0, 1.000000000000001);
        assertEquals(1.0, range1.getLowerBound(), 0.0001); // Lower bound should be 1.0

        // Test a range where lower bound equals upper bound
        Range range2 = new Range(1.0, 1.0);
        assertEquals(1.0, range2.getLowerBound(), 0.0001); // Lower bound should be 1.0

        // Test a range where lower bound is just above upper bound
//        Range range3 = new Range(1.0, 0.9999999999999999);
//        assertEquals(1.0, range3.getLowerBound(), 0.0001); // Lower bound should be 1.0
    }

    // Test for the boundary cases of the upper bound
    @Test
    public void testGetLowerBound_UpperBoundBoundary() {
        // Test a range where lower bound is minimal and upper bound is just below max double value
        Range range1 = new Range(Double.MIN_VALUE, Double.MAX_VALUE - 0.000000000000001);
        assertEquals(Double.MIN_VALUE, range1.getLowerBound(), 0.0001); // Lower bound should be Double.MIN_VALUE

        // Test a range where lower bound is minimal and upper bound equals max double value
        Range range2 = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        assertEquals(Double.MIN_VALUE, range2.getLowerBound(), 0.0001); // Lower bound should be Double.MIN_VALUE

        // Test a range where lower bound is minimal and upper bound is just above max double value
        Range range3 = new Range(Double.MIN_VALUE, Double.MAX_VALUE + 0.000000000000001);
        assertEquals(Double.MIN_VALUE, range3.getLowerBound(), 0.0001); // Lower bound should be Double.MIN_VALUE
    }
    // End of Rhishik's tests
    
    // Michael's Assignment 3 tests
    @Test
    public void testFirstRangeNullSecondNormal() {
        Range range2 = new Range(1.0, 2.0);
        Range result = Range.combineIgnoringNaN(null, range2);
        assertNotNull(result);
        assertEquals(1.0, result.getLowerBound(), DELTA);
        assertEquals(2.0, result.getUpperBound(), DELTA);
    }

    @Test
    public void testFirstRangeNullSecondNaN() {
        Range range2 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(null, range2));
    }

    @Test
    public void testSecondRangeNullFirstNormal() {
        Range range1 = new Range(1.0, 2.0);
        Range result = Range.combineIgnoringNaN(range1, null);
        assertNotNull(result);
        assertEquals(1.0, result.getLowerBound(), DELTA);
        assertEquals(2.0, result.getUpperBound(), DELTA);
    }

    @Test
    public void testSecondRangeNullFirstNaN() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(range1, null));
    }

    @Test
    public void testBothRangesNormal() {
        Range range1 = new Range(1.0, 3.0);
        Range range2 = new Range(2.0, 4.0);
        Range result = Range.combineIgnoringNaN(range1, range2);
        assertNotNull(result);
        assertEquals(1.0, result.getLowerBound(), DELTA);
        assertEquals(4.0, result.getUpperBound(), DELTA);
    }

    @Test
    public void testFirstRangeNaNSecondNormal() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(1.0, 2.0);
        Range result = Range.combineIgnoringNaN(range1, range2);
        assertNotNull(result);
        assertEquals(1.0, result.getLowerBound(), DELTA);
        assertEquals(2.0, result.getUpperBound(), DELTA);
    }

    @Test
    public void testFirstRangeNormalSecondNaN() {
        Range range1 = new Range(1.0, 2.0);
        Range range2 = new Range(Double.NaN, Double.NaN);
        Range result = Range.combineIgnoringNaN(range1, range2);
        assertNotNull(result);
        assertEquals(1.0, result.getLowerBound(), DELTA);
        assertEquals(2.0, result.getUpperBound(), DELTA);
    }

    @Test
    public void testBothRangesNaN() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(range1, range2));
    }
    
    @Test
    public void whenRangeIsNull() {
        Range result = Range.expandToInclude(null, 5.0);
        assertNotNull(result);
        assertEquals(5.0, result.getLowerBound(), DELTA);
        assertEquals(5.0, result.getUpperBound(), DELTA);
    }

    @Test
    public void whenValueIsLessThanLowerBound() {
        Range initialRange = new Range(10.0, 20.0);
        Range result = Range.expandToInclude(initialRange, 5.0);
        assertNotNull(result);
        assertEquals(5.0, result.getLowerBound(), DELTA);
        assertEquals(20.0, result.getUpperBound(), DELTA);
    }

    @Test
    public void whenValueIsGreaterThanUpperBound() {
        Range initialRange = new Range(10.0, 20.0);
        Range result = Range.expandToInclude(initialRange, 25.0);
        assertNotNull(result);
        assertEquals(10.0, result.getLowerBound(), DELTA);
        assertEquals(25.0, result.getUpperBound(), DELTA);
    }

    @Test
    public void whenValueIsWithinRangeBounds() {
        Range initialRange = new Range(10.0, 20.0);
        Range result = Range.expandToInclude(initialRange, 15.0);
        assertSame(initialRange, result);
        assertEquals(10.0, result.getLowerBound(), DELTA);
        assertEquals(20.0, result.getUpperBound(), DELTA);
    }
    
    @Test
    public void testShiftPositiveDelta() {
        Range original = new Range(1, 5); // Assuming Range has a constructor Range(double lower, double upper)
        double delta = 3;
        Range result = Range.shift(original, delta);

        assertEquals(4, result.getLowerBound(), DELTA);
        assertEquals(8, result.getUpperBound(), DELTA);
    }


	@Test
    public void testShiftNegativeDelta() {
        Range original = new Range(1, 5);
        double delta = -0.5;
        Range result = Range.shift(original, delta);

        assertEquals(0.5, result.getLowerBound(), DELTA);
        assertEquals(4.5, result.getUpperBound(), DELTA);
    }

    @Test
    public void testShiftWithZeroCrossingPrevented() {
        Range original = new Range(-3, 3);
        double delta = 5;
        Range result = Range.shift(original, delta, false); // Assuming zero-crossing is handled internally

        // The exact assertion might vary based on how your method handles zero-crossing prevention
        assertNotEquals(0, result.getLowerBound());
        assertTrue(result.getLowerBound() > 0); // This assumes the shift doesn't allow crossing zero into negative territory
    }
    
    @Test
    public void testExpandWithLowerGreaterThanUpper() {
        Range original = new Range(10, 20);
        double lowerMargin = 1.5; // 150% of the length to the left
        double upperMargin = 0.1; // 10% of the length to the right

        Range expanded = Range.expand(original, lowerMargin, upperMargin);

        double length = original.getLength(); // 20 - 10 = 10
        double expectedLower = original.getLowerBound() - length * lowerMargin; // 10 - 15 = -5
        double expectedUpper = original.getUpperBound() + length * upperMargin; // 20 + 1 = 21
        double midpoint = (expectedLower / 2.0) + (expectedUpper / 2.0);

        final double DELTA = 0.0001; // Allowable delta for floating-point comparisons
        assertEquals(midpoint, expanded.getLowerBound(), DELTA);
        assertEquals(midpoint, expanded.getUpperBound(), DELTA);
    }

    @Test
    public void testExpandWithLowerNotGreaterThanUpper() {
        Range original = new Range(10, 20);
        double lowerMargin = 0.1; // 10% of the length to the left
        double upperMargin = 0.2; // 20% of the length to the right

        Range expanded = Range.expand(original, lowerMargin, upperMargin);

        double length = original.getLength(); // 20 - 10 = 10
        double expectedLower = original.getLowerBound() - length * lowerMargin; // 10 - 1 = 9
        double expectedUpper = original.getUpperBound() + length * upperMargin; // 20 + 2 = 22

        final double DELTA = 0.0001; // Allowable delta for floating-point comparisons
        assertEquals(expectedLower, expanded.getLowerBound(), DELTA);
        assertEquals(expectedUpper, expanded.getUpperBound(), DELTA);
    }
    
    @Test
    public void getLowerBound_NormalCondition_ReturnsLower() {
        // Setup - assuming Range has a constructor like Range(double lower, double upper)
        double expectedLower = 1.0;
        double upper = 5.0;
        Range range = new Range(expectedLower, upper);

        // Invoke
        double actualLower = range.getLowerBound();

        // Assert
        assertEquals(expectedLower, actualLower, DELTA);
    }
    
    @Test
    public void getLowerBound_LowerGreaterThanUpper_ThrowsIllegalArgumentException() {
        double lower = 6.0;
        double upper = 5.0;

        try {
            // This operation should trigger the exception
            new Range(lower, upper).getLowerBound();
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Optionally assert the message of the exception for specific validation
            String expectedMessage = "require lower (" + lower + ") <= upper (" + upper + ").";
            assertTrue("Exception message does not match expected message.", e.getMessage().contains(expectedMessage));
        }
    }
    
    @Test
    public void getCentralValue_CorrectCalculation() {
        // Setup - create a range with known lower and upper bounds
        double lower = 2.0;
        double upper = 10.0;
        Range range = new Range(lower, upper);

        // Expected central value is the average of lower and upper bounds
        double expectedCentralValue = (lower + upper) / 2.0;

        // Invoke
        double actualCentralValue = range.getCentralValue();

        // Assert - verify the central value is calculated correctly
        assertEquals("The central value should be correctly calculated as the average of lower and upper bounds.",
                     expectedCentralValue, actualCentralValue, DELTA);
    }
    
    @Test
    public void intersects_RangesOverlap_ReturnsTrue() {
        Range baseRange = new Range(5, 10);
        Range intersectingRange = new Range(7, 12);

        assertTrue("Ranges should intersect", baseRange.intersects(intersectingRange));
    }

    @Test
    public void intersects_RangeCompletelyWithinAnother_ReturnsTrue() {
        Range baseRange = new Range(5, 10);
        Range withinRange = new Range(6, 9);

        assertTrue("Ranges should intersect as one range is completely within another", baseRange.intersects(withinRange));
    }

    @Test
    public void intersects_RangesTouchAtBoundary_ReturnsTrue() {
        Range baseRange = new Range(5, 10);
        Range touchingRange = new Range(10, 15);

        assertTrue("Ranges touching at a boundary should be considered as intersecting", baseRange.intersects(touchingRange));
    }

    @Test
    public void intersects_RangesDoNotOverlap_ReturnsFalse() {
        Range baseRange = new Range(5, 10);
        Range nonIntersectingRange = new Range(11, 15);

        assertFalse("Ranges should not intersect", baseRange.intersects(nonIntersectingRange));
    }

    @Test
    public void intersects_RangeIsBefore_ReturnsFalse() {
        Range baseRange = new Range(10, 15);
        Range beforeRange = new Range(5, 9);

        assertFalse("Range completely before should not intersect", baseRange.intersects(beforeRange));
    }
    
    @Test
    public void constrain_ValueWithinRange_ReturnsValue() {
        Range range = new Range(5, 10);
        double value = 7;
        
        double constrainedValue = range.constrain(value);
        
        assertEquals("Value within range should be returned unchanged.", value, constrainedValue, DELTA);
    }

    @Test
    public void constrain_ValueAboveRange_ReturnsUpperBound() {
        Range range = new Range(5, 10);
        double value = 12;
        
        double constrainedValue = range.constrain(value);
        
        assertEquals("Value above range should return upper bound.", range.getUpperBound(), constrainedValue, DELTA);
    }

    @Test
    public void constrain_ValueBelowRange_ReturnsLowerBound() {
        Range range = new Range(5, 10);
        double value = 3;
        
        double constrainedValue = range.constrain(value);
        
        assertEquals("Value below range should return lower bound.", range.getLowerBound(), constrainedValue, DELTA);
    }
    
    @Test
    public void shift_AllowZeroCrossing_PositiveDelta() {
        Range base = new Range(-5, 5);
        Range result = Range.shift(base, 10, true);
        assertEquals("Shift with zero crossing allowed should move both bounds up by delta.",
                new Range(5, 15), result);
    }

    @Test
    public void shift_NoZeroCrossing_PositiveDelta_NoCross() {
        Range base = new Range(5, 15);
        Range result = Range.shift(base, 5, false);
        assertEquals("Shift without zero crossing, no actual zero cross, should move bounds up by delta.",
                new Range(10, 20), result);
    }

    @Test
    public void shift_NoZeroCrossing_PositiveDelta_WouldCross() {
        Range base = new Range(-10, -5);
        // Assuming shiftWithNoZeroCrossing method adjusts negative result to 0
        Range result = Range.shift(base, 5, false);
        // Expected behavior depends on implementation of shiftWithNoZeroCrossing
        // Assuming it adjusts the shift to avoid crossing zero
        assertEquals("Shift without zero crossing that would cross zero adjusts to 0.",
                new Range(0, 0), result); // Adjust based on shiftWithNoZeroCrossing behavior
    }

    @Test(expected = IllegalArgumentException.class)
    public void shift_NullBase_ThrowsException() {
        Range.shift(null, 10, true);
    }
    
    @Test
    public void scale_PositiveFactor() {
        Range base = new Range(1, 5);
        double factor = 2;
        Range result = Range.scale(base, factor);
        assertEquals("Scaling lower bound failed", 2, result.getLowerBound(), DELTA);
        assertEquals("Scaling upper bound failed", 10, result.getUpperBound(), DELTA);
    }

    @Test
    public void scale_FactorZero() {
        Range base = new Range(3, 6);
        double factor = 0;
        Range result = Range.scale(base, factor);
        assertEquals("Scaling lower bound to zero failed", 0, result.getLowerBound(), DELTA);
        assertEquals("Scaling upper bound to zero failed", 0, result.getUpperBound(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void scale_NegativeFactor() {
        Range base = new Range(2, 4);
        double factor = -1;
        Range.scale(base, factor);
    }

    @Test
    public void scale_IncludesNegativeToPositive() {
        Range base = new Range(-3, 3);
        double factor = 2;
        Range result = Range.scale(base, factor);
        assertEquals("Scaling lower bound failed", -6, result.getLowerBound(), DELTA);
        assertEquals("Scaling upper bound failed", 6, result.getUpperBound(), DELTA);
    }

    // Optional: This test case is based on the assumption that your system should gracefully handle large values.
    // It might require setting a specific expected behavior if your application should handle such cases.
    @Test
    public void scale_LargeFactor() {
        Range base = new Range(1, 3);
        double factor = 1E308;
        Range result = Range.scale(base, factor);
        // Assertions here would depend on the expected behavior, potentially checking for overflow or large values
    }
    
    @Test
    public void equals_Self() {
        Range range = new Range(1, 5);
        assertTrue("A range should be equal to itself", range.equals(range));
    }

    @Test
    public void equals_Null() {
        Range range = new Range(1, 5);
        assertFalse("A range should not be equal to null", range.equals(null));
    }

    @Test
    public void equals_DifferentClass() {
        Range range = new Range(1, 5);
        Object obj = new Object();
        assertFalse("A range should not be equal to an object of a different class", range.equals(obj));
    }

    @Test
    public void equals_DifferentLowerBound() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(2, 5);
        assertFalse("Ranges with different lower bounds should not be equal", range1.equals(range2));
    }

    @Test
    public void equals_DifferentUpperBound() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(1, 6);
        assertFalse("Ranges with different upper bounds should not be equal", range1.equals(range2));
    }

    @Test
    public void equals_SameBounds() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(1, 5);
        assertTrue("Ranges with the same bounds should be equal", range1.equals(range2));
    }
    
    @Test
    public void isNaNRange_BothNaN() {
        Range range = new Range(Double.NaN, Double.NaN);
        assertTrue("Range with both bounds as NaN should return true", range.isNaNRange());
    }

    @Test
    public void isNaNRange_LowerBoundNaN() {
        Range range = new Range(Double.NaN, 1.0);
        assertFalse("Range with only lower bound as NaN should return false", range.isNaNRange());
    }

    @Test
    public void isNaNRange_UpperBoundNaN() {
        Range range = new Range(1.0, Double.NaN);
        assertFalse("Range with only upper bound as NaN should return false", range.isNaNRange());
    }

    @Test
    public void isNaNRange_NeitherNaN() {
        Range range = new Range(1.0, 5.0);
        assertFalse("Range with no bounds as NaN should return false", range.isNaNRange());
    }
    
    @Test
    public void hashCode_Consistency() {
        Range range = new Range(1.0, 5.0);
        int firstHashCode = range.hashCode();
        int secondHashCode = range.hashCode();
        assertEquals("The same range instance should produce the same hash code across invocations", firstHashCode, secondHashCode);
    }

    @Test
    public void hashCode_Equality() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(1.0, 5.0);
        assertEquals("Equal Range objects should produce the same hash code", range1.hashCode(), range2.hashCode());
    }

    @Test
    public void hashCode_DifferentValues() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(2.0, 6.0); // Different range
        assertNotEquals("Ranges with different values should generally produce different hash codes", range1.hashCode(), range2.hashCode());
    }
    
    @Test
    public void shift_NegativeDelta_NoZeroCrossing() {
        // Setup
        Range base = new Range(0.0, 10.0); // A positive range
        double delta = -3.0; // A negative shift
        boolean allowZeroCrossing = false;

        // Execute
        Range result = Range.shift(base, delta, allowZeroCrossing);

        // Verify that the shift does not allow crossing zero
        // Expecting the shifted range to be clipped at zero if it were to cross it
        double expectedLowerBound = 2.0; // 5.0 - 3.0 = 2.0
        double expectedUpperBound = 7.0; // 10.0 - 3.0 = 7.0

        assertEquals("The lower bound of the shifted range is incorrect", expectedLowerBound, result.getLowerBound(), DELTA);
        assertEquals("The upper bound of the shifted range is incorrect", expectedUpperBound, result.getUpperBound(), DELTA);
    }
    
    @Test
    public void expand_LowerGreaterThanUpper_Adjusted() {
        // Setup a range
        Range range = new Range(10, 20);
        // Choose margins that will result in the lower bound calculation
        // being greater than the upper bound calculation
        double lowerMargin = -2.0; // This will extend the lower bound much lower
        double upperMargin = 0.5;  // This extends the upper boundary less aggressively

        // Execute
        Range result = Range.expand(range, lowerMargin, upperMargin);

        // The expected behavior is for the lower and upper bounds to be averaged if lower > upper
        double expectedLower = ((10 - 2*10) + (20 + 0.5*10)) / 2.0;
        double expectedUpper = expectedLower; // They are set to be the same in this case

        // Verify
        assertEquals("The adjusted lower bound is not as expected", expectedLower, result.getLowerBound(), DELTA);
        assertEquals("The adjusted upper bound is not as expected", expectedUpper, result.getUpperBound(), DELTA);
    }  
}
