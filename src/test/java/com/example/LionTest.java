package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Test
    public void testMaleLionHasMane() throws Exception {
        Predator predatorMock = Mockito.mock(Predator.class);
        Lion lion = new Lion("Самец", predatorMock);
        assertTrue(lion.doesHaveMane());
    }

    @Test
    public void testFemaleLionHasNoMane() throws Exception {
        Predator predatorMock = Mockito.mock(Predator.class);
        Lion lion = new Lion("Самка", predatorMock);
        assertFalse(lion.doesHaveMane());
    }

    @Test(expected = Exception.class)
    public void testInvalidSexThrowsException() throws Exception {
        Predator predatorMock = Mockito.mock(Predator.class);
        new Lion("Неизвестный", predatorMock);
    }

    @Test
    public void testGetKittens() throws Exception {
        Predator predatorMock = Mockito.mock(Predator.class);
        when(predatorMock.getKittens()).thenReturn(3);

        Lion lion = new Lion("Самец", predatorMock);
        assertEquals(3, lion.getKittens());

        Mockito.verify(predatorMock).getKittens();
    }

    @Test
    public void testGetFood() throws Exception {
        Predator predatorMock = Mockito.mock(Predator.class);
        List<String> expectedFood = Arrays.asList("Антилопы", "Зебры", "Буйволы");
        when(predatorMock.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самка", predatorMock);
        List<String> actualFood = lion.getFood();

        assertEquals(expectedFood, actualFood);
        Mockito.verify(predatorMock).eatMeat();
    }

    @RunWith(Parameterized.class)
    public static class LionSexParameterizedTest {

        private final String sex;
        private final boolean expectedHasMane;
        private final Predator predatorMock;

        public LionSexParameterizedTest(String sex, boolean expectedHasMane) {
            this.sex = sex;
            this.expectedHasMane = expectedHasMane;
            this.predatorMock = Mockito.mock(Predator.class);
        }

        @Parameters(name = "Пол: {0}, Грива: {1}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"Самец", true},
                    {"Самка", false}
            });
        }

        @Test
        public void testLionManeBasedOnSex() throws Exception {
            Lion lion = new Lion(sex, predatorMock);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }
    }

    @RunWith(Parameterized.class)
    public static class LionKittensParameterizedTest {

        private final int expectedKittens;
        private final Predator predatorMock;

        public LionKittensParameterizedTest(int expectedKittens) {
            this.expectedKittens = expectedKittens;
            this.predatorMock = Mockito.mock(Predator.class);
        }

        @Parameters(name = "Котят: {0}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {0}, {1}, {3}, {5}, {10}
            });
        }

        @Test
        public void testLionGetKittens() throws Exception {
            when(predatorMock.getKittens()).thenReturn(expectedKittens);
            Lion lion = new Lion("Самка", predatorMock);
            assertEquals(expectedKittens, lion.getKittens());
            verify(predatorMock).getKittens();
        }
    }
}
