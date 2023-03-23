package com.awasik.gameserver.repository;
import java.util.UUID;

import com.awasik.gameserver.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

}
