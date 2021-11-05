package com.ewallet.api.model;

import com.ewallet.api.enums.Currencies;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "wallets")
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Currencies currency;

	@Builder.Default
	private Double balance = 0D;

	@Builder.Default
	private LocalDateTime created = LocalDateTime.now();

	private String details;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	@ToString.Exclude
	private Customer customer;

	@ToString.Exclude
	@OneToMany(mappedBy = "wallet")
	private List<Transaction> transactions;

}