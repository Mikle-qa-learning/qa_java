package com.example;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class CatTest {

    @Test
    public void testGetSound() {
        Feline felineMock = Mockito.mock(Feline.class);
        Cat cat = new Cat(felineMock);

        assertEquals("Мяу", cat.getSound());
    }

    @Test
    public void testGetFood() throws Exception {
        // Создаем мок Feline
        Feline felineMock = Mockito.mock(Feline.class);
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");

        // Настраиваем поведение мока
        when(felineMock.eatMeat()).thenReturn(expectedFood);

        Cat cat = new Cat(felineMock);
        List<String> actualFood = cat.getFood();

        assertEquals(expectedFood, actualFood);
        // Проверяем, что метод был вызван
        Mockito.verify(felineMock).eatMeat();
    }

    @Test(expected = Exception.class)
    public void testGetFoodThrowsException() throws Exception {
        Feline felineMock = Mockito.mock(Feline.class);
        when(felineMock.eatMeat()).thenThrow(new Exception("Ошибка получения еды"));

        Cat cat = new Cat(felineMock);
        cat.getFood();
    }
}
