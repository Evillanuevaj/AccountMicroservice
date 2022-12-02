package com.bootcamp.java.account.service;

import com.bootcamp.java.account.domain.Account;
import com.bootcamp.java.account.repository.AccountRepository;
import com.bootcamp.java.account.web.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional

public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    //Llama a los méodos que están en el repositoey
    public Flux<Account> findAll(){
        log.debug("findAll executed");
        return accountRepository.findAll();
    }

    public Mono<Account> findById(String customerId){
        log.debug("findById executed {}", customerId);
        return accountRepository.findById(customerId);
    }
    //Se guarda en el repositorio
    public Mono<Account> create(Account account){
        log.debug("create executed {}", account);
        return accountRepository.save(account);
    }
    public Mono<Account> update(String customerId, Account customer){
        log.debug("update executed {}:{}", customerId, customer);
        //Se busca por ID is existe se actualiza
        return accountRepository.findById(customerId)
                .flatMap(dbCustomer -> {
                    //clientMapper.update(dbCustomer, customer);
                    return accountRepository.save(dbCustomer);
                });
    }

    public Mono<Account> delete(String customerId){
        log.debug("delete executed {}", customerId);
        return accountRepository.findById(customerId)
                .flatMap(existingCustomer -> accountRepository.delete(existingCustomer)
                        .then(Mono.just(existingCustomer)));
    }
    }
