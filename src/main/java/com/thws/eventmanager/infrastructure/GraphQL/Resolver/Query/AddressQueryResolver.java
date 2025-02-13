package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Query;

import com.thws.eventmanager.domain.services.db.AddressService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.AddressCriteriaInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.AdressMapperGQL;
import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.AddressMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;

import java.util.List;

public class AddressQueryResolver implements GraphQLQueryResolver {
    AddressMapper addressMapper = new AddressMapper();
    AdressMapperGQL adressMapperGQL = new AdressMapperGQL();
    AddressService addressService = new AddressService();
    public AddressGQL address(String id){
        AddressEntity addressEntity = addressService.getAddressById(Long.parseLong(id)).orElse(null);
        assert addressEntity != null;
        return adressMapperGQL.toModelGQL(addressMapper.toModel(addressEntity));
    }

    public List<AddressGQL> addresses(AddressCriteriaInput criteria) {
        if (criteria == null) {
            return addressService.getAllAddresses().stream()
                    .map(addressMapper::toModel)
                    .map(adressMapperGQL::toModelGQL)
                    .toList();
        }

        List<String> criteriaList = criteria.getCriteria();
        List<Object> valuesList = criteria.getValues();
        if (criteriaList.size() != valuesList.size()) {
            throw new IllegalArgumentException("Criteria and values lists must have the same size");
        } else {
            return addressService.getAllAddresses(criteriaList, valuesList).stream()
                    .map(addressMapper::toModel)
                    .map(adressMapperGQL::toModelGQL)
                    .toList();
        }
    }
}
