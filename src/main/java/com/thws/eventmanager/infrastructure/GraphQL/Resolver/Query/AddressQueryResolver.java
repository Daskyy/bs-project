package com.thws.eventmanager.infrastructure.GraphQL.Resolver.Query;

import com.thws.eventmanager.domain.services.models.AddressService;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.AddressCriteriaInput;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.PaginationInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperGQLDomain.AddressMapperGQL;
import com.thws.eventmanager.infrastructure.components.persistence.entities.AddressEntity;
import com.thws.eventmanager.infrastructure.components.persistence.mapper.AddressMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;

import java.util.List;

public class AddressQueryResolver implements GraphQLQueryResolver {
    AddressMapper addressMapper = new AddressMapper();
    AddressMapperGQL addressMapperGQL = new AddressMapperGQL();
    AddressService addressService = new AddressService();
    public AddressGQL address(String id){
        AddressEntity addressEntity = addressService.getAddressById(Long.parseLong(id)).orElse(null);
        assert addressEntity != null;
        return addressMapperGQL.toModelGQL(addressMapper.toModel(addressEntity));
    }

    public List<AddressGQL> addresses(AddressCriteriaInput criteria, PaginationInput page) {
        int safePage = (page != null && page.getPage() != null) ? page.getPage() : 1;
        int safePageSize = (page != null && page.getPageSize() != null) ? page.getPageSize() : Integer.MAX_VALUE;


        if (criteria == null) {
            return addressService.getAllAddresses(List.of(), List.of(), safePage, safePageSize).stream()
                    .map(addressMapper::toModel)
                    .map(addressMapperGQL::toModelGQL)
                    .toList();
        }

        List<String> criteriaList = criteria.getCriteria();
        List<Object> valuesList = criteria.getValues();

        if (criteriaList.size() != valuesList.size()) {
            throw new IllegalArgumentException("Criteria and values lists must have the same size");
        } else {
            return addressService.getAllAddresses(criteriaList, valuesList, safePage, safePageSize).stream()
                    .map(addressMapper::toModel)
                    .map(addressMapperGQL::toModelGQL)
                    .toList();
        }
    }
}
