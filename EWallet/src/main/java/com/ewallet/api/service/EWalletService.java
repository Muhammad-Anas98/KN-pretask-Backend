package com.ewallet.api.service;

import com.ewallet.api.data.EntityToDtoMapper;
import com.ewallet.api.data.EWalletDto;
import com.ewallet.api.enums.Currencies;
import com.ewallet.api.enums.TransactionType;
import com.ewallet.api.model.*;
import com.ewallet.api.repository.CustomerRepo;
import com.ewallet.api.repository.TransactionsRepo;
import com.ewallet.api.repository.EWalletRepo;
import com.ewallet.api.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EWalletService {

	private final EWalletRepo EWalletRepos;
	private final EntityToDtoMapper entityToDtoMapper;
	private final CustomerRepo customerRepository;
	private final TransactionsRepo transactionRepository;

	public EWalletDto createNewWallet(Long id, Currencies currencies, String name) {

		Optional<Customer> customer = customerRepository.findById(id);
		if(!customer.isPresent()) throw new ApplicationException(id);

		Wallet wallet = new Wallet();
		wallet.setName(name);
		wallet.setCurrency(currencies);
		wallet.setCustomer(customer.get());
		EWalletRepos.save(wallet);

		return EWalletDto.builder()
				.id(wallet.getId())
				.name(wallet.getName())
				.balance(wallet.getBalance())
				.currency(wallet.getCurrency().toString())
				.created(wallet.getCreated())
				.build();
	}

	public EWalletDto getWallet(Long id) {

		Optional<Wallet> wallet = EWalletRepos.findById(id);
		if(!wallet.isPresent()) throw new ApplicationException(id);

		return EWalletDto.builder()
				.id(wallet.get().getId())
				.name(wallet.get().getName())
				.balance(wallet.get().getBalance())
				.currency(wallet.get().getCurrency().toString())
				.created(wallet.get().getCreated())
				.build();
	}

	public List<EWalletDto> fetchAllWallets() {

		return entityToDtoMapper.toDto(EWalletRepos.findAll());
	}

	public EWalletDto topUpOrWithdraw(Long id, Double amount, TransactionType type) {

		Optional<Wallet> wallet = EWalletRepos.findById(id);
		if(!wallet.isPresent()) throw new ApplicationException(id);

		Transaction transaction = new Transaction();
		transaction.setTransactionType(type);
		transaction.setWallet(wallet.get());
		transaction.setAmount(amount);

		if (type == TransactionType.CREDIT) {
			amount = Math.abs(amount);
		}
		else {
			amount = -1 * Math.abs(amount);
		}

		if (wallet.get().getBalance() + amount < 0) {
			throw new ApplicationException("Not Enough Amount To Perform The Transaction.");
		}

		wallet.get().setBalance(wallet.get().getBalance() + amount);

		EWalletRepos.save(wallet.get());
		transactionRepository.save(transaction);
		return entityToDtoMapper.toDto(wallet.get());
	}

}
