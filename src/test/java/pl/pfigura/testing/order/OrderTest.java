package pl.pfigura.testing.order;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pl.pfigura.testing.Meal;
import pl.pfigura.testing.extensions.BeforeAfterExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@ExtendWith(BeforeAfterExtension.class)
class OrderTest {

    private Order order;

    @BeforeEach
    void initializeOrder() {

        System.out.println("Before each");
        order = new Order();
    }

    @AfterEach
    void cleanUp() {

        System.out.println("After each");
        order.cancel();
    }

    @Test
    void testAssertArrayEquals() {
        //given
        int[] ints1 = {1, 2, 3};
        int[] ints2 = {1, 2, 3};

        //then

        assertArrayEquals(ints1, ints2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder() {
        //given

        //then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        MatcherAssert.assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize() {
        //given
        Meal meal = new Meal(10, "pizza");
        Meal meal2 = new Meal(5, "sandwich");
        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);
        //then
        assertThat(order.getMeals(), hasSize(2));
        assertThat(order.getMeals(), containsInAnyOrder(meal2, meal));
        assertThat(order.getMeals(), hasItem(meal));
        assertThat(order.getMeals().get(0).getPrice(), equalTo(10));
    }

    @Test
    void mealShouldBeInCorrectOrderAfterAddingThemToOrder() {
        //given
        Meal meal = new Meal(10, "pizza");
        Meal meal2 = new Meal(5, "sandwich");
        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);

        //than
        assertThat(order.getMeals(), containsInAnyOrder(meal, meal2));
    }
}