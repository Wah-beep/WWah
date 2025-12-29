import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.BeforeAll;

public class CircleOverlapTest{
	private static Circle defaultCircle;
	@BeforeAll
	static void setUpTests(){
		System.out.println("Setting up tests...");
		defaultCircle = new Circle(0,0,3);
	}
	
	
	@Test
	@DisplayName("Testing Normal Overlapping in Each Quadrants")
	void testNormalOverlap(){
		assertAll("Normal Overlapping Circles", 
			() -> assertTrue(defaultCircle.overlaps(new Circle(1,2,3)), "Circles should overlaps"),
			() -> assertTrue(defaultCircle.overlaps(new Circle(1,-2,3)), "Circles should overlaps"),
			() -> assertTrue(defaultCircle.overlaps(new Circle(-1,-2,3)), "Circles should overlaps"),
			() -> assertTrue(defaultCircle.overlaps(new Circle(-1,2,3)), "Circles should overlaps"),
			//Testing False
			() -> assertFalse(defaultCircle.overlaps(new Circle(6,12,3)), "Circles should not overlaps"),
			() -> assertFalse(defaultCircle.overlaps(new Circle(6,-12,3)), "Circles should not overlaps"),
			() -> assertFalse(defaultCircle.overlaps(new Circle(-6,-12,3)), "Circles should not overlaps"),
			() -> assertFalse(defaultCircle.overlaps(new Circle(-6,12,3)), "Circles should not overlaps")
		);
	}
	
	@Test
	@DisplayName("Testing Stacked Overlapping")
	void testStackedOverlap(){
		assertAll("Testing Stacked Circles", 
			() -> assertFalse(defaultCircle.overlaps(new Circle(3,9,3)), "Circles should not overlay"),
			() -> assertFalse(defaultCircle.overlaps(new Circle(3,-9,3)), "Circles should not overlay"),
			() -> assertFalse(defaultCircle.overlaps(new Circle(-3,-9,3)), "Circles should not overlay"),
			() -> assertTrue(defaultCircle.overlaps(new Circle(0,0,3)), "Same size circles should overlap stacked on each other"),
			() -> assertTrue(defaultCircle.overlaps(new Circle(0,0,6)), "Bigger circle should overlap by stacked on smaller circle")
		);
	}
	
    @ParameterizedTest(name = "Test with x/y-Parameter = {0}")
    @ValueSource(ints = {6, -6})
    @DisplayName("Testing Touching Overlapping on x and y axis")
    void testTouchOverlap(int value) {
        assertAll("Touch Overlapping Circles",
            () -> assertTrue(defaultCircle.overlaps(new Circle(value, 0, 3)), "Circles should overlap by touching on the x-axis"),
            () -> assertTrue(defaultCircle.overlaps(new Circle(0, value, 3)), "Circles should overlap by touching on the y-axis")
        );
    }
	
	@ParameterizedTest(name = "Testing Circle({0}, {1}, {2})")
	@CsvSource({"1,2,3,",
				"1,-2,3",
				"-1,-2,3",
				"-1,2,3"
				})
	@DisplayName("Testing overlapping with csv source")
	void testCsvOverlap(int param0, int param1, int param2){
		assertAll("Testing overlap with csv source",
			() -> assertTrue(defaultCircle.overlaps(new Circle(param0, param1, param2)), "Circles should overlaps"),
			() -> assertFalse(defaultCircle.overlaps(new Circle(param0, param1, param2)), "Circles should overlaps")
		);
	}
}