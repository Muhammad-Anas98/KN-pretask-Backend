package com.ewallet.api.controller.v1;


import com.ewallet.api.data.EWalletDto;
import com.ewallet.api.enums.Currencies;
import com.ewallet.api.enums.TransactionType;
import com.ewallet.api.service.EWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://172.20.208.1:3000", "http://localhost:3000"})
public class EWalletController {

	private final EWalletService walletService;

	@GetMapping("/wallet/{id}")
	public EWalletDto getWallet(@PathVariable("id") Long id) {

		return walletService.getWallet(id);
	}

	@PutMapping("/wallet/update/{id}")
	public EWalletDto topUpOrWithdraw(@PathVariable("id") Long id,
									@RequestParam TransactionType type,
									@RequestParam Double amount) {
		return walletService.topUpOrWithdraw(id, amount, type);
	}

	@GetMapping("/wallet/fetchAll")
	public List<EWalletDto> fetchAllWallets() {

		return walletService.fetchAllWallets();
	}

	@PostMapping("/wallet/create")
	public EWalletDto createNewWallet(@RequestParam(name = "id") Long id,
							 @RequestParam Currencies currency,
							 @RequestParam String name) {
		return walletService.createNewWallet(id, currency, name);
	}


}