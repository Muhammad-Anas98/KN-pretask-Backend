package com.ewallet.api.data;

import com.ewallet.api.model.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EWalletDto {

	public Long id;
	public String name;
	public String currency;
	public Double balance;
	public LocalDateTime created;
}