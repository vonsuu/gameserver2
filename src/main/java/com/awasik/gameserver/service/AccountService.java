package com.awasik.gameserver.service;

import com.awasik.gameserver.model.Account;
import com.awasik.gameserver.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createGuestAccount() {
        String random = "guest-" + UUID.randomUUID();
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setCreationDate(LocalDateTime.now());
        account.setLastLogin(LocalDateTime.now());
        account.setEmail(random + "@example.com");
        account.setUsername(random);
        account.setPassword(random);
        return accountRepository.save(account);
    }
}
