package com.example.BankingApp.service.impl;


import com.example.BankingApp.dto.AccountDto;
import com.example.BankingApp.entity.Account;
import com.example.BankingApp.mapper.AccountMapper;
import com.example.BankingApp.repository.AccountRepo;
import com.example.BankingApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepo accountRepo;

    @Autowired
    public AccountServiceImpl (AccountRepo accountRepo){
        this.accountRepo=accountRepo;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepo.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account=accountRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDto(account);


    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account=accountRepo.findById(id).orElseThrow(()-> new RuntimeException("account does not exist"));
        double total=account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount=accountRepo.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

}
