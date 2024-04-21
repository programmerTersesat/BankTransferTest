package com.example.nando.demoajanando.service;


import com.example.nando.demoajanando.dto.AccountBankDTO;
import com.example.nando.demoajanando.model.AccountBank;
import com.example.nando.demoajanando.repository.AccountBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class AccountBankService {

    @Autowired
    private AccountBankRepository accountBankRepository;

    public AccountBank save(AccountBank accountBank,String type){
        if("UPDATE".equals(type)){
            accountBank.setUpdatedAt(LocalDateTime.now().toString());
        }else if("SAVE".equals(type)){
            accountBank.setCreatedAt(LocalDateTime.now().toString());
        }
        return accountBankRepository.save(accountBank);
    }

    public Iterable<AccountBank> findAll(){
        return  accountBankRepository.findAll();
    }

    public void removeOne(Long id){
        accountBankRepository.deleteById(id);
    }

    public String transfer(AccountBankDTO dto){

        Optional<AccountBank> checkedSource = accountBankRepository.findById(dto.getIdSource());

        if(checkedSource.isPresent()){
            AccountBank accountBankSource = checkedSource.get();
            if(accountBankSource.getSaldo() > 0){
                Double saldo = accountBankSource.getSaldo() - dto.getTransfer();
                accountBankSource.setSaldo(saldo);
                accountBankSource.setUpdatedAt(LocalDateTime.now().toString());
            }
            accountBankRepository.save(accountBankSource);
        }else {
            return "Failed";
        }

        Optional<AccountBank> destinationSource = accountBankRepository.findById(dto.getIdDestination());

        if(destinationSource.isPresent()){
            AccountBank accountBankDestination = destinationSource.get();
            if(accountBankDestination.getSaldo() > 0){
                Double saldo = accountBankDestination.getSaldo() + dto.getTransfer();
                accountBankDestination.setSaldo(saldo);
                accountBankDestination.setCreatedAt(LocalDateTime.now().toString());
            }
            accountBankRepository.save(accountBankDestination);
        }else{
            return "Failed";
        }

        return "Success";
    }
}
