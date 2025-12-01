package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class LionTest {

    @Test
    public void testMaleLionHasMane() throws Exception {
        Feline felineMock = Mockito.mock(Feline.class);
        Lion lion = new Lion("Самец", felineMock);
        assertTrue(lion.doesHaveMane());
    }

    @Test
    public void testFemaleLionHasNoMane() throws Exception {
        Feline felineMock = Mockito.mock(Feline.class);
        Lion lion = new Lion("Самка", felineMock);
        assertFalse(lion.doesHaveMane());
    }

    @Test(expected = Exception.class)
    public void testInvalidSexThrowsException() throws Exception {
        Feline felineMock = Mockito.mock(Feline.class);
        new Lion("Неизвестный", felineMock);
    }

    @Test
    public void testGetKittensReturnsCorrectValue() throws Exception {
        Feline felineMock = Mockito.mock(Feline.class);
        Mockito.when(felineMock.getKittens()).thenReturn(3);

        Lion lion = new Lion("Самец", felineMock);
        assertEquals(3, lion.getKittens());
    }

    @Test
    public void testGetKittensCallsFelineMethod() throws Exception {
        Feline felineMock = Mockito.mock(Feline.class);
        Mockito.when(felineMock.getKittens()).thenReturn(3);

        Lion lion = new Lion("Самец", felineMock);
        lion.getKittens();

        Mockito.verify(felineMock).getKittens();
    }

    @Test
    public void testGetFoodReturnsCorrectFood() throws Exception {
        Feline felineMock = Mockito.mock(Feline.class);
        List<String> expectedFood = Arrays.asList("Антилопы", "Зебры", "Буйволы");
        Mockito.when(felineMock.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самка", felineMock);
        List<String> actualFood = lion.getFood();

        assertEquals(expectedFood, actualFood);
    }

    @Test
    public void testGetFoodCallsEatMeat() throws Exception {
        Feline felineMock = Mockito.mock(Feline.class);
        List<String> expectedFood = Arrays.asList("Антилопы", "Зебры", "Буйволы");
        Mockito.when(felineMock.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самка", felineMock);
        lion.getFood();

        Mockito.verify(felineMock).eatMeat();
    }

    @RunWith(Parameterized.class)
    public static class LionSexParameterizedTest {

        private final String sex;
        private final boolean expectedHasMane;

        public LionSexParameterizedTest(String sex, boolean expectedHasMane) {
            this.sex = sex;
            this.expectedHasMane = expectedHasMane;
        }

        @Parameterized.Parameters(name = "Пол: {0}, Грива: {1}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"Самец", true},
                    {"Самка", false}
            });
        }

        @Test
        public void testLionManeBasedOnSex() throws Exception {
            Feline felineMock = Mockito.mock(Feline.class);
            Lion lion = new Lion(sex, felineMock);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }
    }

    @RunWith(Parameterized.class)
    public static class LionKittensParameterizedTest {

        private final int expectedKittens;

        public LionKittensParameterizedTest(int expectedKittens) {
            this.expectedKittens = expectedKittens;
        }

        @Parameterized.Parameters(name = "Котят: {0}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {0}, {1}, {3}, {5}, {10}
            });
        }

        @Test
        public void testLionGetKittensReturnsCorrectValue() throws Exception {
            Feline felineMock = Mockito.mock(Feline.class);
            Mockito.when(felineMock.getKittens()).thenReturn(expectedKittens);

            Lion lion = new Lion("Самка", felineMock);
            assertEquals(expectedKittens, lion.getKittens());
        }

        @Test
        public void testLionGetKittensCallsFelineMethod() throws Exception {
            Feline felineMock = Mockito.mock(Feline.class);
            Mockito.when(felineMock.getKittens()).thenReturn(expectedKittens);

            Lion lion = new Lion("Самка", felineMock);
            lion.getKittens();

            Mockito.verify(felineMock).getKittens();
        }
    }
}
