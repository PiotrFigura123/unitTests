package pl.pfigura.testing.account;

import java.util.List;
import java.util.stream.Collectors;

class AccountService {
    private AccountRepository accountRepository;

    AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    List<Account> getAllActiveAccount(){
      return accountRepository.getAllAccount().stream()
              .filter(Account::isActive)
              .collect(Collectors.toList());
    }

    List<String> findByName(String name){
        return accountRepository.getByName(name);
    }
}
