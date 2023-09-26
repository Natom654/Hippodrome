import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;


class HippodromeTest {

    @Test
    public void construct_NullListParamPassed_ThrowsIllegalArgumentException() {
        String expectMessage = "Horses cannot be null.";
        List<Horse> horses = null;

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        Assertions.assertEquals(expectMessage, exception.getMessage());
    }

    @Test
    public void construct_EmptyListParamPassed_ThrowsIllegalArgumentException() {
        String expectMessage = "Horses cannot be empty.";
        List<Horse> horses = new ArrayList<>();

        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        Assertions.assertEquals(expectMessage, exception.getMessage());
    }

    @Test
    void getHorses_ReturnListWithAllHorses() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse" + i, i, i));
        }
        Hippodrome hipp = new Hippodrome(horses);

        Assertions.assertNotNull(hipp.getHorses());
        Assertions.assertEquals(30, hipp.getHorses().size());
        Assertions.assertEquals("Horse0", hipp.getHorses().get(0).getName());
        Assertions.assertEquals("Horse10", hipp.getHorses().get(10).getName());
        Assertions.assertEquals("Horse29", hipp.getHorses().get(29).getName());
    }


    @Test
    void move_CallMoveAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hipp = new Hippodrome(horses);
        hipp.move();
        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }


    @Test
    void getWinner_ReturnCorrectWinner() {
        Hippodrome hipp = new Hippodrome(List.of(
                new Horse("horse10", 1, 10),
                new Horse("horse20", 1, 20),
                new Horse("horse30", 1, 30)
        ));
        Assertions.assertEquals("horse30", hipp.getWinner().getName());
    }
}