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
        Account account = new Account();
        account.setId(UUID.randomUUID());
        account.setCreationDate(LocalDateTime.now());
        account.setLastLogin(LocalDateTime.now());
        account.setEmail("guest-" + UUID.randomUUID() + "@example.com");
        return accountRepository.save(account);
    }
}
