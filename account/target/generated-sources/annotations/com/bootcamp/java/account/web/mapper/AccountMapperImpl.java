package com.bootcamp.java.account.web.mapper;

import com.bootcamp.java.account.domain.Account;
import com.bootcamp.java.account.web.model.AccountModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-02T12:45:48-0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.16 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account modelToEntity(AccountModel model) {
        if ( model == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.id( model.getId() );
        account.identityNumber( model.getIdentityNumber() );
        account.name( model.getName() );
        account.lastName( model.getLastName() );
        account.businessName( model.getBusinessName() );
        account.email( model.getEmail() );
        account.phoneNumber( model.getPhoneNumber() );
        account.birthday( model.getBirthday() );
        account.type( model.getType() );

        return account.build();
    }

    @Override
    public AccountModel entityToModel(Account event) {
        if ( event == null ) {
            return null;
        }

        AccountModel.AccountModelBuilder accountModel = AccountModel.builder();

        accountModel.id( event.getId() );
        accountModel.identityNumber( event.getIdentityNumber() );
        accountModel.name( event.getName() );
        accountModel.lastName( event.getLastName() );
        accountModel.businessName( event.getBusinessName() );
        accountModel.email( event.getEmail() );
        accountModel.phoneNumber( event.getPhoneNumber() );
        accountModel.birthday( event.getBirthday() );
        accountModel.type( event.getType() );

        return accountModel.build();
    }

    @Override
    public void update(Account entity, Account updateEntity) {
        if ( updateEntity == null ) {
            return;
        }

        entity.setIdentityNumber( updateEntity.getIdentityNumber() );
        entity.setName( updateEntity.getName() );
        entity.setLastName( updateEntity.getLastName() );
        entity.setBusinessName( updateEntity.getBusinessName() );
        entity.setEmail( updateEntity.getEmail() );
        entity.setPhoneNumber( updateEntity.getPhoneNumber() );
        entity.setBirthday( updateEntity.getBirthday() );
        entity.setType( updateEntity.getType() );
    }
}
