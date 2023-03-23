package com.awasik.gameserver.service;

import com.awasik.gameserver.model.Account;
import com.awasik.gameserver.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AccountServiceIntegrationTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testCreateGuestAccount() {
        Account account = accountService.createGuestAccount();

        assertNotNull(account.getId());
        assertNotNull(account.getCreationDate());
        assertNotNull(account.getLastLogin());
        assertNotNull(account.getEmail());

        Account savedAccount = accountRepository.findById(account.getId()).orElse(null);
        assertNotNull(savedAccount);
        assertEquals(account.getId(), savedAccount.getId());
        assertEquals(account.getCreationDate(), savedAccount.getCreationDate());
        assertEquals(account.getLastLogin(), savedAccount.getLastLogin());
        assertEquals(account.getEmail(), savedAccount.getEmail());
    }
}
