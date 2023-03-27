package com.awasik.gameserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import com.awasik.gameserver.model.Character;

import java.util.UUID;

@Component
public class CharacterCacheService {
    @Autowired
    private CacheManager characterCacheManager;

    @Cacheable(value = "characters", key = "#id")
    public Character getCharacterFromCache(String id) {
        return null; // This will trigger the cache miss and fetch the character from the repository
    }

    public void putCharacterInCache(Character character) {
        characterCacheManager.getCache("characters").put(character.getId().toString(), character);
    }
}
