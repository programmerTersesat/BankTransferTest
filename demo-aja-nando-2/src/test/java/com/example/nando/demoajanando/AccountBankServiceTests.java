package com.example.nando.demoajanando;

import com.example.nando.demoajanando.dto.AccountBankDTO;
import com.example.nando.demoajanando.model.AccountBank;
import com.example.nando.demoajanando.repository.AccountBankRepository;
import com.example.nando.demoajanando.service.AccountBankService;
import com.sun.net.httpserver.Authenticator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountBankServiceTests {

    @Mock
    private AccountBankRepository accountBankRepository;

    @InjectMocks
    private AccountBankService accountBankService;

    @Test
    public void AccountBank_CreateAccount(){
        AccountBank accountBank = new AccountBank();
        accountBank.setName("Nandosan");
        accountBank.setId(1L);
        accountBank.setAddress("Jl Panjang");
        accountBank.setDatebirth("1990-11-11");
        accountBank.setSaldo(0.0);
        accountBank.setCreatedAt(LocalDateTime.now().toString());
        accountBank.setUpdatedAt(LocalDateTime.now().toString());

        when(accountBankRepository.save(Mockito.any(AccountBank.class))).thenReturn(accountBank);

        AccountBank saved = accountBankService.save(accountBank);

        Assertions.assertNotNull(saved,"Account Bank is not Null");
    }

    @Test
    public void AccountBank_TransferMoney(){
        AccountBankDTO dto = AccountBankDTO.builder().idSource(1L).idDestination(2L).transfer(10000.00).build();
        AccountBank accountBank1 = AccountBank.builder().id(1L)
                        .address("Jl Jalan 1")
                                .name("nando")
                                        .datebirth("1990-11-11")
                                                .createdAt(LocalDateTime.now().toString())
                                                        .updatedAt(LocalDateTime.now().toString())
                                                                .saldo(10000.00).build();

        when(accountBankRepository.findById(1L)).thenReturn(Optional.of(accountBank1));

        AccountBank accountBank2 = AccountBank.builder().id(1L)
                .address("Jl Jalan 2")
                .name("bibi")
                .datebirth("1991-11-11")
                .createdAt(LocalDateTime.now().toString())
                .updatedAt(LocalDateTime.now().toString())
                .saldo(10000.00).build();

        when(accountBankRepository.findById(1L)).thenReturn(Optional.of(accountBank2));

        String result = accountBankService.transfer(dto);
        Assertions.assertSame("Failed",result);
    }
}
