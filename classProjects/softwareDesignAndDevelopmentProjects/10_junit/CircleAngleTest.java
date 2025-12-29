import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Circle Angle Between Tests")
class CircleAngleTest {

    Circle baseCircle;

    @BeforeEach
    void setUp() {
        baseCircle = new Circle(0, 0, 5);
    }

    @Test
    @DisplayName("Basic Angle Tests with assertAll")
    void testBasicAngles() {
        assertAll("Angle calculations",
            () -> assertEquals(0, baseCircle.angleBetween(new Circle(5, 0, 5)), "Right (0°)"),
            () -> assertEquals(90, baseCircle.angleBetween(new Circle(0, 5, 5)), "Up (90°)"),
            () -> assertEquals(180, baseCircle.angleBetween(new Circle(-5, 0, 5)), "Left (180°)"),
            () -> assertEquals(270, baseCircle.angleBetween(new Circle(0, -5, 5)), "Down (270°)")
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 90, 180, 270})
    @DisplayName("Cardinal Directions Test")
    void testCardinalAngles(int expectedAngle) {
        Circle testCircle = switch (expectedAngle) {
            case 0 -> new Circle(5, 0, 5);
            case 90 -> new Circle(0, 5, 5);
            case 180 -> new Circle(-5, 0, 5);
            case 270 -> new Circle(0, -5, 5);
            default -> throw new IllegalArgumentException("Unexpected angle: " + expectedAngle);
        };
        assertEquals(expectedAngle, baseCircle.angleBetween(testCircle), "Expected " + expectedAngle + "°");
    }

    @ParameterizedTest
    @CsvSource({
        "3, 3, 45", 
        "-3, -3, 225"
    })
    @DisplayName("Diagonal Angles Test")
    void testDiagonalAngles(int x, int y, int expectedAngle) {
        assertEquals(expectedAngle, baseCircle.angleBetween(new Circle(x, y, 5)), 
            "Expected " + expectedAngle + "° for (" + x + "," + y + ")");
    }

    @AfterEach
    void tearDown() {
        baseCircle = null;
    }
}
