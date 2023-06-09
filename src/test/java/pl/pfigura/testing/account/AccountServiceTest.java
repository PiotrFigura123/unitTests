package pl.pfigura.testing.account;

import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class AccountServiceTest {
    @Test
    void getAllActiveAccount() {
        //given
        List<Account> accounts = prepareAccountData();
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);

        given(accountRepository.getAllAccount()).willReturn(accounts);
        //when
        List<Account> accountList = accountService.getAllActiveAccount();
        //than
        assertThat(accountList, hasSize(2));
    }


    @Test
    void getAccountsByName() {
        //given
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getByName("dawid")).willReturn(Collections.singletonList("nowak"));

        //when
        List<String> accountNames = accountService.findByName("dawid");

        //then
        assertThat(accountNames, contains("nowak"));
    }

    private List<Account> prepareAccountData() {
        Address address1 = new Address("pierwsza", 1);
        Account account1 = new Account(address1);
        Account account2 = new Account();
        Address address3 = new Address("druga", 2);
        Account account3 = new Account(address3);

        return Arrays.asList(account1, account2, account3);
    }
}
