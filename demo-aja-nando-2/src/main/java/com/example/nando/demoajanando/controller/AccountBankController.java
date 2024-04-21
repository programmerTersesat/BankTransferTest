package com.example.nando.demoajanando.controller;

import com.example.nando.demoajanando.dto.AccountBankDTO;
import com.example.nando.demoajanando.dto.ResponseData;
import com.example.nando.demoajanando.model.AccountBank;
import com.example.nando.demoajanando.service.AccountBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account_bank")
public class AccountBankController {

    @Autowired
    private AccountBankService accountBankService;

    @GetMapping
    public Iterable<AccountBank> getAllAccountBank(){
        return accountBankService.findAll();
    }

    @PostMapping
    public ResponseEntity<ResponseData<AccountBank>> saveAccountBank(@Valid @RequestBody AccountBank accountBank,Errors errors){
        ResponseData responseData = new ResponseData();

        // get error
        if(errors.hasErrors()){
            for(ObjectError error :errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

        }

        responseData.setStatus(true);
        responseData.setPayload(accountBankService.save(accountBank,"SAVE"));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<AccountBank>> updateAccountBank(@Valid @RequestBody AccountBank accountBank,Errors errors){
        ResponseData responseData = new ResponseData();

        // get error
        if(errors.hasErrors()){
            for(ObjectError error :errors.getAllErrors()){
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

        }

        responseData.setStatus(true);
        responseData.setPayload(accountBankService.save(accountBank,"UPDATE"));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void deleteByIdAccountBank(@PathVariable("id") Long id){
        accountBankService.removeOne(id);
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public ResponseEntity<ResponseData<String>> saveTransfer(@RequestBody AccountBankDTO dto){
        ResponseData responseData = new ResponseData();
        responseData.setStatus(true);
        responseData.setPayload(accountBankService.transfer(dto));
        return ResponseEntity.ok(responseData);
    }
}
