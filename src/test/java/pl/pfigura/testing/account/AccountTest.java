package pl.pfigura.testing.account;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class AccountTest {

    @Test
    void newAccountShouldNotBeActive() {
        //given
        Account account = new Account();
        //then -> Junit
        assertFalse(account.isActive());

        //then -> hamcrest
        assertThat(account.isActive(), equalTo(false));
        assertThat(account.isActive(), is(false));

    }

    @Test
    void newAccountShouldBeActiveAfterActivation() {
        Account account = new Account();
        assertFalse(account.isActive());
        account.activate();
        assertTrue(account.isActive());
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultAddressSet() {
        //given
        Account account = new Account();
        //when
        Address address = account.getDefaultDeliveryAddress();
        //then
        assertNull(address);
    }

    @Test
    void defaultAdressShouldNotBeNUllAfterBeeingSet() {
        //given
        Address address = new Address("kar", 9);
        Account account = new Account();
        //when
        account.setDefaultDeliveryAddress(address);
        Address defaulfAdress = account.getDefaultDeliveryAddress();
        //then -> assertJ
        assertNotNull(defaulfAdress);

        //then -> hamcrest
        assertThat(defaulfAdress, notNullValue());
    }

    @RepeatedTest(5)
    void newAccountWithNotNullAddressShouldNotBeActive() {
        //given
        Address address = new Address("Wino", 9);

        //when
        Account account = new Account(address);

        //than
        assumingThat(address != null, () -> assertTrue(account.isActive()));
    }

}