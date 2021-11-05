package com.ewallet.api.repository;

import com.ewallet.api.model.Wallet;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EWalletRepo extends JpaRepository<Wallet, Long> {

	@Query("select wallet from Wallet wallet order by wallet.id asc , wallet.balance desc")
	List<Wallet> findAll();
}
