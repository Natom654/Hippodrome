import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import java.util.List;

import static org.mockito.Mockito.mockStatic;


public class HorseTest {

    @Test
    public void construct_NullNameParamTest_ThrowsIllegalArgumentException() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 2));
        Assertions.assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "\n", " \n\n", "\t", "\t\t", "\t \t"})
    public void construct_EmpNameParamTest_ThrowsIllegalArgumentException(String s) {
        String expectedMessage = "Name cannot be blank.";
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(s, 1, 2));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void construct_NegativeSpeedParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Speed cannot be negative.";
        String name = "TestName";
        int speed = -5;
        int distance = 2;

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }


    @Test
    public void construct_NegativeDistanceParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Distance cannot be negative.";
        String name = "TestName1";
        int speed = 10;
        int distance = -3;

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getName_ReturnsCorrectName() {
        String name = "TestName3";
        int speed = 9;
        int distance = 8;
        Horse horse = new Horse(name, speed, distance);

        String actualName = horse.getName();
        Assertions.assertEquals(name, actualName);
    }

    @Test
    void getSpeed_ReturnedCorrectSpeed() {
        String name = "TestName";
        int speed = 4;
        int distance = 6;
        Horse horse = new Horse(name, speed, distance);

        Double actualSpeed = horse.getSpeed();
        Assertions.assertEquals(speed, actualSpeed);
    }

    @Test
    void getDistance_ReturnedCorrectDistance() {
        String name = "TestName4";
        int speed = 2;
        int distance = 5;
        Horse horse = new Horse(name, speed, distance);
        Double actualDistance = horse.getDistance();
        Assertions.assertEquals(distance, actualDistance);
    }

    @Test
    void getDistance_Returned0ifNoDistance() {
        String name = "TestName4";
        int speed = 5;
        int distance = 0;
        Horse horse = new Horse(name, speed);
        Double actualDistance = horse.getDistance();
        Assertions.assertEquals(distance, actualDistance);

    }

    @Mock
    List mockList;

    @Test
    void move_getRandomDoubleMethod() {
        try (MockedStatic<Horse> horseMock = mockStatic(Horse.class)) {
            Horse horse = new Horse("Testname", 1, 2);
            horse.move();

            horseMock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.3, 0.5, 15, 0, 153})
    public void move_Calc(double fakeRandomValue) {
        double min = 0.2;
        double max = 0.9;
        String name = "testName";
        double speed = 2.5;
        double distance = 250;
        Horse horse = new Horse(name, speed, distance);

        double expectedDistance = distance + speed * fakeRandomValue;

        try (MockedStatic<Horse> horseMock = mockStatic(Horse.class)) {

            horseMock.when(() -> Horse.getRandomDouble(min, max)).thenReturn(fakeRandomValue);
            horse.move();

            Assertions.assertEquals(expectedDistance, horse.getDistance());
        }
    }

    @Test
    void getRandomDouble() {
    }
}
