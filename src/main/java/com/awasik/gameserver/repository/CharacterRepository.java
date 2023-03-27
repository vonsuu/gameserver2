package com.awasik.gameserver.repository;

import com.awasik.gameserver.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CharacterRepository extends JpaRepository<Character, UUID> {
    List<Character> findByAccountId(UUID accountId);

}
