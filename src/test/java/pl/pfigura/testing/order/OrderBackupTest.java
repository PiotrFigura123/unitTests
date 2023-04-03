package pl.pfigura.testing.order;

import org.junit.jupiter.api.*;
import pl.pfigura.testing.Meal;

import java.io.FileNotFoundException;
import java.io.IOException;

class OrderBackupTest {

    private static OrderBackup orderBackup;

    @BeforeAll
    static void setup() throws FileNotFoundException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @BeforeEach
    void appendOnBegining() throws IOException {
        orderBackup.getWriter().append("Now order : ");

    }

    @AfterEach
    void appendOnEnd() throws IOException {
        orderBackup.getWriter().append(" backed up");

    }

    @Test
    void backupOrderWithOneMeal() throws IOException {

        //given
        Meal meal = new Meal(10, "pizza");
        pl.pfigura.testing.order.Order order = new pl.pfigura.testing.order.Order();
        order.addMealToOrder(meal);

        //when
        orderBackup.backupOrder(order);

        //than
        System.out.println(order.toString() + " backup");
    }


    @AfterAll
    static void tearDown() throws IOException {
        orderBackup.closeFile();
    }

}