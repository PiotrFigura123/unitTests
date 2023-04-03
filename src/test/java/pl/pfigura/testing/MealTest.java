package pl.pfigura.testing;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pfigura.testing.extensions.IAException;
import pl.pfigura.testing.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;


class MealTest {

    @Spy
    private Meal mealSpy;

    @Test
    void shouldReturnDiscountedPrice() {
        //given
        Meal meal = new Meal(30);
        //when
        int discountPrice = meal.getDiscountPrice(7);

        //then
        assertEquals(23, discountPrice);

    }

    @Test
    void referencesToTheSameObjectShouldBeEqual() {
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;
        //then
        assertSame(meal1, meal2);

    }

    @Test
    void referencesToTheDifferenceObjectShouldNotBeEqual() {
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);
        //then -> Junit
        assertNotSame(meal1, meal2);

        //then -> hamcrest
        assertThat(meal1, not(sameInstance(meal2)));
    }

    @Test
    void twoMealShouldBeEqualIfTheSameName() {
        //given
        Meal meal1 = new Meal(10, "pizza");
        Meal meal2 = new Meal(10, "pizza");

        //then
        assertEquals(meal1, meal2);
    }

    @Test
    void exceptionShouldBeThownIfDiscountHigherThanPrice() {
        Meal meal = new Meal(8, "pizza");

        //when + then
        assertThrows(IllegalArgumentException.class, () -> meal.getDiscountPrice(10));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 19})
    void mealPricesShouldBeLowerThan20(int price) {
        assertThat(price, lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealWithNameAndPrice")
    void burgerShouldHaveCorrectNameAndPrice(String name, int price) {
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }

    private static Stream<Arguments> createMealWithNameAndPrice() {
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheeseburger", 12)
        );
    }

    @ParameterizedTest
    @MethodSource("createCakeName")
    void cakeNamesShouldEndWithCake(String name) {
        assertThat(name, notNullValue());
        assertThat(name, endsWith("cake"));
    }

    private static Stream<String> createCakeName() {
        List<String> cakeNames = Arrays.asList("Cheescake", "fruitcake", "Cupcake");
        return cakeNames.stream();
    }

    @Tag("fries")
    @ExtendWith(IAException.class)
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 8})
    void mealPricesShouldBeLowerThan10(int price) {
        if (price > 5) {
            throw new IllegalArgumentException();
        }
        assertThat(price, lessThan(20));
    }

    //Testy Dynamiczne
    @Tag("fries") //-> moge potem w testach sprawdzic testy tylko dla danego taga
    @TestFactory
    Collection<DynamicTest> calculateMealPrice() {
        Order order = new Order();
        order.addMealToOrder(new Meal(10, "pizza", 2));
        order.addMealToOrder(new Meal(7, "fries", 4));
        order.addMealToOrder(new Meal(22, "hamb", 3));
        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        for (int i = 0; i < order.getMeals().size(); i++) {
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();

            Executable executable = () -> {
                assertThat(calculatePrice(price, quantity), lessThan(67));
            };
            String name = "Test name " + i;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name, executable);
            dynamicTests.add(dynamicTest);
        }
        return dynamicTests;
    }

    @Test
    void testMealSumPrice() {
        //given
        Meal meal = mock(Meal.class);
        given(meal.getPrice()).willReturn(15);
        given(meal.getQuantity()).willReturn(3);
        given(meal.sumPrice()).willCallRealMethod();

        //when
        int resule = meal.sumPrice();

        //then
        assertThat(resule, equalTo(45));
    }


    @Test
    @ExtendWith(MockitoExtension.class)
    void testMealSumPriceWithSpy() {
        //given
        given(mealSpy.getPrice()).willReturn(15);
        given(mealSpy.getQuantity()).willReturn(3);

        //when
        int resule = mealSpy.sumPrice();

        //then
        then(mealSpy).should().getPrice();
        then(mealSpy).should().getQuantity();
        assertThat(resule, equalTo(45));
    }

    private int calculatePrice(int price, int quantity) {
        return price * quantity;
    }
}