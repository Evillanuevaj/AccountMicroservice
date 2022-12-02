package com.bootcamp.java.account.web;
import javax.validation.Valid;
import com.bootcamp.java.account.domain.Account;
import com.bootcamp.java.account.service.AccountService;
import com.bootcamp.java.account.web.mapper.AccountMapper;
import com.bootcamp.java.account.web.model.AccountModel;
import lombok.RequiredArgsConstructor;
//import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/account")

public class AccountController {
    //@Value("${spring.application.name}")
    String name;
    //@Value("${server.port}")
    String port;

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountMapper accountMapper;
    @GetMapping
    public Mono<ResponseEntity<Flux<AccountModel>>> getAll(){
        log.info("getAll executed");
        return Mono.just(ResponseEntity.ok()
                .body(accountService.findAll()
                        .map(customer -> accountMapper.entityToModel(customer))));
    }
    @GetMapping("/{id}")
            public Mono<ResponseEntity<AccountModel>> getById(@PathVariable String id){
        log.info("getById executed {}", id);
        Mono<Account> response = accountService.findById(id);
        return response
                .map(customer -> accountMapper.entityToModel(customer))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<AccountModel>> create(@Valid @RequestBody AccountModel
                                                              request){
        log.info("create executed {}", request);
        return accountService.create(accountMapper.modelToEntity(request))
                .map(customer -> accountMapper.entityToModel(customer))
                .flatMap(c ->
                        Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name,
                                        port, "customer", c.getId())))
                                .body(c)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<AccountModel>> updateById(@PathVariable String id, @Valid
    @RequestBody AccountModel request){
        log.info("updateById executed {}:{}", id, request);
        return accountService.update(id, accountMapper.modelToEntity(request))
                .map(customer -> accountMapper.entityToModel(customer))
                .flatMap(c ->
                        Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name,
                                        port, "customer", c.getId())))
                                .body(c)))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id){
        log.info("deleteById executed {}", id);
        return accountService.delete(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
