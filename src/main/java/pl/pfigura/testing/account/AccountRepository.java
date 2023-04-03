package pl.pfigura.testing.account;

import java.util.List;

interface AccountRepository {
    List<Account> getAllAccount();
    List<String> getByName(String name);
}
