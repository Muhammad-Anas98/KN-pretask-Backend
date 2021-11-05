package com.ewallet.api.repository;

import com.ewallet.api.model.Transaction;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionsRepo extends JpaRepository<Transaction, Long> {
}
