package com.thws.eventmanager.Integration.Operations;

import com.thws.eventmanager.infrastructure.GraphQL.Models.AddressGQL;
import com.thws.eventmanager.infrastructure.GraphQL.InputModels.AddressInput;

public class AddressOperation extends AbstractAPIOperation {

    public AddressGQL createAddress(AddressInput input) throws Exception {
        String mutation = String.format(
            "mutation createAddress { " +
            "  createAddress(input: {street: \\\"%s\\\", no: %d, city: \\\"%s\\\", zipCode: %d, country: \\\"%s\\\"}) { " +
            "    id street no city zipCode country " +
            "  }" +
            "}",
            input.getStreet(), input.getNo(), input.getCity(), input.getZipCode(), input.getCountry());

        return executeQuery(mutation, "createAddress", AddressGQL.class);
    }

    public AddressGQL updateAddress(String id, AddressInput input) throws Exception {
        String mutation = String.format(
            "mutation updateAddress { " +
            "  updateAddress(id: \\\"%s\\\", input: {street: \\\"%s\\\", no: %d}) { " +
            "    id street no " +
            "  }" +
            "}",
            id, input.getStreet(), input.getNo());

        return executeQuery(mutation, "updateAddress", AddressGQL.class);
    }

    public AddressGQL deleteAddress(String id) throws Exception {
        String mutation = String.format(
            "mutation deleteAddress { " +
            "  deleteAddress(id: \\\"%s\\\") { " +
            "    id " +
            "  }" +
            "}", 
            id);

        return executeQuery(mutation, "deleteAddress", AddressGQL.class);
    }
    
    // Neue Query-Methoden

    public AddressGQL getAddress(String id) throws Exception {
        String query = String.format(
            "query getAddress { " +
            "  address(id: \\\"%s\\\") { id street no city zipCode country }" +
            "}", id);
        return executeQuery(query, "address", AddressGQL.class);
    }
    
    public AddressGQL[] getAddresses() throws Exception {
        String query = "query getAddresses { " +
                       "  addresses(criteria: {}, page: {}) { id street no city zipCode country }" +
                       "}";
        return executeQuery(query, "addresses", AddressGQL[].class);
    }
} 