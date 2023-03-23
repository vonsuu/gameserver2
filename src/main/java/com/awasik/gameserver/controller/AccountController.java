package com.awasik.gameserver.controller;

import com.awasik.gameserver.model.Account;
import com.awasik.gameserver.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createGuestAccount")
    public ResponseEntity<String> createGuestAccount() {
        // Call accountService to create a new account without specifying email or username
        Account account = accountService.createGuestAccount();
        // Return a response with the new account's ID
        return ResponseEntity.ok("New guest account created with ID: " + account.getId());
    }

}
