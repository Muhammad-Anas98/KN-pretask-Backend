package com.ewallet.api.data;

import com.ewallet.api.model.Wallet;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class EntityToDtoMapper {


	public List<EWalletDto> toDto(List<Wallet> data) {

		List<EWalletDto> list = new ArrayList<>();
		for (Wallet wallet : data) {
			list.add(toDto(wallet));
		}
		return list;
	}

	public EWalletDto toDto(Wallet wallet) {
		return EWalletDto.builder()
				.id(wallet.getId())
				.name(wallet.getName())
				.balance(wallet.getBalance())
				.currency(wallet.getCurrency().toString())
				.created(wallet.getCreated())
				.build();
	}
}
