package com.ewallet.api.model;

import com.ewallet.api.enums.TransactionType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "transactions")
public class Transaction{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Builder.Default
	private LocalDateTime time = LocalDateTime.now();

	private Double amount;

	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	@ManyToOne
	@JoinColumn(name = "wallet_id", nullable = false)
	private Wallet wallet;
}
