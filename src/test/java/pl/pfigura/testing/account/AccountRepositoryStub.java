package pl.pfigura.testing.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AccountRepositoryStub implements AccountRepository{
    @Override
    public List<Account> getAllAccount() {
        Address address1 = new Address("pierwsza",1);
        Account account1 = new Account(address1);
        Account account2= new Account();
        Address address3 = new Address("druga",2);
        Account account3 = new Account(address3);

        return Arrays.asList(account1,account2,account3);
    }

    @Override
    public List<String> getByName(final String name) {
        return null;
    }
}
