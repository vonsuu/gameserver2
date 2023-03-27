package com.awasik.gameserver.controller;

import com.awasik.gameserver.model.Account;
import com.awasik.gameserver.model.Character;
import com.awasik.gameserver.repository.AccountRepository;
import com.awasik.gameserver.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts/{accountId}/characters")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public List<Character> getCharactersByAccountId(@PathVariable UUID accountId) {
        List<Character> characters = characterRepository.findByAccountId(accountId);
        if (characters.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No characters found for account ID: " + accountId);
        }
        return characters;
    }
    @PostMapping
    public ResponseEntity<Character> createCharacter(@PathVariable UUID accountId, @RequestBody Character character) {
        // Set the account ID for the new character
        // Get the account to associate with the character
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        character.setAccount(account);
        // Generate a new UUID for the character ID
        character.setId(UUID.randomUUID());
        // Set the default value for the level property
        character.setLevel(1);
        // Save the new character to the database
        Character savedCharacter = characterRepository.save(character);
        // Return a response with the saved character and a 201 Created status
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

}
