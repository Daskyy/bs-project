package com.thws.eventmanager.Unit.Infrastructure.GraphQL.resolver.Mapper.MapperInputGQL;

import com.thws.eventmanager.infrastructure.GraphQL.InputModels.AddressInput;
import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;
import com.thws.eventmanager.infrastructure.GraphQL.Resolver.Mapper.MapperInputGQL.AddressInputMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressInputMapperTest {
    @Test
    void toModelGQL(){
        AddressInputMapper mapper = new AddressInputMapper();
        AddressInput addressInput = new AddressInput();
        addressInput.setStreet("street");
        addressInput.setCity("city");
        addressInput.setNo(32);
        addressInput.setCountry("country");
        addressInput.setZipCode(19432);

        AddressGQL adressGQL = mapper.toModelGQL(addressInput);

        assertNotNull(adressGQL);
        assertEquals("street", adressGQL.getStreet());
        assertEquals("city", adressGQL.getCity());
        assertEquals(32, adressGQL.getNo());
        assertEquals("country", adressGQL.getCountry());
        assertEquals(19432, adressGQL.getZipCode());
    }
}