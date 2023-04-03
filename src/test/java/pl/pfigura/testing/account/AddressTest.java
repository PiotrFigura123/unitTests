package pl.pfigura.testing.account;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AddressTest {

    @ParameterizedTest
    @CsvSource({"fab, 10", "armii, 3"})
    void givenAdressesShouldNotBeEmptyAndHasProperNames(String name, String number) {
        assertThat(name, notNullValue());
        assertThat(name, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number.length(), lessThan(4));

    }


}