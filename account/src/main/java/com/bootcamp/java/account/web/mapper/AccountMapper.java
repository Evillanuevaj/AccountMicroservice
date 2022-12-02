package com.bootcamp.java.account.web.mapper;

import com.bootcamp.java.account.domain.Account;
import com.bootcamp.java.account.web.model.AccountModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    //Permite crear un modelo a una entidad de dominio
    Account modelToEntity (AccountModel model);
    //de un dominio a una entidad de modelo para navegar entre las capas
    AccountModel entityToModel (Account event);
    //actualizar una entidad existente con otra
    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Account entity, Account updateEntity);

}
